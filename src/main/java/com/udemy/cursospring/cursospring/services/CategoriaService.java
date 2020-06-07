package com.udemy.cursospring.cursospring.services;

import com.udemy.cursospring.cursospring.model.Categoria;
import com.udemy.cursospring.cursospring.repositories.CategoriaRepository;
import com.udemy.cursospring.cursospring.services.exceptions.DataIntegrityException;
import com.udemy.cursospring.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repository;

    public Categoria find(Integer id){
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        return repository.saveAndFlush(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repository.saveAndFlush(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
}
