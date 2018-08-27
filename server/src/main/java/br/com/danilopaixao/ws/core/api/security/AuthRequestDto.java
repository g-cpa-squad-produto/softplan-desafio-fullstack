package br.com.danilopaixao.ws.core.api.security;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequestDto implements ApiRequest {
	
	@Min(3)
	@NotNull(message = "expected valid value.")
	private final String username;

	@Min(3)
	@NotNull(message = "expected valid value.")
	private final String password;
	
	@JsonCreator
	public AuthRequestDto(
			@JsonProperty("username") final String username,
			@JsonProperty("password") final String password
			) {
		this.username = username;
		this.password = password;
	}

}
