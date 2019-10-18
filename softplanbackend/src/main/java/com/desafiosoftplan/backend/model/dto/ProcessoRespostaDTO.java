package com.desafiosoftplan.backend.model.dto;

import java.io.Serializable;

import com.desafiosoftplan.backend.enums.Status;
import com.desafiosoftplan.backend.model.Processo;

public class ProcessoRespostaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String descricao;
	private String parecer;
	private String usuarioParecer;
//	private Situacao status;
	private Status status;
	
//	public ProcessoRespostaDTO(Long id, String descricao, String solicitante, String avaliador, Situacao status) {
//		this.id = id;
//		this.descricao = descricao;
//		this.solicitante = solicitante;
//		this.avaliador = avaliador;
//		this.status = status;
//	}
	public ProcessoRespostaDTO(Long id, String descricao, String parecer, String usuarioParecer, Status status) {
		this.id = id;
		this.descricao = descricao;
		this.parecer = parecer;
		this.usuarioParecer = usuarioParecer;
		this.status = status;
	}
	
	public static ProcessoRespostaDTO toDTO(Processo processo) {
		return new ProcessoRespostaDTO(processo.getId(), processo.getDescricao(), processo.getParecer(), processo.getUsuarioParecer(), processo.getStatus());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

//	public Situacao getStatus() {
//		return status;
//	}
//
//	public void setStatus(Situacao status) {
//		this.status = status;
//	}
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public String getUsuarioParecer() {
		return usuarioParecer;
	}

	public void setUsuarioParecer(String usuarioParecer) {
		this.usuarioParecer = usuarioParecer;
	}

}
