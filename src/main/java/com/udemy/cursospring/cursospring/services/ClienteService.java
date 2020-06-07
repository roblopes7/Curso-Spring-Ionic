package com.udemy.cursospring.cursospring.services;

import com.udemy.cursospring.cursospring.dto.ClienteDTO;
import com.udemy.cursospring.cursospring.dto.ClienteDTO;
import com.udemy.cursospring.cursospring.model.Cliente;
import com.udemy.cursospring.cursospring.model.Cliente;
import com.udemy.cursospring.cursospring.repositories.ClienteRepository;
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
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public Cliente find(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(()->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: "
                        + Cliente.class.getName()));
    }

    public Cliente insert(Cliente obj) {
        return repository.saveAndFlush(obj);
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.saveAndFlush(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException error) {
            throw new DataIntegrityException("Não é possível excluir um Cliente que possui Pedidos");
        }
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO dto){
        return new Cliente(dto);
    }
}
