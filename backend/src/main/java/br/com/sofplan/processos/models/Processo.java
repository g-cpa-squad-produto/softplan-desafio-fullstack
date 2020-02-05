package br.com.sofplan.processos.models;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_processo")
public class Processo implements Serializable {

	private static final long serialVersionUID = 3879094426751402854L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, unique = true)
	private String titulo;
	
	private String descricao;
	
	@OneToOne(mappedBy = "processo", cascade = CascadeType.ALL, orphanRemoval = true)
	private Parecer parecer;
	
	@ManyToOne
	@JoinColumn(name = "criado_por")
	private Usuario criadoPor;
	
	@OneToMany(mappedBy = "id.processo")
	private Set<ProcessoUsuario> responsaveis;
	
	private OffsetDateTime dataCriacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Usuario criadoPor) {
		this.criadoPor = criadoPor;
	}

	public OffsetDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(OffsetDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Parecer getParecer() {
		return parecer;
	}

	public void setParecer(Parecer parecer) {
		this.parecer = parecer;
	}

	public Set<ProcessoUsuario> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(Set<ProcessoUsuario> responsaveis) {
		this.responsaveis = responsaveis;
	}
	
}
