package com.pmanagement.pmanagementbackend.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

/**
 * The service to manage the tokens
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
public class TokenService {

    static final String SECRET = "theSecret";
    static final String HEADER_STRING = "Authorization";
    static final long EXPIRATION_HOURS = 5;

    /**
     * Generate the token and attach to header
     *
     * @param response
     * @param username
     */
    public static void addAuthentication(HttpServletResponse response, String username) {
        try {
            final Algorithm algorithm = Algorithm.HMAC256(SECRET);

            final Date expiresDate = Date.from(
                    LocalDateTime.now().plusHours(5).
                            atZone(ZoneId.systemDefault()).
                            toInstant());

            final String token = JWT.create()
                    .withClaim("username", username)
                    .withClaim("createdAt", Calendar.getInstance().getTime())
                    .withExpiresAt(expiresDate)
                    .sign(algorithm);

            response.addHeader(HEADER_STRING, token);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TokenService.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(TokenService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Restore the token
     *
     * @param request
     * @return
     */
    public static Authentication getAuthentication(HttpServletRequest request) {
        try {
            final String token = request.getHeader(HEADER_STRING);

            final Algorithm algorithm = Algorithm.HMAC256(SECRET);
            final JWTVerifier verifier = JWT.require(algorithm).build();

            //decrypt the token
            final DecodedJWT jwt = verifier.verify(token);
            final String user = jwt.getClaim("username").asString();

            if (!StringUtils.isEmpty(user)) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        } catch (JWTVerificationException ex) {
            Logger.getLogger(TokenService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TokenService.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(TokenService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
