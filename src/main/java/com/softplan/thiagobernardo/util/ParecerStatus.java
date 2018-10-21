package com.softplan.thiagobernardo.util;

public enum ParecerStatus {
	
	PENDENTE("Pendente"), 
	CONCLUIDO("Concluido"), 
	CANCELADO("Cancelado");

	private final String descricao;

	private ParecerStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
