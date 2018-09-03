package br.com.renancelso.service.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Renan Celso
 */
@Entity
@Table(name="processo")
public class Processo implements Serializable {

	private static final long serialVersionUID = -3753006732463425171L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
		
	@Lob
	@Column(name = "descricaoProcesso")
	private String descricaoProcesso;	
		
	@ManyToOne
	@JoinColumn(name = "usuarioCriador")
	private Usuario usuarioCriador;
	
	@Transient
	private List<UsuarioProcesso> usuariosParecer;
	
	/**
	 * S = SIM
	 * N = NAO
	 */
	@Column(name="parecerValido")
	private String parecerValido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoProcesso() {
		return descricaoProcesso;
	}

	public void setDescricaoProcesso(String descricaoProcesso) {
		this.descricaoProcesso = descricaoProcesso;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}	

	public List<UsuarioProcesso> getUsuariosParecer() {
		return usuariosParecer;
	}

	public void setUsuariosParecer(List<UsuarioProcesso> usuariosParecer) {
		this.usuariosParecer = usuariosParecer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	

	public String getParecerValido() {
		return parecerValido;
	}

	public void setParecerValido(String parecerValido) {
		this.parecerValido = parecerValido;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String getUsuariosAdicionadosParecer(){		
		StringBuilder str = new StringBuilder();	
		if(usuariosParecer != null && !usuariosParecer.isEmpty()){
			for (UsuarioProcesso usuarioProcesso : usuariosParecer) {
				str.append(usuarioProcesso.getUsuario().getLogin()).append("; ");
			}		
		}
		return str != null  ? str.toString() : "";	
	}	
	
	
			 
}
