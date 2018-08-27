package br.com.danilopaixao.ws.core.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

class JwtTokenRaw implements JwtToken {
            
	private static final Logger log = LoggerFactory.getLogger(JwtTokenRaw.class);
    private final String token;
    
    public JwtTokenRaw(final String token) {
        this.token = token;
    }

    /**
     * Parses and validates JWT Token signature.
     * 
     * @throws BadCredentialsException
     * @throws JwtTokenExpiredException
     * 
     */
    public Jws<Claims> parse(final String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            log.error("Invalid JWT Token {}", ex);
            throw new RuntimeException();
        } catch (ExpiredJwtException expiredEx) {
            log.info("JWT Token is expired {}", expiredEx);
            throw new RuntimeException();
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}