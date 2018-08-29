package br.com.softplan.desafio.models;

import lombok.Getter;

@Getter
public enum Status {

    PDT ("Pendente"),
    FNL ("Finalizado");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

}
