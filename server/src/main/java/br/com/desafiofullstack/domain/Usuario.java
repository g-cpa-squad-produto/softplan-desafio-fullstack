/**
 * 
 */
package br.com.desafiofullstack.domain;

import java.io.Serializable;
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
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "isn_usuario")
	private Long isnUsuario;

	@Column(name = "nome_usuario")
	@NotNull
	private String nomeUsuario;

	@Column(name = "sobrenome_usuario")
	@NotNull
	private String sobrenomeUsuario;

	@Column(name = "senha_usuario")
	@NotNull
	private String senhaUsuario;

	@OneToMany(cascade = { CascadeType.MERGE })
	private List<Permissao> permissoes;

	public Usuario() {
		super();
	}

	public Usuario(Long isnUsuario, String nomeUsuario, String sobrenomeUsuario) {
		super();
		this.isnUsuario = isnUsuario;
		this.nomeUsuario = nomeUsuario;
		this.sobrenomeUsuario = sobrenomeUsuario;
	}

	public Long getIsnUsuario() {
		return isnUsuario;
	}

	public void setIsnUsuario(Long isnUsuario) {
		this.isnUsuario = isnUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSobrenomeUsuario() {
		return sobrenomeUsuario;
	}

	public void setSobrenomeUsuario(String sobrenomeUsuario) {
		this.sobrenomeUsuario = sobrenomeUsuario;
	}

}
