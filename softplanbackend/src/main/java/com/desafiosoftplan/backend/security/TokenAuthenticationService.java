package com.desafiosoftplan.backend.security;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.desafiosoftplan.backend.model.Role;
import com.desafiosoftplan.backend.model.Usuario;
import com.desafiosoftplan.backend.service.UsuarioService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.bytebuddy.asm.Advice.This;
import springfox.documentation.spring.web.json.Json;

public class TokenAuthenticationService {
	
	@Autowired
	private static ImplementsUserDetailsService detailsService;
	
	// EXPIRATION_TIME = 10 dias
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "desafioFullStackSoftplan@#$1234";
	static final String TOKEN_PREFIX = "Desafio";
	static final String HEADER_STRING = "Authorization";
    public static final String CLAIM_EMAIL = "email";

	    public String getToken(HttpServletRequest request) {
	        String desafioToken = request.getHeader("Authorization");

	        if (StringUtils.hasText(desafioToken) && desafioToken.startsWith("Desafio ")) {
	            return desafioToken.substring(7, desafioToken.length());
	        }
	        return null;
	    }

	    public boolean validarToken(String token) {
	        try {
	            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
	            return true;
	        } catch (Exception e) {
	            throw new RuntimeException("Expired or invalid JWT token");
	        }
	    }

	    public Authentication getAuthentication(String token) {
	        UserDetails userDetails = getDetailsService().loadUserByUsername("admin");// this.detailsService.loadUserByUsername(getEmail(token));
	        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	    }

	    public String getEmail(String token) {
	        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().get("name", String.class);
	    }

	    public static Authentication getAuthentication(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
	        String token = request.getHeader(HEADER_STRING);
	        if (token != null) {
	            // faz parse do token
	            String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
	            if (user != null) {
	            	user = user.replaceAll("authorities", "roles").replaceAll("authority", "nomeRole").replaceAll("password", "senha").replaceAll("username", "login");
	            	Usuario credentials = new ObjectMapper().readValue(user, Usuario.class);
	            	
	                return new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getSenha(), credentials.getAuthorities());
	            }
	        }
	        return null;
	    }

	    public static void addAuthentication(HttpServletResponse response, Authentication auth) throws JsonProcessingException {
	    	ObjectMapper objectMapper = new ObjectMapper();
	        String JWT = Jwts.builder().setSubject(objectMapper.writeValueAsString(auth.getPrincipal()))
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
	        System.out.println(JWT);
	        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	    }

		public static ImplementsUserDetailsService getDetailsService() {
			return detailsService;
		}

		public static void setDetailsService(ImplementsUserDetailsService detailsService) {
			TokenAuthenticationService.detailsService = detailsService;
		}
	
}