package com.agfgerador.compartilhado.domain;

import java.io.Serializable;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@MappedSuperclass
public abstract class ObjetoPadraoSemId implements Comparator<Object>, Serializable  {

	private static final long serialVersionUID = 1L;
	@Column(columnDefinition="serial")
	@Generated(GenerationTime.INSERT)
	private Long id;
	
	@Version
	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public abstract String toLog();

}
