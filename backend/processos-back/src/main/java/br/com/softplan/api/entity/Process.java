package br.com.softplan.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * Processo
 * @author Marco
 *
 */
@Data
@Entity
public class Process {

	@Id @GeneratedValue
	private Long id;

	@NotBlank(message = "Descrição required")
	private String description;

	@NotBlank(message = "Number required") 
	private String number;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
