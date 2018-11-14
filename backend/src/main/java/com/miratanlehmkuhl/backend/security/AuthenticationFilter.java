package com.miratanlehmkuhl.backend.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.miratanlehmkuhl.backend.model.User;

import io.jsonwebtoken.Jwts;

@WebFilter
@ServletComponentScan
public class AuthenticationFilter extends GenericFilterBean {

	static final String SECRET = "MySecret";
	static final String HEADER_STRING = "Authorization";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		User user = null;

		try {
			Authentication authentication = null;
			HttpServletRequest req2 = (HttpServletRequest) req;
			String token = req2.getHeader(HEADER_STRING);
			if (token != null) {
				String user2 = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
				authentication = new UsernamePasswordAuthenticationToken(user2, null, Collections.emptyList());
			} else {
				authentication = null;
			}

			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			HttpServletResponse response = ((HttpServletResponse) res);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().flush();
			response.getWriter().close();

			res = response;
		} finally {
			chain.doFilter(req, res);
		}
	}


}
