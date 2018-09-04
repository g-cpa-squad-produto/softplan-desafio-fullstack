package br.com.softplan.desafiojava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import br.com.softplan.desafiojava.entity.enums.TipoUsuario;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, nullable=false, length=10)
	private String matricula;
	
	@NotNull
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo = TipoUsuario.TRIADOR;
	
	public Usuario() {
		super();
	}
	
	public Usuario(Long id, String matricula, String nome, TipoUsuario tipo) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
}
