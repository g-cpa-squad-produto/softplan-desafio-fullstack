package com.processos.luiz.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	@Id
    private String nomeRole;
	
	public String getNomeRole() {
		return nomeRole;
	}
	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}
	@JsonIgnore
	@Override
	public String getAuthority() {
		return this.nomeRole;
	}

}
