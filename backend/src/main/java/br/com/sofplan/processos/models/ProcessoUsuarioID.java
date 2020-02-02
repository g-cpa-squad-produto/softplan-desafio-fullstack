package br.com.sofplan.processos.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ProcessoUsuarioID implements Serializable {

	private static final long serialVersionUID = -1532466155133910416L;

	@ManyToOne
	@JoinColumn(name = "processo_id")
	private Processo processo;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario responsavel;

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(processo, responsavel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ProcessoUsuarioID)) {
			return false;
		}
		ProcessoUsuarioID other = (ProcessoUsuarioID) obj;
		return Objects.equals(processo, other.processo) && Objects.equals(responsavel, other.responsavel);
	}

}
