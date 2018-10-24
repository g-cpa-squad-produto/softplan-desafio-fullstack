package com.agfgerador.autenticacao.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;


@Entity
@Table(name = "agf_usuario",schema="agfpb_autenticacao")
public class Usuario  extends ObjetoPadrao {
	private static final long serialVersionUID = 1L;
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=255,nullable=false)
	private String nome;
	
	@Column(unique = true,length=60,nullable = false)
	private String login;
	
	@Column(length=255,nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private Boolean habilitado;
	
	@Column(length=255,nullable = true)
	private String email;
	
	@Column(length=14,nullable = true)
	private String telefone;
	
	@Column(length=15,nullable = true)
	private String telefoneAlternativo;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
	@OneToOne
	private Aviso aviso;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTelefoneAlternativo() {
		return telefoneAlternativo;
	}
	public void setTelefoneAlternativo(String telefoneAlternativo) {
		this.telefoneAlternativo = telefoneAlternativo;
	}
	
	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public Aviso getAviso() {
		return aviso;
	}

	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}

	@Override
	public int compare(Object arg0, Object arg1) {
		return 0;
	}

	@Override
	public String toLog() {

		return null;
	}

	
}
