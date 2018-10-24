package com.agfgerador.autenticacao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;

@Entity
@Table(name="agf_compativeis",schema="agfpb_autenticacao")
public class Compativeis extends ObjetoPadrao{

	private static final long serialVersionUID = 1L;

	@Column(unique=true)
	private Integer codsistema;
	
	@Column(length=10)
	private String versaoliberada;
	
	@Column(length=30)
	private String senhadeliberacao;
	
	
	public Integer getCodsistema() {
		return codsistema;
	}


	public void setCodsistema(Integer codsistema) {
		this.codsistema = codsistema;
	}


	public String getVersaoliberada() {
		return versaoliberada;
	}


	public void setVersaoliberada(String versaoliberada) {
		this.versaoliberada = versaoliberada;
	}


	public String getSenhadeliberacao() {
		return senhadeliberacao;
	}


	public void setSenhadeliberacao(String senhadeliberacao) {
		this.senhadeliberacao = senhadeliberacao;
	}


	@Override
	public int compare(Object o1, Object o2) {

		return 0;
	}


	@Override
	public String toLog() {

		return null;
	}

	

}

