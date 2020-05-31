package com.udemy.cursospring.cursospring.repositories;

import com.udemy.cursospring.cursospring.model.Cidade;
import com.udemy.cursospring.cursospring.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
