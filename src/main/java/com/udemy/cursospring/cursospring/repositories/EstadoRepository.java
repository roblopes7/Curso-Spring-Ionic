package com.udemy.cursospring.cursospring.repositories;

import com.udemy.cursospring.cursospring.model.Cidade;
import com.udemy.cursospring.cursospring.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
