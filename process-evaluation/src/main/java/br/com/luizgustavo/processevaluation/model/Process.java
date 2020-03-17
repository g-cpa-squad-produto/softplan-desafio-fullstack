package br.com.luizgustavo.processevaluation.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idProcess"})
public class Process implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "idprocess")
	private Long idProcess;
	@Column(length = 20)
	private String title;
	@Column(length = 256)
	private String description;
	@ManyToOne @JoinColumn(name = "idauthor", referencedColumnName = "iduser")
	private User author;
	@OneToMany(mappedBy = "process")
	private Set<Report> reports = new HashSet<Report>();
	
}
