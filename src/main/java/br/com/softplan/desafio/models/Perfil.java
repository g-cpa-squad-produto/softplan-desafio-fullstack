package br.com.softplan.desafio.models;

import lombok.Getter;

@Getter
public enum Perfil {

    ADM ("Administrador"),
    TRI ("Triador"),
    FIN ("Finalizador");

    private String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }
}
