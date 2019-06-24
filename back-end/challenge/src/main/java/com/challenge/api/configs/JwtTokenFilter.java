package com.challenge.api.configs;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;



public class JwtTokenFilter extends GenericFilterBean {

	private JwtTokenProvider jwtTokenProvider;
	
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
        throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
            SecurityContextHolder.getContext().setAuthentication(auth);
        }else if(jwtTokenProvider.validateToken(token)){
        	String msg = "{\"status\":401,\"error\":\"Unauthorized\",\""
        			+ "message\":\"Expired or invalid token\"}";
        	res.getWriter().write(msg);
        }
        
        filterChain.doFilter(req, res);
        
    }
}

