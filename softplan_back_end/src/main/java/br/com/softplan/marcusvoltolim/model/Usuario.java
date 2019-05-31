package br.com.softplan.marcusvoltolim.model;

import br.com.softplan.marcusvoltolim.enums.Permissao;
import br.com.softplan.marcusvoltolim.model.support.AbstractEntityLongId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Usuario extends AbstractEntityLongId {
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "text", updatable = false, unique = true)
	private String login;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "text")
	private String senha;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "text")
	private String nomeCompleto;
	
	@NotBlank
	@Column(nullable = false, columnDefinition = "text")
	private String email;
	
	@NotNull
	@Column(nullable = false, updatable = false, length = 13)
	@Enumerated(EnumType.STRING)
	private Permissao permissao;
	
}
