package org.gradle.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="processo")
public class Processo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="descricao")
	private String descricao;
	@OneToMany
	private List<UsuarioProcesso> usuarioProcesso;
	
	public Processo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Processo(Long id, String descricao, List<UsuarioProcesso> usuarioProcesso) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.usuarioProcesso = usuarioProcesso;
	}
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
	public List<UsuarioProcesso> getUsuarioProcesso() {
		return usuarioProcesso;
	}
	public void setUsuarioProcesso(List<UsuarioProcesso> usuarioProcesso) {
		this.usuarioProcesso = usuarioProcesso;
	}
}
