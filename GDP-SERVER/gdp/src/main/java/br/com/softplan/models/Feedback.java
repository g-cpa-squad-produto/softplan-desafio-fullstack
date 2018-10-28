package br.com.softplan.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "FEEDBACK")
public @Data class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_feedback_id")
	@SequenceGenerator(sequenceName = "seq_feedback_id", allocationSize = 1, name = "seq_feedback_id")
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	private User user;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Proccess process;
	
	@Column(name="description")
	private String description;
	
}