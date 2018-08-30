package br.com.danilopaixao.ws.legal.advice;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.danilopaixao.ws.process.Process;
import br.com.danilopaixao.ws.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="legal_advices")
public class LegalAdvice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4835910182046216415L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "description")
	private String description;
	
	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by")
	private User userCreatedBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finished_by")
	private User userFinishedBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "responsable_for")
	private User userResponsableFor;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_process", referencedColumnName="id", nullable = false, updatable = true, insertable = true)
	private Process process;
	
	
}
