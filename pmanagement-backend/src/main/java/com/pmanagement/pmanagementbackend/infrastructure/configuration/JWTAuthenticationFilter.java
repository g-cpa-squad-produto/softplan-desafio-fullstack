package com.pmanagement.pmanagementbackend.infrastructure.configuration;

import com.pmanagement.pmanagementbackend.domain.service.TokenService;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * The filter to get the authentication
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

    /**
     * {@inheritDoc }
     * 
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        Authentication authentication = TokenService.getAuthentication((HttpServletRequest) request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}