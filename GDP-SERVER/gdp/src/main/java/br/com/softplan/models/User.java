package br.com.softplan.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "USERS")
public @Data class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_id")
	@SequenceGenerator(sequenceName = "seq_user_id", allocationSize = 1, name = "seq_user_id")
	private Long id;

	@Column(nullable = false, unique = true)
	private String login;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = true)
	private String name;
	
	@Column(nullable = true, name="last_name")
	private String lastName;
	
    @Enumerated(EnumType.STRING)
	private ProfileTypes profile;



}
