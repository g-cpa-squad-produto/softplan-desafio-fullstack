package br.com.luizgustavo.processevaluation.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;

		resp.setHeader("Access-Control-Allow-Credentials", "true");
		
		resp.setHeader("Access-Control-Allow-Origin", "*");
        
		resp.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE, PATCH");
        
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Authorization, Content-Type, Accept, X-CSRF-TOKEN, Cache-Control, "
        		+ "DNT, X-CustomHeader, Keep-Alive, User-Agent, If-Modified-Since, Content-Range, Range");
        
        resp.setHeader("Access-Control-Expose-Headers", "Origin, X-Requested-With, Authorization, Content-Type, Accept, X-CSRF-TOKEN, Cache-Control, "
        		+ "DNT, X-CustomHeader, Keep-Alive, User-Agent, If-Modified-Since, Content-Range, Range");
        
        resp.setHeader("Access-Control-Max-Age", "3600");
		
		if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
			resp.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(request, response);
		}
	}

}
