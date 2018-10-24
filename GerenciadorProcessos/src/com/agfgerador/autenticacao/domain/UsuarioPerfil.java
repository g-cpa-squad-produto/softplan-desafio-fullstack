package com.agfgerador.autenticacao.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;

@Entity
@Table(name = "agf_usuarioperfil",schema="agfpb_autenticacao",
uniqueConstraints = @UniqueConstraint(columnNames = { "perfil_id","usuario_id" }) )
public class UsuarioPerfil extends ObjetoPadrao {
	
	private static final long serialVersionUID = 1L;
	
	@Basic
	private Boolean ativo;
	@Basic
	private Boolean administrador;
	
	@ManyToOne  
	@JoinColumn(name="perfil_id") 
	private Perfil perfil;
	@ManyToOne  
	@JoinColumn(name="usuario_id") 
	private Usuario usuario;
	
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
