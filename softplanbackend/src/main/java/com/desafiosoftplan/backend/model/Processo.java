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
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.desafiosoftplan.backend.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="processo")
@Table(schema="public", name="processo")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(schema="public", name="processo_id_seq", sequenceName = "\"public.processo_id_seq\"", 
initialValue = 1, allocationSize = 1)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Processo implements Serializable{

	private static final long serialVersionUID = 3276742767073552581L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "processo_id_seq")
    @Column(name = "id", nullable = false)  
	private Long id;

	@NotBlank
	@JsonProperty
	@Column(name="descricao")
	private String descricao;
	
	@JsonProperty
	@Column(name="parecer")
	private String parecer;
	
	@JsonProperty
	@Column(name="usuario_parecer")
	private String usuarioParecer;
	
//	@JsonProperty
//	@ManyToOne
//	@JoinColumn(name = "status", referencedColumnName = "id", nullable = true, updatable = true)
//	private Situacao status;
	
	@JsonProperty
	@Column(name="status")
	Status status;
	
	public Processo() {
		// TODO Auto-generated constructor stub
	}
	
	public Processo(Long id, @NotBlank String descricao, String parecer, String usuarioParecer, Status status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.parecer = parecer;
		this.usuarioParecer = usuarioParecer;
		this.status = status;
	}

	
	public Processo(Long id, @NotBlank String descricao, String parecer, String usuarioParecer) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.parecer = parecer;
		this.usuarioParecer = usuarioParecer;
	}
	
	public Processo(Long id, @NotBlank String descricao, String parecer) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.parecer = parecer;
	}
	
	public Processo(Long id, @NotBlank String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public Processo(Long id) {
		super();
		this.id = id;
	}
	
	public Processo(@NotBlank String descricao) {
		super();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

//	public Situacao getStatus() {
//		return status;
//	}
//
//	public void setStatus(Situacao status) {
//		this.status = status;
//	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public String getUsuarioParecer() {
		return usuarioParecer;
	}

	public void setUsuarioParecer(String usuarioParecer) {
		this.usuarioParecer = usuarioParecer;
	}

	@Override
	public String toString() {
		return "Processo [id=" + id + ", descricao=" + descricao + ", parecer=" + parecer + ", usuarioParecer="
				+ usuarioParecer + ", status=" + status + "]";
	}

}
