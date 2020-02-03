package br.com.sofplan.processos.models;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_processo_usuario")
public class ProcessoUsuario implements Serializable {

	private static final long serialVersionUID = -7724545301024527448L;

	@EmbeddedId
	private ProcessoUsuarioID id;

	@ManyToOne
	@JoinColumn(name = "criado_por")
	private Usuario criadoPor;

	private OffsetDateTime dataCriacao;

	public ProcessoUsuario() {
		super();
	}

	public ProcessoUsuario(Processo processo, Usuario responsavel, Usuario criadoPor, OffsetDateTime dataCriacao) {
		this.id = new ProcessoUsuarioID(processo, responsavel);
		this.criadoPor = criadoPor;
		this.dataCriacao = dataCriacao;
	}

	public ProcessoUsuario(ProcessoUsuarioID id, Usuario criadoPor, OffsetDateTime dataCriacao) {
		this.id = id;
		this.criadoPor = criadoPor;
		this.dataCriacao = dataCriacao;
	}

	public ProcessoUsuarioID getId() {
		return id;
	}

	public void setId(ProcessoUsuarioID id) {
		this.id = id;
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
