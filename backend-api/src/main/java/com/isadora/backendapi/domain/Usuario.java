package com.isadora.backendapi.domain;

import com.isadora.backendapi.enums.UserEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//persistencia responsavel por gerar o id (auto incremento nesse caso)
    private Long id;
    @NotNull
    @NotEmpty
    @NotBlank(message = "Necessário incluir um email.")
    @Column(nullable = false, unique = true)
    private String email;
    private UserEnum tipo;
    private UserEnum status;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEnum getTipo() {
        return tipo;
    }

    public void setTipo(UserEnum tipo) {
        this.tipo = tipo;
    }

    public UserEnum getStatus() {
        return status;
    }

    public void setStatus(UserEnum status) {
        this.status = status;
    }
}
