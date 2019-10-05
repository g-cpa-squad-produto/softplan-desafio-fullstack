package com.isadora.backendapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isadora.backendapi.enums.UserEnum;
import com.isadora.backendapi.enums.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//persistencia responsavel por gerar o id (auto incremento nesse caso)
    private Long id;
    @NotNull
    @NotBlank(message = "Necessário incluir um email.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password required")
    @Size(min = 6)
    private String password;

    private UserEnum tipo;
    private UserStatus status;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "usuario", orphanRemoval = true)
    @JsonIgnore
    private List<Parecer> parecer = new ArrayList<>();
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<Parecer> getParecer() {
        return parecer;
    }

    public void setParecer(List<Parecer> parecer) {
        this.parecer = parecer;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
}
