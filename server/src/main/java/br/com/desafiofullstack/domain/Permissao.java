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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Delano Jr
 *
 */
@Entity
@Table(name = "tb_permissao")
public class Permissao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "isn_permissao")
	private Long isnPermissao;

	@Column(name = "descricao_permissao")
	@NotNull
	private String descricaoPermissao;

	public Permissao() {
		super();
	}

	public Permissao(Long isnPermissao, String descricaoPermissao) {
		super();
		this.isnPermissao = isnPermissao;
		this.descricaoPermissao = descricaoPermissao;
	}

	public Long getIsnPermissao() {
		return isnPermissao;
	}

	public void setIsnPermissao(Long isnPermissao) {
		this.isnPermissao = isnPermissao;
	}

	public String getDescricaoPermissao() {
		return descricaoPermissao;
	}

	public void setDescricaoPermissao(String descricaoPermissao) {
		this.descricaoPermissao = descricaoPermissao;
	}

}
