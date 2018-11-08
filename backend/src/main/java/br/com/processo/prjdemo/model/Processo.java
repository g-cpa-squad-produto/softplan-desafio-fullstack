package br.com.processo.prjdemo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Guilherme Sena
 * Classe que mapeia o PROCESSO
 */
@Entity
public class Processo implements Serializable {
	
	//CONSTANTES 
	
	private static final long serialVersionUID = 1L;
	
	//ATRIBUTOS

	@Id
	@SequenceGenerator(name="proc_generator",sequenceName="proc_seq",allocationSize=10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proc_generator")
	private Long id;
	
	@Column
	@NotBlank
	@NotNull
	private String descricao;
	
	@OneToMany(mappedBy = "processo")
	private Set<Parecer> lstParecer= new HashSet<Parecer>();
	
	
	
	@Column
	private Date dataCriacao;
	
	@OneToMany
    @JoinColumn(name = "idProcesso")
	private Set<Usuario> lstUsuParecer= new HashSet<Usuario>();
	
	//CONSTRUTORES
	
	public Processo() {
		super();
	}
	
	//GETS AND SETS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Parecer> getLstParecer() {
		return lstParecer;
	}

	public void setLstParecer(Set<Parecer> lstParecer) {
		this.lstParecer = lstParecer;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Set<Usuario> getLstUsuParecer() {
		return lstUsuParecer;
	}

	public void setLstUsuParecer(Set<Usuario> lstUsuParecer) {
		this.lstUsuParecer = lstUsuParecer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		Processo other = (Processo) obj;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Processo [id=" + id + ", descricao=" + descricao
				+ ", dataCriacao=" + dataCriacao + "]";
	}
	
}
