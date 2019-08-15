package com.renantabaresmachado.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renantabaresmachado.dto.CredenciaisDTO;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
private AuthenticationManager authenticationManager;
    
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
    	setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CredenciaisDTO credenciais = new ObjectMapper()
	                .readValue(request.getInputStream(), CredenciaisDTO.class);
	
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credenciais.getEmail(), credenciais.getSenha(), new ArrayList<>());
	        
	        Authentication authentication = authenticationManager.authenticate(authToken);
	        return authentication;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication authentication) throws IOException, ServletException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> mapToken = new HashMap<>();
		
		UserSSecurity user = ((UserSSecurity) authentication.getPrincipal());
        String token = jwtUtil.generateToken(user.getUsername());
        
        mapToken.put("token", token);
        mapToken.put("authorities", user.getAuthorities().toString());
        
        //response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().write(mapper.writeValueAsString(mapToken));
        response.getWriter().flush();
        response.getWriter().close();
	}
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
        }
        
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
    }
	
	
}
