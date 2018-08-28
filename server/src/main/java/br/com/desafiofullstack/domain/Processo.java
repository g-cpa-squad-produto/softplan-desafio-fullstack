/**
 * 
 */
package br.com.desafiofullstack.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Delano Jr
 *
 */
@Entity
@Table(name = "tb_processo")
public class Processo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "isn_processo")
	private Long isnProcesso;

	@Column(name = "descricao_prorcesso")
	@NotNull
	private String descricaoProcesso;

	@Column(name = "data_publicacao")
	private LocalDate dataPublicaocaoProcesso;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "processo", fetch = FetchType.EAGER)
	private List<ProcessoUsuario> processosUsuario;

	public Long getIsnProcesso() {
		return isnProcesso;
	}

	public void setIsnProcesso(Long isnProcesso) {
		this.isnProcesso = isnProcesso;
	}

	public String getDescricaoProcesso() {
		return descricaoProcesso;
	}

	public void setDescricaoProcesso(String descricaoProcesso) {
		this.descricaoProcesso = descricaoProcesso;
	}

	public LocalDate getDataPublicaocaoProcesso() {
		return dataPublicaocaoProcesso;
	}

	public void setDataPublicaocaoProcesso(LocalDate dataPublicaocaoProcesso) {
		this.dataPublicaocaoProcesso = dataPublicaocaoProcesso;
	}

	public List<ProcessoUsuario> getProcessosUsuario() {
		return processosUsuario;
	}

	public void setProcessosUsuario(List<ProcessoUsuario> processosUsuario) {
		this.processosUsuario = processosUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataPublicaocaoProcesso == null) ? 0 : dataPublicaocaoProcesso.hashCode());
		result = prime * result + ((descricaoProcesso == null) ? 0 : descricaoProcesso.hashCode());
		result = prime * result + ((isnProcesso == null) ? 0 : isnProcesso.hashCode());
		result = prime * result + ((processosUsuario == null) ? 0 : processosUsuario.hashCode());
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
		Processo other = (Processo) obj;
		if (dataPublicaocaoProcesso == null) {
			if (other.dataPublicaocaoProcesso != null)
				return false;
		} else if (!dataPublicaocaoProcesso.equals(other.dataPublicaocaoProcesso))
			return false;
		if (descricaoProcesso == null) {
			if (other.descricaoProcesso != null)
				return false;
		} else if (!descricaoProcesso.equals(other.descricaoProcesso))
			return false;
		if (isnProcesso == null) {
			if (other.isnProcesso != null)
				return false;
		} else if (!isnProcesso.equals(other.isnProcesso))
			return false;
		if (processosUsuario == null) {
			if (other.processosUsuario != null)
				return false;
		} else if (!processosUsuario.equals(other.processosUsuario))
			return false;
		return true;
	}

}
