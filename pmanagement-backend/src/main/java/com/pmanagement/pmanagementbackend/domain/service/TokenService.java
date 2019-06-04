package com.pmanagement.pmanagementbackend.domain.service;

import com.pmanagement.pmanagementbackend.application.configuration.ApplicationConstants;
import com.pmanagement.pmanagementbackend.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The service to manage the tokens
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
public class TokenService {
    
    private static final Logger log = LoggerFactory.getLogger(TokenService.class);

    /**
     * Generate the token and attach to header
     *
     * @param user to create the token
     */
    public static String createAuthentication(User user) {
        final byte[] signingKey = ApplicationConstants.SECRET.getBytes();

        final Date expiresDate = Date.from(
                LocalDateTime.now().plusHours(ApplicationConstants.EXPIRATION_HOURS).
                        atZone(ZoneId.systemDefault()).
                        toInstant());

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setSubject(user.getUsername())
                .setExpiration(expiresDate)
                .compact();
    }

    /**
     * Restore the token
     *
     * @param token
     * @return the {@link User#username} found
     */
    public static String getAuthentication(String token) {
        try {
            final byte[] signingKey = ApplicationConstants.SECRET.getBytes();
            final Jws<Claims> parsedToken = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token);

            return parsedToken
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException exception) {
            log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
        } catch (MalformedJwtException exception) {
            log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
        } catch (SignatureException exception) {
            log.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
        }
        
        return null;
    }
}