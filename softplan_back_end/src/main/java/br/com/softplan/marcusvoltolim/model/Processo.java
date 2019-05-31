package br.com.softplan.marcusvoltolim.model;

import br.com.softplan.marcusvoltolim.model.support.AbstractEntityLongId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.CollectionUtils;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	@JsonIgnore
	List<Parecer> pareceres = new ArrayList<>();
	
	@Transient
	private boolean isPendente() {
		return CollectionUtils.isEmpty(pareceres) || pareceres.stream().anyMatch(Parecer::isPendente);
	}
	
	@Transient
	public Date getDataFinalizacao() {
		return !isPendente() ? getDataModificacao() : null;
	}
	
	@Transient
	public List<String> getFinalizadores() {
		return !CollectionUtils.isEmpty(pareceres) ? pareceres.stream().map(Parecer::getResponsavel)
				.collect(Collectors.toList()) : new ArrayList<>();
	}
	
}
