package com.desafiosoftplan.backend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.desafiosoftplan.backend.model.Usuario;
import com.desafiosoftplan.backend.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	protected LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		Usuario credentials =new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getUsername(), 
						credentials.getPassword(), 
						credentials.getRoles()
						)
				);
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		
		TokenAuthenticationService.addAuthentication(response, auth);
	}

}