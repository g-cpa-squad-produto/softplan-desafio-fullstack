package br.com.danilopaixao.ws.core.api.security;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
final class JwtTokenAccess implements JwtToken {

	private final String rawToken;
	private final Claims claims;

	protected JwtTokenAccess(final String token, final Claims claims) {
		this.rawToken = token;
		this.claims = claims;
	}

	@Override
	public String getToken() {
		return this.rawToken;
	}

}