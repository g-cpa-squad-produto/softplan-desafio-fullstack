package com.desafiosoftplan.backend.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="usuario")
@Table(schema="public", name="usuario")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario implements UserDetails, Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@JsonProperty
	@Column(name="login")
	private String login;
	
	@JsonProperty
	@Column(name="nome_completo")
	private String nomeCompleto;
	
	@NotEmpty
	@JsonProperty
	@Column(name="senha")
	private String senha;
	
//	@JsonProperty
	@ManyToMany
	@JoinTable( 
	        name = "usuarios_roles", 
	        joinColumns = @JoinColumn(
	          name = "usuario_id", referencedColumnName = "login"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "role_id", referencedColumnName = "nome_role")) 
    private List<Role> roles;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(String login, String nomeCompleto,String senha) {
		this.login = login;
		this.nomeCompleto = nomeCompleto;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return this.senha;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return this.login;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", nomeCompleto=" + nomeCompleto + ", senha=" + senha + ", roles=" + roles
				+ "]";
	}

}