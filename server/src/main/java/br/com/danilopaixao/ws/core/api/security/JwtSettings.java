package br.com.danilopaixao.ws.core.api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class JwtSettings {

    @Value("${security.jwt.tokenSigningKey}")
    private String tokenSigningKey;

    @Value("${security.jwt.tokenIssuer}")
    private String tokenIssuer;

    @Value("${security.jwt.tokenExpirationTime}")
    private Long tokenExpirationTime;

}
