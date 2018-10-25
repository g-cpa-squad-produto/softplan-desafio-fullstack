package br.com.softplan.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.softplan.usuario.modelos.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author emanuel
 *
 */
public class TokenAuthenticationService {

	// EXPIRATION_TIME = 10 dias
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "matmdtokenkeysecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	static void addAuthentication(HttpServletResponse response, String username) throws IOException {
		Optional<Usuario> usuarioDoBanco = null;
		try {
			usuarioDoBanco = new SecurityDao().obterUsuaruio(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String JWT = null;
		if (usuarioDoBanco.isPresent()) {
			Usuario usuario = usuarioDoBanco.get();
			JWT = Jwts.builder().setSubject(usuario.getLogin() + "/" + usuario.getPerfil())
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, SECRET).compact();

			response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		}
		PrintWriter out = response.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(JWT);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonString);
		out.flush();

	}

	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// faz parse do token
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}
		return null;
	}
}