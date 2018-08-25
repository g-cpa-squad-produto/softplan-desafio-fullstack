package br.com.danilopaixao.ws.user.api.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3733146230737994444L;
	
	private String name;
	private String login;
	private String password;
	
	@JsonCreator
	public UserRequest(
			@JsonProperty("name") final String name,
			@JsonProperty("login") final String login,
			@JsonProperty("password") final String password) {
		this.name = name;		
		this.login = login;
		this.password = password;
	}
}
