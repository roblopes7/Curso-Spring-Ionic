package com.udemy.cursospring.cursospring.repositories;

import com.udemy.cursospring.cursospring.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
