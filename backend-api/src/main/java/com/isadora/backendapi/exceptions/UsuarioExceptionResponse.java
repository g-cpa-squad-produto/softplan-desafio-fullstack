package com.isadora.backendapi.exceptions;

public class UsuarioExceptionResponse {
    private String usuarioId;


    public UsuarioExceptionResponse(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}
