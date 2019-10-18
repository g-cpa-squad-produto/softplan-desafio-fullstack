package com.desafiosoftplan.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity(name="role")
@Table(schema="public", name="role")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8523874846891043820L;

	@Id
	@JsonProperty
	@Column(name="nome_role")
	private String nomeRole;

	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

	public Role() {
		// TODO Auto-generated constructor stub
	}
	
	public Role(String nomeRole) {
		this.nomeRole=nomeRole;
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@JsonIgnore
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.nomeRole;
	}
	
	
}
