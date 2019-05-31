package br.com.softplan.marcusvoltolim.model;

import br.com.softplan.marcusvoltolim.enums.SituacaoParecer;
import br.com.softplan.marcusvoltolim.model.support.AbstractEntityLongId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Parecer extends AbstractEntityLongId {
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private String conclusao;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SituacaoParecer situacao;
	
	@Transient
	private String responsavel;
	
	@PostLoad
	private void init() {
		responsavel = getLastModifiedBy();
	}
	
	@Transient
	public boolean isPendente() {
		return situacao == SituacaoParecer.PENDENTE;
	}
	
	
}
