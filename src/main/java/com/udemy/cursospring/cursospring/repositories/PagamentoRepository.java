package com.udemy.cursospring.cursospring.repositories;

import com.udemy.cursospring.cursospring.model.Categoria;
import com.udemy.cursospring.cursospring.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
