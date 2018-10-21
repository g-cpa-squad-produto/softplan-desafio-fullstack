package com.softplan.thiagobernardo.processo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.softplan.thiagobernardo.usuario.entity.Usuario;
import com.softplan.thiagobernardo.util.ParecerStatus;

@Entity
@Table(name="Processo")
public class Processo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5361818186621806240L;

	@Id
    @GeneratedValue(generator = "processo_generator")
    @SequenceGenerator(name = "processo_generator", sequenceName = "processo_sequence", initialValue = 1)
	private Long id;
	
	@NotNull
	@Column(unique=true)
	private Integer numero;
	
	@NotBlank
	@Column(columnDefinition = "text")
    private String descricao;
	
	@NotNull
	@Enumerated
    private ParecerStatus statusParecer;
	
	@ManyToMany
	@JoinTable(name = "processo_usuario", 
	joinColumns = @JoinColumn(name = "processo_id"), 
	inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Usuario> usuariosPararecer;
	
	
	
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
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public List<Usuario> getUsuariosPararecer() {
		return usuariosPararecer;
	}
	public void setUsuariosPararecer(List<Usuario> usuariosPararecer) {
		this.usuariosPararecer = usuariosPararecer;
	}
	public ParecerStatus getStatusParecer() {
		return statusParecer;
	}
	public void setStatusParecer(ParecerStatus statusParecer) {
		this.statusParecer = statusParecer;
	}

}
