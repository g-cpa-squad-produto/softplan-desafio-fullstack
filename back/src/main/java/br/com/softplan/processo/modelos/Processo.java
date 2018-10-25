package br.com.softplan.processo.modelos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.softplan.usuario.modelos.Usuario;

/**
 * @author emanuel
 *
 */
@Entity(name = "Processo")
@Table(name = "processo")
public class Processo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column
	private String nome;

	@Column
	private Timestamp dataHora = new Timestamp(new Date().getTime());

	@Column
	private Timestamp dataHoraDeletado;

	@Column
	private Boolean finalizado = Boolean.FALSE;

	@Column
	private Boolean deletado = Boolean.FALSE;

	@NotNull
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_processo", joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = {
			@JoinColumn(name = "id_processo") })
	private List<Usuario> usuarios = new ArrayList<>();

	public Processo(Integer id) {
		this.id = id;
	}

	public Processo() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Timestamp getDataHora() {
		return dataHora;
	}

	public void setDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	public Timestamp getDataHoraDeletado() {
		return dataHoraDeletado;
	}

	public void setDataHoraDeletado(Timestamp dataHoraDeletado) {
		this.dataHoraDeletado = dataHoraDeletado;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Boolean getDeletado() {
		return deletado;
	}

	public void setDeletado(Boolean deletado) {
		this.deletado = deletado;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuario) {
		this.usuarios = usuario;
	}
}
