package com.agfgerador.autenticacao.domain;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;

@Entity
@Table(name = "agf_permissao",schema="agfpb_autenticacao",
uniqueConstraints = @UniqueConstraint(columnNames = { "componente_id","perfil_id" }) )
public class Permissao extends ObjetoPadrao{
	private static final long serialVersionUID = 1L;
	
	@Basic
	private Boolean ativo;
	@Basic
	private Boolean atalho;
	@Basic
	private Boolean excluir;
	@Basic
	private Boolean somenteLeitura;
	@Basic
	private Boolean controle1;
	@Basic
	private Boolean controle2;
	@Basic
	private Boolean controle3;
	@Basic
	private Boolean controle4;
	@Basic
	private Boolean controle5;
	
	@ManyToOne  
	@JoinColumn(name="perfil_id") 
	private Perfil perfil;
	
	@ManyToOne  
	@JoinColumn(name="componente_id") 
	private Componente componente;
	

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean isAtalho() {
		return atalho;
	}

	public void setAtalho(Boolean atalho) {
		this.atalho = atalho;
	}

	public Boolean isExcluir() {
		return excluir;
	}

	public void setExcluir(Boolean excluir) {
		this.excluir = excluir;
	}

	public Boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(Boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}

	public Boolean isControle1() {
		return controle1;
	}

	public void setControle1(Boolean controle1) {
		this.controle1 = controle1;
	}

	public Boolean isControle2() {
		return controle2;
	}

	public void setControle2(Boolean controle2) {
		this.controle2 = controle2;
	}

	public Boolean isControle3() {
		return controle3;
	}

	public void setControle3(Boolean controle3) {
		this.controle3 = controle3;
	}

	public Boolean isControle4() {
		return controle4;
	}

	public void setControle4(Boolean controle4) {
		this.controle4 = controle4;
	}

	public Boolean isControle5() {
		return controle5;
	}

	public void setControle5(Boolean controle5) {
		this.controle5 = controle5;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
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
