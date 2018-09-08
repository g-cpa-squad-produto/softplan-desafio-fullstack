package br.com.sitalobr.dev.desafio.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RoleName {
    @JsonProperty("ADMIN") ROLE_ADMIN,
    @JsonProperty("TRIADOR") ROLE_TRIADOR,
    @JsonProperty("FINALIZADOR") ROLE_FINALIZADOR
}
