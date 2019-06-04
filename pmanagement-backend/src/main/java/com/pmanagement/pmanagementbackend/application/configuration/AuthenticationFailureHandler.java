package com.pmanagement.pmanagementbackend.application.configuration;

import com.pmanagement.pmanagementbackend.domain.entity.User;
import com.pmanagement.pmanagementbackend.domain.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Handler for authentication failure
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 4, 2019
 */
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UserRepository userRepository;

    /**
     * {@inheritDoc }
     *
     * @param request
     * @param response
     * @param exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            String username = (String) request.getParameter("username");

            final User user = this.userRepository.findByUsername(username).get();

            if (user != null) {
                user.registerFailedLogin();
                this.userRepository.saveAndFlush(user);
            }
        }
        
        throw exception;
    }
}