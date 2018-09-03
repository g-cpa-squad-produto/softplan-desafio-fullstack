package br.com.softplan.desafiojava.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Processo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String numero;
	private String dados;
	private String pendente = "N";
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "processo_usuario", 
		joinColumns = @JoinColumn(name = "processo_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Usuario> finalizadores;

	public Processo() {
		super();
	}

	@Transient
	public boolean isPendente() {
		return "S".equals(pendente);
	}

	public void setPendente(boolean pendente) {
		this.pendente = (pendente ? "S" : "N");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public List<Usuario> getFinalizadores() {
		return finalizadores;
	}

	public void setFinalizadores(List<Usuario> finalizadores) {
		this.finalizadores = finalizadores;
	}

}
