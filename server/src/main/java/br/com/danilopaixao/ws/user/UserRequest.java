package br.com.danilopaixao.ws.user;

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
	private ProfileEnum profile;
	private UserStatusEnum status;
	
	@JsonCreator
	public UserRequest(
			@JsonProperty("name") final String name,
			@JsonProperty("login") final String login,
			@JsonProperty("password") final String password,
			@JsonProperty("profile") final ProfileEnum profile,
			@JsonProperty("status") final UserStatusEnum status) {
		this.name = name;		
		this.login = login;
		this.password = password;
		this.profile = profile;
		this.status = status;
	}
}
