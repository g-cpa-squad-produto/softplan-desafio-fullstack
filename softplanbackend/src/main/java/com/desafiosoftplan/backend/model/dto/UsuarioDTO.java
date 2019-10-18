package com.desafiosoftplan.backend.model.dto;

import java.io.Serializable;

import com.desafiosoftplan.backend.model.Usuario;

public class UsuarioDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String login;

	private String senha;
	
	public Usuario toObj() {
		return new Usuario(login,senha);
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
	
}
