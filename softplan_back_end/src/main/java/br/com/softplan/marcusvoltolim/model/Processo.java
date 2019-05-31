package br.com.softplan.marcusvoltolim.model;

import br.com.softplan.marcusvoltolim.model.support.AbstractEntityLongId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.CollectionUtils;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Processo extends AbstractEntityLongId {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(nullable = false)
	private String titulo;
	
	@NotBlank
	@Column(nullable = false)
	private String descricao;
	
	@OneToMany
	List<Parecer> pareceres;
	
	@Transient
	private boolean isPendente() {
		return CollectionUtils.isEmpty(pareceres) || pareceres.stream().anyMatch(Parecer::isPendente);
	}
	
	@Transient
	public String getResponsavel() {
		return !isPendente() ? getLastModifiedBy() : "";
	}
	
	@Transient
	public Date getDataFinalizacao() {
		return !isPendente() ? getDataModificacao() : null;
	}
	
}
