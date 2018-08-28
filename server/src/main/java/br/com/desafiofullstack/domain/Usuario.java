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

	@Column(name = "usuario_ativo")
	private boolean usuarioAtivo;

	@OneToMany(cascade = { CascadeType.MERGE })
	private List<Permissao> permissoes;

	public Usuario() {
		super();
	}

	public Usuario(Long isnUsuario, @NotNull String nomeUsuario, @NotNull String sobrenomeUsuario,
			@NotNull String senhaUsuario, boolean usuarioAtivo, List<Permissao> permissoes) {
		super();
		this.isnUsuario = isnUsuario;
		this.nomeUsuario = nomeUsuario;
		this.sobrenomeUsuario = sobrenomeUsuario;
		this.senhaUsuario = senhaUsuario;
		this.usuarioAtivo = usuarioAtivo;
		this.permissoes = permissoes;
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

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public boolean isUsuarioAtivo() {
		return usuarioAtivo;
	}

	public void setUsuarioAtivo(boolean usuarioAtivo) {
		this.usuarioAtivo = usuarioAtivo;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isnUsuario == null) ? 0 : isnUsuario.hashCode());
		result = prime * result + ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
		result = prime * result + ((permissoes == null) ? 0 : permissoes.hashCode());
		result = prime * result + ((senhaUsuario == null) ? 0 : senhaUsuario.hashCode());
		result = prime * result + ((sobrenomeUsuario == null) ? 0 : sobrenomeUsuario.hashCode());
		result = prime * result + (usuarioAtivo ? 1231 : 1237);
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
		Usuario other = (Usuario) obj;
		if (isnUsuario == null) {
			if (other.isnUsuario != null)
				return false;
		} else if (!isnUsuario.equals(other.isnUsuario))
			return false;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		if (permissoes == null) {
			if (other.permissoes != null)
				return false;
		} else if (!permissoes.equals(other.permissoes))
			return false;
		if (senhaUsuario == null) {
			if (other.senhaUsuario != null)
				return false;
		} else if (!senhaUsuario.equals(other.senhaUsuario))
			return false;
		if (sobrenomeUsuario == null) {
			if (other.sobrenomeUsuario != null)
				return false;
		} else if (!sobrenomeUsuario.equals(other.sobrenomeUsuario))
			return false;
		if (usuarioAtivo != other.usuarioAtivo)
			return false;
		return true;
	}

}
