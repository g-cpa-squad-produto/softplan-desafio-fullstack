package com.isadora.backendapi.exceptions;

public class ProcessoExceptionResponse {
    private String processoId;


    public ProcessoExceptionResponse(String processoId) {
        this.processoId = processoId;
    }

    public String getProcessoId() {
        return processoId;
    }

    public void setProcessoId(String processoId) {
        this.processoId = processoId;
    }
}
