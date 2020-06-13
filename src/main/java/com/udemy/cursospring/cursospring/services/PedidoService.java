package com.udemy.cursospring.cursospring.services;

import com.udemy.cursospring.cursospring.model.ItemPedido;
import com.udemy.cursospring.cursospring.model.PagamentoComBoleto;
import com.udemy.cursospring.cursospring.model.Pedido;
import com.udemy.cursospring.cursospring.model.enums.EstadoPagamento;
import com.udemy.cursospring.cursospring.repositories.ItemPedidoRepository;
import com.udemy.cursospring.cursospring.repositories.PagamentoRepository;
import com.udemy.cursospring.cursospring.repositories.PedidoRepository;
import com.udemy.cursospring.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;

    @Autowired
    BoletoService boletoService;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public Pedido buscar(Integer id){
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItemPedidos()) {
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItemPedidos());
        return obj;
    }
}
