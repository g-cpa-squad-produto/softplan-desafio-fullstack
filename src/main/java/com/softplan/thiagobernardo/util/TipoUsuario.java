package com.softplan.thiagobernardo.util;

public enum TipoUsuario {
	
	ADMIN("Administrador"), 
	TRIADOR("Triador"), 
	FINALIZADOR("Finalizador");

	private final String descricao;

	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
