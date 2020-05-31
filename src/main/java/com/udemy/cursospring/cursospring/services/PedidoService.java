package com.udemy.cursospring.cursospring.services;

import com.udemy.cursospring.cursospring.model.Pedido;
import com.udemy.cursospring.cursospring.repositories.PedidoRepository;
import com.udemy.cursospring.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;

    public Pedido buscar(Integer id){
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Pedido.class.getName()));
    }
}
