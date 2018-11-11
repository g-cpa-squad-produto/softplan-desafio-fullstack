package com.miratanlehmkuhl.backend.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miratanlehmkuhl.backend.dto.UserInfoDTO;
import com.miratanlehmkuhl.backend.dto.UserLoginDTO;
import com.miratanlehmkuhl.backend.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class BackendLoginFilter extends AbstractAuthenticationProcessingFilter {

	// EXPIRATION_TIME = 1 day
	static final long EXPIRATION_TIME = 86_000_000;
	static final String SECRET = "MySecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	protected BackendLoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
		try {
			UserLoginDTO user = new ObjectMapper().readValue(req.getInputStream(), UserLoginDTO.class);
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), Collections.emptyList()));
		} catch (Exception e) {
			throw new AuthenticationServiceException("");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authentication) throws IOException, ServletException {
		if (authentication.getPrincipal() instanceof User) {
			User user = (User) authentication.getPrincipal();

			String JWT = Jwts.builder().setSubject(user.getEmail())
							 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
							 .signWith(SignatureAlgorithm.HS512, SECRET).compact();

			res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
			res.getWriter().write(new ObjectMapper().writeValueAsString(new UserInfoDTO(JWT, user.getRoles().toString().replace("[", "").replace("]", ""))));
			res.getWriter().flush();
			res.getWriter().close();

			SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(user));
		}
	}

}
