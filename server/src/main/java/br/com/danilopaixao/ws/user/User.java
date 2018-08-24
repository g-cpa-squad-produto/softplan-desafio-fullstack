package br.com.danilopaixao.ws.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import br.com.danilopaixao.ws.core.BaseEntity;
import lombok.Data;

@Data
@Entity
public class User {//extends BaseEntity<Long> {

	private static final long serialVersionUID = 6661098931687966006L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "login")
	private String login;
	
}
