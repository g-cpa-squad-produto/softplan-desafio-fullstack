package br.com.softplan.desafio.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@Getter
@JsonSerialize(using = PerfilSerializer.class)
@JsonDeserialize(using = PerfilDeserializer.class)
public enum Perfil {

    ADM ("Administrador"),
    TRI ("Triador"),
    FIN ("Finalizador");

    private String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }
}
