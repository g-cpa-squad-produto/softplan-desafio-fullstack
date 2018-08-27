package br.com.danilopaixao.ws.user;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse implements Serializable{

	private static final long serialVersionUID = 2572139086998939238L;

	private Long id;
	private String name;
	private String login;
	private ProfileEnum profile;
	private UserStatusEnum status;
	
	@JsonCreator
	public UserResponse(
			@JsonProperty("id") final Long id,
			@JsonProperty("name") final String name,
			@JsonProperty("login") final String login,
			@JsonProperty("profile") final ProfileEnum profile,
			@JsonProperty("status") final UserStatusEnum status) {
		this.id = id;
		this.name = name;		
		this.login = login;
		this.profile = profile;
		this.status = status;
	}
	
}
