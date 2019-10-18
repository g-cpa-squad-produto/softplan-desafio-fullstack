package com.desafiosoftplan.backend.model.dto;

import java.io.Serializable;
import java.util.List;

import com.desafiosoftplan.backend.model.Role;
import com.desafiosoftplan.backend.model.Usuario;

public class UsuarioRespostaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private List<Role> roles;
	
	public UsuarioRespostaDTO(String login, List<Role> roles) {
		this.login = login;
		this.roles = roles;
	}
	
	public static UsuarioRespostaDTO toDTO(Usuario usuario) {
		return new UsuarioRespostaDTO(usuario.getLogin(), usuario.getRoles());
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
