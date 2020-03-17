package br.com.luizgustavo.processevaluation.security.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.luizgustavo.processevaluation.model.User;
import br.com.luizgustavo.processevaluation.security.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager manager;
	private JwtUtil jwtUtil;

	public AuthenticationFilter(AuthenticationManager manager, JwtUtil jwtUtil) {
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/**", "POST"));
		this.manager = manager;
		this.jwtUtil = jwtUtil;
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			Credentials credenciais = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					credenciais.getUsername(), credenciais.getPassword());
			return manager.authenticate(authentication);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		User user = (User) auth.getPrincipal();
		String token = jwtUtil.generateToken(user);
		response.addHeader("Authorization", token);
	}

	@NoArgsConstructor
	@AllArgsConstructor
	public static class Credentials {

		@Getter @Setter
		private String username;
		@Getter @Setter
		private String password;
		
	}
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			response.setStatus(401);
			response.setContentType("application/json");
			response.getWriter().append(json(exception.getMessage()));
		}

		private String json(String message) {
			long date = new Date().getTime();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("timestamp", date);
			jsonObject.put("status", 401);
			jsonObject.put("error", "Não autorizado");
			jsonObject.put("mensagemUsuario", "Login e/ou senha inválido(s)");
			jsonArray.put(jsonObject);
			return jsonArray.toString();
		}
	}
}

