package com.desafiosoftplan.backend.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.desafiosoftplan.backend.model.Processo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	@JsonProperty
	@Column(name="descricao")
	private String descricao;
	
	public Processo toObj() {
		return new Processo(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
