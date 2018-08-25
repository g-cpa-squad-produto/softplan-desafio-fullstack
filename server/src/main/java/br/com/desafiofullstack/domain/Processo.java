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

	@OneToMany(cascade = { CascadeType.MERGE }, mappedBy = "processo")
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

}
