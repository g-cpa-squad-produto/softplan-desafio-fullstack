package br.com.ramonbarros.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.ramonbarros.enuns.StatusProcessoEnum;

@Entity
@Table(name = "PROCESSO")
public class Processo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@OneToMany(mappedBy="processo", cascade=CascadeType.ALL)
	private List<Parecer> listaParecer = new ArrayList<>();
	
	@Column(name = "STATUS")
	private Integer status;

	public Processo() {
	}

	public Processo(Long id, StatusProcessoEnum status) {
		super();
		this.id = id;
		this.status = (status == null) ? null : status.getCod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Parecer> getListaParecer() {
		return listaParecer;
	}

	public void setListaParecer(List<Parecer> listaParecer) {
		this.listaParecer = listaParecer;
	}

	public StatusProcessoEnum getStatus() {
		return StatusProcessoEnum.toEnum(status);
	}

	public void setStatus(StatusProcessoEnum status) {
		this.status = status.getCod();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
}
