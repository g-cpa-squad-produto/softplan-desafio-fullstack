package br.com.softplan.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Parecer do usuário
 * @author Marco
 *
 */
@Data
@Entity
public class ProcessFeedback {

	@Id @GeneratedValue
	private Long id;

	private String feedback;
	
	/**Processo ao qual pertence o feedback*/
	@ManyToOne
	private Process process;

	/**Usuário atribuido como finalizador, responsável pelo perecer*/
	@ManyToOne
	private User finalizator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getFinalizator() {
		return finalizator;
	}

	public void setFinalizator(User finalizator) {
		this.finalizator = finalizator;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}
