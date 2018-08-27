package br.com.danilopaixao.ws.core.api.security;

import java.time.ZonedDateTime;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenAccessFactory {

	public static final String CLAIMS_PRIVILEGES = "privileges";

	@Autowired
	private JwtSettings settings;

	public JwtToken createAccessJwtToken(final UserLoggedDto userLoggedDto) {
		Preconditions.checkArgument(StringUtils.isNotBlank(userLoggedDto.getUsername()), "Cannot create JWT Token without username");

		final String tokenSigningKey = settings.getTokenSigningKey();
		final String tokenIssuer = settings.getTokenIssuer();
		final Long tokenExpiration = settings.getTokenExpirationTime();

		final ZonedDateTime currentTime = ZonedDateTime.now();
		final Date issueAt = Date.from(currentTime.toInstant());
		final Date expiration = Date.from(currentTime.plusMinutes(tokenExpiration).toInstant());

		final Claims claims = Jwts.claims().setSubject(userLoggedDto.getUsername());

		claims.put(CLAIMS_PRIVILEGES, userLoggedDto.getAuthorities());

		String token = Jwts.builder().setClaims(claims).setIssuer(tokenIssuer).setIssuedAt(issueAt)
				.setExpiration(expiration).signWith(SignatureAlgorithm.HS512, tokenSigningKey).compact();

		return new JwtTokenAccess(token, claims);
	}


	public JwtToken createRawJwtToken(final String token) {
		return new JwtTokenRaw(token);
	}

}
