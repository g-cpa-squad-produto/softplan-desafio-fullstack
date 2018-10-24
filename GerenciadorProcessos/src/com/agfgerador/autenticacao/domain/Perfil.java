package com.agfgerador.autenticacao.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;




import org.hibernate.annotations.Type;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;

@Entity
@Table(name ="agf_perfil",schema="agfpb_autenticacao")
public class Perfil extends ObjetoPadrao {
	private static final long serialVersionUID = 1L;
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=100)
	private String nome;
	@Type(type="com.agfgerador.compartilhado.util.UpperCase")
	@Column(length=3000)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TipoPerfil tipo; 
	
	@Enumerated(EnumType.STRING)
	private TipoSistema sistema;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoPerfil getTipo() {
		return tipo;
	}

	public void setTipo(TipoPerfil tipo) {
		this.tipo = tipo;
	}

	public TipoSistema getSistema() {
		return sistema;
	}

	public void setSistema(TipoSistema sistema) {
		this.sistema = sistema;
	}

	@Override
	public int compare(Object o1, Object o2) {

		return 0;
	}

	@Override
	public String toLog() {

		return null;
	}

	public String toString()
	{
		return nome;
	}
}
