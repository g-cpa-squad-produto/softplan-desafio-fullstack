package br.com.sitalobr.dev.desafio.dto;

import java.io.Serializable;

public class ProcessoFinalizadorDTO implements Serializable {
    private static final long serialVersionUID = 2146923029154280521L;

    private Long idProcesso;
    private String parecer;

    public Long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }
}
