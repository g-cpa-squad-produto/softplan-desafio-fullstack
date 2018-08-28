/**
 * 
 */
package br.com.desafiofullstack.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Delano Jr
 *
 */
@Entity
@Table(name = "tb_processo_usuario")
public class ProcessoUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "isn_processo_usuario")
	private Long isnProcessoUsuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "isn_usuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "isn_processo")
	private Processo processo;

	@Column(name = "descricao_parecer")
	private String descricaoParecer;

	public Long getIsnProcessoUsuario() {
		return isnProcessoUsuario;
	}

	public void setIsnProcessoUsuario(Long isnProcessoUsuario) {
		this.isnProcessoUsuario = isnProcessoUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public String getDescricaoParecer() {
		return descricaoParecer;
	}

	public void setDescricaoParecer(String descricaoParecer) {
		this.descricaoParecer = descricaoParecer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoParecer == null) ? 0 : descricaoParecer.hashCode());
		result = prime * result + ((isnProcessoUsuario == null) ? 0 : isnProcessoUsuario.hashCode());
		result = prime * result + ((processo == null) ? 0 : processo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessoUsuario other = (ProcessoUsuario) obj;
		if (descricaoParecer == null) {
			if (other.descricaoParecer != null)
				return false;
		} else if (!descricaoParecer.equals(other.descricaoParecer))
			return false;
		if (isnProcessoUsuario == null) {
			if (other.isnProcessoUsuario != null)
				return false;
		} else if (!isnProcessoUsuario.equals(other.isnProcessoUsuario))
			return false;
		if (processo == null) {
			if (other.processo != null)
				return false;
		} else if (!processo.equals(other.processo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
