package com.agfgerador.autenticacao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;
@Entity
@Table(name="agf_aviso",schema="agfpb_autenticacao")
public class Aviso extends ObjetoPadrao {

	private static final long serialVersionUID = 1L;

	@Column(length = 100,nullable=false)
	private String titulo;
	
	@Column(length = 5000,nullable=false)
	private String descricao;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private TipoSistema sistema;
	
	@Column(length = 100,nullable=true)
	private String nomedoc;
	
	@Column(length = 1000,nullable=true)
	private String urldoc;
	
	@Column(nullable=true)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	@OneToOne
	private Perfil perfil;
	
	@Override
	public String toString() {
		return "Aviso [titulo=" + titulo + ", descricao=" + descricao
				+ ", sistema=" + sistema + ", nomedoc=" + nomedoc + ", urldoc="
				+ urldoc + ", tipoUsuario=" + tipoUsuario + ", perfil="
				+ perfil + "]";
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoSistema getSistema() {
		return sistema;
	}

	public void setSistema(TipoSistema sistema) {
		this.sistema = sistema;
	}

	public String getNomedoc() {
		return nomedoc;
	}

	public void setNomedoc(String nomedoc) {
		this.nomedoc = nomedoc;
	}

	public String getUrldoc() {
		return urldoc;
	}

	public void setUrldoc(String urldoc) {
		this.urldoc = urldoc;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
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
