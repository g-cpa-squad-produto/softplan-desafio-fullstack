/**
 * 
 */
package br.com.desafiofullstack.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

	@ManyToOne
	@JoinColumn(name = "isn_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "isn_processo")
	private Processo processo;

	@Column(name = "descricao_parecer")
	@NotNull
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

}
