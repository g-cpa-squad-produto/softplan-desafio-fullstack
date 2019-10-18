package com.desafiosoftplan.backend.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public enum Status {

	APROVADO(1L,"APROVADO"),
	REPROVADO(2L,"REPROVADO"),
	PENDENTE_AGUARDANDO_AVALIACAO(3L,"PENDENTE_AGUARDANDO_AVALIACAO"),
	PENDENTE_AGUARDANDO_REVISAO(4L,"PENDENTE_AGUARDANDO_REVISAO"),
	PENDENTE_READEQUACAO(5L,"PENDENTE_READEQUACAO");
	
	@JsonProperty
	private Long codigo;
	@JsonProperty
	private String descricao;
	
	private Status() {
		// TODO Auto-generated constructor stub
	}
	
	private Status(Long codigo, String descricao) {
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
