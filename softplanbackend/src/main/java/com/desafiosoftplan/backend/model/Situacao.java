package com.desafiosoftplan.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="situacao")
@Table(schema="public", name="situacao")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(schema="public", name="situacao_id_seq", sequenceName = "\"public.situacao_id_seq\"", 
initialValue = 1, allocationSize = 1)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Situacao implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "situacao_id_seq")
	@JsonProperty
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT")  
	private Long id;
	
	@JsonProperty
	private String descricao;
	
	public Situacao() {
		// TODO Auto-generated constructor stub
	}
	
	public Situacao(String descricao) {
		this.descricao=descricao;
	}
	
	public Situacao(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
