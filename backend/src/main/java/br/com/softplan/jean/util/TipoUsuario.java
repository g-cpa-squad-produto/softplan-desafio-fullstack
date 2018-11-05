package br.com.softplan.jean.util;

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
