package com.teste.magnum.gerenciadorprocessosapi.model;

public enum TipoUsuarioEnum {

    ADMINISTRADOR(1, "Administrador"),
    TRIADOR(2, "Triador"),
    FINALIZADOR(3, "Finalizador");

    private Integer value;
    private String description;

    TipoUsuarioEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
