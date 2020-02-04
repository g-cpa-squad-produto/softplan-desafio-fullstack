package br.com.sofplan.processos.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.sofplan.processos.dto.v1.UsuarioDTO;
import br.com.sofplan.processos.services.UsuarioService;

@Component
public class JwtTokenProvider {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_URL = "/api/v1/auth/login";

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds = 3600000; // 1h
    
    private final UsuarioService usuarioService;
    
    public JwtTokenProvider(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UsuarioDTO usuario) {
        return JWT.create()
                .withSubject(usuario.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + this.validityInMilliseconds))
                .withClaim("role", usuario.getPerfil().getAuthority())
                .sign(Algorithm.HMAC512(this.secretKey));
    }

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = null;

        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse do token.
            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(this.secretKey))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));

            String email = jwt.getSubject();
            
            // consulta usuário pelo e-mail
            UsuarioDTO usuarioDTO = usuarioService.findByEmail(email);

			authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDTO, null,
					List.of(usuarioDTO.getPerfil()));
        }

        return authenticationToken;
    }

	public long getValidityInMilliseconds() {
		return validityInMilliseconds;
	}

}
