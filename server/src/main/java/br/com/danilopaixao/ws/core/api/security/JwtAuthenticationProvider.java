package br.com.danilopaixao.ws.core.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final JwtSettings settings;
	
	@Autowired
	public JwtAuthenticationProvider(final JwtSettings settings) {
		this.settings = settings;
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final AuthenticationToken authToken = (AuthenticationToken) authentication;
		final JwtTokenRaw rawToken = (JwtTokenRaw) authToken.getCredentials();

		final Jws<Claims> jwsClaims = rawToken.parse(settings.getTokenSigningKey());

		final String subject = jwsClaims.getBody().getSubject();

		return new AuthenticationToken(new UserLoggedDto(subject));
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return AuthenticationToken.class.isAssignableFrom(authentication);
	}
	
}