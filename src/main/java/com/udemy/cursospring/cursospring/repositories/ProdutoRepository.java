package com.udemy.cursospring.cursospring.repositories;

import com.udemy.cursospring.cursospring.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
}
