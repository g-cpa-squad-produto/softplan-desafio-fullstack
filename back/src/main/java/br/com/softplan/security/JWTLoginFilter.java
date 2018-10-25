package br.com.softplan.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.softplan.usuario.modelos.Usuario;

/**
 * @author emanuel
 *
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	protected JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException {

		Usuario credentials = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getLogin(),
				credentials.getSenha(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication auth) throws IOException {
		TokenAuthenticationService.addAuthentication(response, auth.getName());
	}
}