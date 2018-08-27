package br.com.danilopaixao.ws.process;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.danilopaixao.ws.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="processes")
public class Process implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4835910182046216415L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "summary")
	private String summary;
	
	@Column(name = "description")
	private String description;
	
	//@JsonIgnore
	//@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name = "created_by")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", nullable = false, referencedColumnName = "id")
	private User userCreatedBy;
	
	//@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name = "finished_by")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finished_by", nullable = true, referencedColumnName = "id")
	private User userFinishedBy;
	
	
}
