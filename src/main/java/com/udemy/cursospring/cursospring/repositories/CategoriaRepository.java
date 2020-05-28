package com.udemy.cursospring.cursospring.repositories;

import com.udemy.cursospring.cursospring.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
