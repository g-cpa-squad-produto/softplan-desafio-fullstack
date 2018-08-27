package br.com.danilopaixao.ws.core.api.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class AccessTokenDto {

	private final JwtToken accessToken;

	@JsonCreator
	public AccessTokenDto(
			@JsonProperty("accessToken") final JwtToken accessToken
			) {
		this.accessToken = accessToken;
	}
}
