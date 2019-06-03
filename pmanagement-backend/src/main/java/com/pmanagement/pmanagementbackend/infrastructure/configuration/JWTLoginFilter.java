package com.pmanagement.pmanagementbackend.infrastructure.configuration;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmanagement.pmanagementbackend.domain.entity.User;
import com.pmanagement.pmanagementbackend.domain.service.TokenService;

/**
 * The filter to process the login and create the token
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * Constructor
     *
     * @param url to filter
     * @param authManager the authentication manager
     */
    protected JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        super.setAuthenticationManager(authManager);
    }

    /**
     * {@inheritDoc }
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        User credentials = new ObjectMapper()
                .readValue(request.getInputStream(), User.class);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    /**
     * {@inheritDoc }
     *
     * @param request
     * @param response
     * @param filterChain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication auth)
            throws IOException, ServletException {

        TokenService.addAuthentication(response, auth.getName());
    }
}
