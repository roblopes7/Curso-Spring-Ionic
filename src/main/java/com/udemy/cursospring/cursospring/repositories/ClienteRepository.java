package com.udemy.cursospring.cursospring.repositories;

import com.udemy.cursospring.cursospring.model.Cliente;
import com.udemy.cursospring.cursospring.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
