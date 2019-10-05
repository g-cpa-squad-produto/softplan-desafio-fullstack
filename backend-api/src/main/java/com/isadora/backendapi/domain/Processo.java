package com.isadora.backendapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Integer numero;
    @NotBlank(message = "Necessário incluir uma descrição para o processo.")
    private String descricao;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created_at;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated_at;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "processo")
    private Parecer parecer;

    public Processo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Parecer getParecer() {
        return parecer;
    }

    public void setParecer(Parecer parecer) {
        this.parecer = parecer;
    }
}
