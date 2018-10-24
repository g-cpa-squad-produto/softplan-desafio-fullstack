package com.agfgerador.autenticacao.domain;

public enum TipoSistema   {

	ProjetoBase("ProjetoBase");
	
	private final String nome;
	
	public String getNome() {
		return nome;
	}
	
	public String toString()
	{
		return nome;
	}
	
	private TipoSistema(String nome){
		this.nome = nome;
	}

}
