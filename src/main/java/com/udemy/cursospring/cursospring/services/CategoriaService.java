package com.udemy.cursospring.cursospring.services;

import com.udemy.cursospring.cursospring.model.Categoria;
import com.udemy.cursospring.cursospring.repositories.CategoriaRepository;
import com.udemy.cursospring.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;

    public Categoria buscar(Integer id){
        Optional<Categoria> categoria = repository.findById(id);
        return categoria.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Categoria.class.getName()));
    }
}
