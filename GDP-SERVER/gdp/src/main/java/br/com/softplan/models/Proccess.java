/* <p> NAME OF THIS CLASS IS Proccess NOT TO CUDDLE WITH Process OF java.lang.Process </p>**/
package br.com.softplan.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PROCESS") 
public @Data class Proccess {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_process_id")
	@SequenceGenerator(sequenceName = "seq_process_id", allocationSize = 1, name = "seq_process_id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="code")
	private String code;
	
	@Enumerated(EnumType.STRING)
	private StatusProcess status;
	
	@OneToMany(cascade = CascadeType.ALL,  orphanRemoval = true )
	private List<Feedback> feedbacks;
	

	
}

