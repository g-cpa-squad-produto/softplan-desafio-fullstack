package br.com.fwom.api.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import static br.com.fwom.api.configuration.TokenAuthentication.EXPIRATION_TIME;
import static br.com.fwom.api.configuration.TokenAuthentication.SECRET;

@Component
public class JwtTokenUtil implements Serializable {

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails user) {
        return doGenerateToken(user);
    }

    private String doGenerateToken(UserDetails user) {

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("scopes", Arrays.asList(user.getAuthorities()));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://www.fwom.com.br")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME*1000))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getEmailFromToken(token);
        return ( username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}