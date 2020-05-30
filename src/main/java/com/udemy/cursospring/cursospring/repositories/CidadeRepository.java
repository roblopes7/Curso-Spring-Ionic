package com.udemy.cursospring.cursospring.repositories;

import com.udemy.cursospring.cursospring.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
