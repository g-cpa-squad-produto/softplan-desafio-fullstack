package br.com.luizgustavo.processevaluation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"idReport"})
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReport;
	@Column(length = 256)
	private String text;
	@ManyToOne @JoinColumn(name = "idauthor", referencedColumnName = "iduser")
	private User author;
	@ManyToOne @JoinColumn(name = "idprocess", referencedColumnName = "idprocess")
    private Process process;
}
