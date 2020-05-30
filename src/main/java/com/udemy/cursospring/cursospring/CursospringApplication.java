package com.udemy.cursospring.cursospring;

import com.udemy.cursospring.cursospring.model.*;
import com.udemy.cursospring.cursospring.model.enums.TipoCliente;
import com.udemy.cursospring.cursospring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

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
	}
}
