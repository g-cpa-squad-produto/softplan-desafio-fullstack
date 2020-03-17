package br.com.luizgustavo.processevaluation.security.util;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.luizgustavo.processevaluation.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private static final String KEY = "process-evaluation-secret-key";
	private static final long EXPIRATION = 3600000; //1 hora
	
	public String generateToken(User user) {
		return Jwts.builder().setIssuer("API Process Evaluation")
				.setSubject(user.getUsername())
				.claim("authorities", user.getAuthorities()
						.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.claim("userName", user.getName())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, KEY)
				.compact();
	}

	public boolean isValidToken(String token) {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(KEY)
					.parseClaimsJws(token)
					.getBody();
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();			
			Date now = new Date(System.currentTimeMillis());
			return (username != null && expirationDate != null && now.before(expirationDate));
		} catch (Exception ex) {
			return false;
		}
	}

	public String getLoginByToken(String token) {
		try {
			return Jwts.parser()
					.setSigningKey(KEY)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
		} catch (Exception ex) {
			return null;
		}
	}

}
