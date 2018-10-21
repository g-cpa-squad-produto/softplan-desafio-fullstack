package br.com.softplan.parecer.modelos;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.softplan.processo.modelos.Processo;

@Entity(name = "Parecer")
@Table(name = "parecer")
public class Parecer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column
	private String nome;

	@Column
	private Timestamp dataHora = new Timestamp(new Date().getTime());

	@Column
	private Timestamp dataHoraDeletado;

	@Column
	private Boolean ativo = Boolean.TRUE;

	@Column
	private Boolean deletado = Boolean.FALSE;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_processo", nullable = true)
	private Processo processo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Timestamp getDataHora() {
		return dataHora;
	}

	public void setDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	public Timestamp getDataHoraDeletado() {
		return dataHoraDeletado;
	}

	public void setDataHoraDeletado(Timestamp dataHoraDeletado) {
		this.dataHoraDeletado = dataHoraDeletado;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getDeletado() {
		return deletado;
	}

	public void setDeletado(Boolean deletado) {
		this.deletado = deletado;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	@Override
	public String toString() {
		return "Parecer [id=" + id + ", nome=" + nome + ", dataHora=" + dataHora + ", dataHoraDeletado="
				+ dataHoraDeletado + ", ativo=" + ativo + ", deletado=" + deletado + ", processo=" + processo + "]";
	}

}
