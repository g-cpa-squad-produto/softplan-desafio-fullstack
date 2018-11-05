package br.com.softplan.jean.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ParecerStatus {
	
	PENDENTE("Pendente"), 
	CONCLUIDO("Concluido");

	private final String descricao;

	private ParecerStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	@JsonValue
    public int toValue() {
        return ordinal();
    }

}
