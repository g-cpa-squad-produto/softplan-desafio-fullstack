package com.softplan.thiagobernardo.usuario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.softplan.thiagobernardo.util.TipoUsuario;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7849030271870110123L;

	@Id
    @GeneratedValue(generator = "usuario_generator")
    @SequenceGenerator(name = "usuario_generator", sequenceName = "usuario_sequence", initialValue = 1)
	private Long id;
	
	@NotBlank
	@Size(min = 2, max = 100)
	@Column(columnDefinition = "text")
    private String nome;
	
	@NotBlank
	@Size(min = 5, max = 20)
	@Column(columnDefinition = "text", unique = true)
    private String login;
	
	@NotBlank
	@Size(min = 5, max = 12)
	@Column(columnDefinition = "text")
    private String senha;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	@Column(columnDefinition = "text")
	private String token;
	
	public Usuario() {
		super();
	}
	
	public Usuario(@NotBlank @Size(min = 2, max = 100) String nome, @NotBlank @Size(min = 6, max = 20) String login,
			@NotBlank @Size(min = 6, max = 12) String senha, @NotNull TipoUsuario tipoUsuario) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}