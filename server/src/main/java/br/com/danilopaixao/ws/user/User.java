package br.com.danilopaixao.ws.user;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.damilopaixao.ws.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseEntity<Long> {

	private static final long serialVersionUID = 6661098931687966006L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "login")
	private String login;
	
}
