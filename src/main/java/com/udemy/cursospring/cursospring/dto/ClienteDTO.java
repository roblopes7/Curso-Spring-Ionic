package com.udemy.cursospring.cursospring.dto;

import com.udemy.cursospring.cursospring.model.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ClienteDTO {


    private Integer id;
    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min = 5, max = 120, message = "Tamanho deve ser entre 5 e 120 caracteres")
    private String nome;
    @NotEmpty(message = "Preenchimento Obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Integer id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public ClienteDTO(Cliente c) {
        this.id = c.getId();
        this.nome = c.getNome();
        this.email = c.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
