package br.com.luizgustavo.processevaluation.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.luizgustavo.processevaluation.security.util.JwtUtil;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	private UserDetailsService userDetailsService;
	private JwtUtil jwtUtil;

	public AuthorizationFilter(AuthenticationManager manager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
		super(manager);
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			authenticate(token);
		}

		chain.doFilter(request, response);
	}

	private void authenticate(String token) {
		if (!jwtUtil.isValidToken(token)) {
			return;
		}

		String login = jwtUtil.getLoginByToken(token);
		UserDetails user = userDetailsService.loadUserByUsername(login);
		UsernamePasswordAuthenticationToken authentication =
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
