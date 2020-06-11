package com.udemy.cursospring.cursospring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemPedido implements Serializable {


    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPK itemPedidoPK = new ItemPedidoPK();
    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        this.itemPedidoPK.setPedido(pedido);
        this.itemPedidoPK.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Double getSubTotal(){
        return (preco - desconto) * quantidade;
    }

    public ItemPedidoPK getItemPedidoPK() {
        return itemPedidoPK;
    }

    @JsonIgnore
    public Pedido getPedido(){
        return itemPedidoPK.getPedido();
    }

    public Produto getProduto(){
        return itemPedidoPK.getProduto();
    }

    public void setItemPedidoPK(ItemPedidoPK itemPedidoPK) {
        this.itemPedidoPK = itemPedidoPK;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedido)) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(getItemPedidoPK(), that.getItemPedidoPK());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemPedidoPK());
    }
}
