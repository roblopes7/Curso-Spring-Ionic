package com.udemy.cursospring.cursospring.services;

import com.udemy.cursospring.cursospring.model.Cliente;
import com.udemy.cursospring.cursospring.repositories.ClienteRepository;
import com.udemy.cursospring.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public Cliente buscar(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Cliente.class.getName()));
    }
}
