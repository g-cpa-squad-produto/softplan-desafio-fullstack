package br.com.danilopaixao.ws.core.api.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class AuthResponseDto extends ApiResource {

	private final String id;
	private final String username;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String token;
	
	@JsonCreator
	public AuthResponseDto(
			@JsonProperty("id") final String id,
			@JsonProperty("username") final String username,
			@JsonProperty("firstName") final String firstName,
			@JsonProperty("lastName") final String lastName,
			@JsonProperty("email") final String email,
			@JsonProperty("token") final String token
			) {
		this.username = username;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.token = token;
	}

}
