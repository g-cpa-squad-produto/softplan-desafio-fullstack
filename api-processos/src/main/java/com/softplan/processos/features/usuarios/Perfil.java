package com.softplan.processos.features.usuarios;

public enum Perfil {
    ADMINISTRADOR("A", "Administrador"),
    TRIADOR("T", "Triador"),
    FINALIZADOR("F", "Finalizador");

    private final String value;
    private final String description;

    Perfil(final String value, final String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static Perfil of(String value) {
        for (Perfil perfil : values()) {
            if (perfil.getValue().equals(value)) {
                return perfil;
            }
        }
        return null;
    }

}
