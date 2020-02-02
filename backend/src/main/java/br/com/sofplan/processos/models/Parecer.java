package br.com.sofplan.processos.models;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.sofplan.processos.enums.Situacao;

@Entity
@Table(name = "tb_parecer")
public class Parecer implements Serializable {

	private static final long serialVersionUID = 6673733971477194415L;

	@Id
	private Long id;
	
	private String texto;

	@MapsId
	@OneToOne
	private Processo processo;
	
	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@ManyToOne
	@JoinColumn(name = "criado_por")
	private Usuario criadoPor;
	
	private OffsetDateTime dataCriacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Usuario getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}

	public OffsetDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(OffsetDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
}
