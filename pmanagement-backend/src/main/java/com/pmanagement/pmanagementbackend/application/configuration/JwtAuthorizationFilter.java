package com.pmanagement.pmanagementbackend.application.configuration;

import com.pmanagement.pmanagementbackend.domain.service.TokenService;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * The filter to get the authorization
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    /**
     * Constructor
     * 
     * @param authenticationManager the bean to submit authentication
     */
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final String token = request.getHeader(ApplicationConstants.HEADER_AUTHENTICATION);
//        final String token = this.getCookeiToken(request.getCookies());

        if (StringUtils.isNotBlank(token)) {
            SecurityContextHolder.getContext().setAuthentication(this.getAuthentication(token));
        }

        filterChain.doFilter(request, response);
    }
    
    /**
     * Search for the token
     * 
     * @param cookies to search
     * @return the token
     */
    private String getCookeiToken(final Cookie[] cookies) {
        for (final Cookie cookie : cookies) {
            if (ApplicationConstants.HEADER_AUTHENTICATION.equals(cookie.getValue())) {
                return cookie.getValue();
            }
        }
        
        return null;
    }

    /**
     * Process the token
     *
     * @param token to process
     * @return the {@link UsernamePasswordAuthenticationToken} found
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        if (StringUtils.isNotEmpty(token)) {
            final String username = TokenService.getAuthentication(token);

            if (StringUtils.isNotEmpty(username)) {
                return new UsernamePasswordAuthenticationToken(username, null, null);
            }
        }

        return null;
    }
}