package br.com.softplan.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
public class Permissao implements Serializable {

	private static final long serialVersionUID = 7511764381347180796L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPermissao", length = 20, updatable = false)
	private Long id;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "incluir", nullable = false)
	private boolean incluir;
	
	@Column(name = "alterar", nullable = false)
	private boolean alterar;
	
	@Column(name = "excluir", nullable = false)
	private boolean excluir;
	
	@Column(name = "visualizar", nullable = false)
	private boolean visualizar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isIncluir() {
		return incluir;
	}
	public String getIncluirString() {
		return (incluir) ? "Sim" : "Não";
	}
	public void setIncluir(boolean incluir) {
		this.incluir = incluir;
	}
	public boolean isAlterar() {
		return alterar;
	}
	public String getAlterarString() {
		return (alterar) ? "Sim" : "Não";
	}
	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}
	public boolean isExcluir() {
		return excluir;
	}
	public String getExcluirString() {
		return (excluir) ? "Sim" : "Não";
	}
	public void setExcluir(boolean excluir) {
		this.excluir = excluir;
	}
	public boolean isVisualizar() {
		return visualizar;
	}
	public String getVisualizarString() {
		return (visualizar) ? "Sim" : "Não";
	}
	public void setVisualizar(boolean visualizar) {
		this.visualizar = visualizar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Permissao other = (Permissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PermissaoBean [id=" + id + ", nome=" + nome + ", incluir=" + incluir + ", alterar=" + alterar
				+ ", excluir=" + excluir + ", visualizar=" + visualizar + "]";
	}
}