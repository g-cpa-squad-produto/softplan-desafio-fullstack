package com.teste.magnum.gerenciadorprocessosapi.model;

public enum StatusProcessoEnum {

    PENDENTE_PARECER(0, "Pendente de parecer"),
    PARECER_ADICIONADO(1, "Parecer adicionado");

    private Integer value;
    private String description;

    StatusProcessoEnum(Integer value, String description) {
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
