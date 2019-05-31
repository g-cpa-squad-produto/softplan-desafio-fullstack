package br.com.softplan.marcusvoltolim.model;

import br.com.softplan.marcusvoltolim.enums.SituacaoParecer;
import br.com.softplan.marcusvoltolim.model.support.AbstractEntityLongId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(exclude = "processo")
@Entity
public class Parecer extends AbstractEntityLongId {
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private String descricao;
	
	@Column
	private String conclusao;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SituacaoParecer situacao;
	
	@Column(nullable = false)
	private String responsavel;
	
	@OneToOne
	@JsonIgnore
	private Processo processo;
	
	@Transient
	public boolean isPendente() {
		return situacao == SituacaoParecer.PENDENTE;
	}
	
	public Parecer() {
	
	}
	
	public Parecer(String responsavel, Processo processo) {
		this.responsavel = responsavel;
		this.processo = processo;
		this.situacao = SituacaoParecer.PENDENTE;
	}
	
}
