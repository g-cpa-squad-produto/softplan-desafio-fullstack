package br.com.danilopaixao.ws.user;

import br.com.danilopaixao.ws.core.BaseEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2572139086998939238L;

	private String name;
	
	private String login;
	
}
