package br.com.sitalobr.dev.desafio.dto;

import java.io.Serializable;

public class ProcessoCadastroDTO implements Serializable {
    private static final long serialVersionUID = 2167414995894556065L;

    private String descricao;
    private Long finalizador;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getFinalizador() {
        return finalizador;
    }

    public void setFinalizador(Long finalizador) {
        this.finalizador = finalizador;
    }
}
