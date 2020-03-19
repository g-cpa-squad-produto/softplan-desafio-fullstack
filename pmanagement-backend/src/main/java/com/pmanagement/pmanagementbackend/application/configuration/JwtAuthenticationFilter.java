package com.pmanagement.pmanagementbackend.application.configuration;

import com.google.gson.Gson;
import com.pmanagement.pmanagementbackend.domain.dto.UserDTO;
import com.pmanagement.pmanagementbackend.domain.entity.User;
import com.pmanagement.pmanagementbackend.domain.service.TokenService;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The filter to process the login and create the token
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * {@inheritDoc }
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    /**
     * {@inheritDoc }
     *
     * @param request
     * @param response
     * @param filterChain
     * @param authentication
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) throws IOException, ServletException {
        final User user = (User) authentication.getPrincipal();
        final String token = TokenService.createAuthentication(user);

        final UserDTO userDTO = new UserDTO();
        userDTO.toDto(user);
        userDTO.setToken(token);

        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(userDTO));
        out.flush();

        response.setHeader(ApplicationConstants.HEADER_AUTHORIZATION, token);

        getSuccessHandler().onAuthenticationSuccess(request, response, authentication);
    }
}
