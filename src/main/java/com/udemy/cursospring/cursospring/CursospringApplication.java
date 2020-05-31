package com.udemy.cursospring.cursospring;

import com.udemy.cursospring.cursospring.model.*;
import com.udemy.cursospring.cursospring.model.enums.EstadoPagamento;
import com.udemy.cursospring.cursospring.model.enums.TipoCliente;
import com.udemy.cursospring.cursospring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	PagamentoRepository pagamentoRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.0);
        Produto p2 = new Produto(null, "Impressora", 800.0);
        Produto p3 = new Produto(null, "Mouse", 80.0);

        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado estado1 = new Estado(null, "Paraná");
		Estado estado2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "São Paulo", estado2);
		Cidade cid2 = new Cidade(null, "Curitiba", estado1);
		Cidade cid3 = new Cidade(null, "Maringá", estado1);

		estado1.getCidades().addAll(Arrays.asList(cid2, cid3));
		estado2.getCidades().addAll(Arrays.asList(cid1));

        estadoRepository.saveAll(Arrays.asList(estado1, estado2));
        cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "Maria@gmail.com", "1234567891230", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("3532-1111","3532-2222"));
        Endereco e1 = new Endereco(null, "Rua Flores", "991", "Fundo", "Centro", "86390000", cli1, cid1);
        Endereco e2 = new Endereco(null, "Rua Marechal Candido", "3890", "Apt 325", "Centro", "75800000", cli1, cid2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1,e2));

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, dateFormat.parse("27/05/2017 08:25"), cli1, e1);
		Pedido ped2 = new Pedido(null, dateFormat.parse("08/07/2018 09:25"), cli1, e2);

		Pagamento pg1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pg1);
		Pagamento pg2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, dateFormat.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pg2);

		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pg1,pg2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.0);

		ped1.getItemPedidos().addAll(Arrays.asList(ip1,ip2));
		ped2.getItemPedidos().addAll(Arrays.asList(ip3));

		p1.getItemPedidos().addAll(Arrays.asList(ip1));
		p2.getItemPedidos().addAll(Arrays.asList(ip3));
		p3.getItemPedidos().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
