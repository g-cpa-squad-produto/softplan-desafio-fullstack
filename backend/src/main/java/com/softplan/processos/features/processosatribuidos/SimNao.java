package com.softplan.processos.features.processosatribuidos;

public enum SimNao {
    SIM("S", "Sim"),
    NAO("N", "Não");

    private final String value;
    private final String description;

    SimNao(final String value, final String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static SimNao of(String value) {
        for (SimNao simNao : values()) {
            if (simNao.getValue().equals(value)) {
                return simNao;
            }
        }
        return null;
    }

}
