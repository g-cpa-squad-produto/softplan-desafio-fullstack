package com.agfgerador.autenticacao.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;


@Entity
@Table(name="agf_parametrosemail",schema="agfpb_autenticacao")
public class Email extends ObjetoPadrao {
	private static final long serialVersionUID = 1L;

	@Column(length=100)
	private String servidor;
	@Basic
	private Integer porta;
	@Column(length=71)
	private String login;
	@Basic
	private String senha;
	@Basic 
	private String email;
	@Basic 
	private String finalidade;
	
	@ManyToOne  
	@JoinColumn(name="parametro_id") 
	private Parametros parametro;

	@Override
	public String toString() {
		return "Email [servidor=" + servidor + ", porta=" + porta + ", login="
				+ login + ", senha=" + senha + ", email=" + email
				+ ", finalidade=" + finalidade + ", parametro=" + parametro.getId()
				+ "]";
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public Integer getPorta() {
		return porta;
	}

	public void setPorta(Integer porta) {
		this.porta = porta;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public Parametros getParametro() {
		return parametro;
	}

	public void setParametro(Parametros parametro) {
		this.parametro = parametro;
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



