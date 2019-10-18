package com.desafiosoftplan.backend.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public enum UsuarioVisao {

	ADMINISTRADOR(1L,"ADMINISTRADOR"),
	TRIADOR(2L,"TRIADOR"),
	FINALIZADOR(3L,"FINALIZADOR");
	
	@JsonProperty
	private Long codigo;
	@JsonProperty
	private String descricao;
	
	private UsuarioVisao() {
		// TODO Auto-generated constructor stub
	}
	
	private UsuarioVisao(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
