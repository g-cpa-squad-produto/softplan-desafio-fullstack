package com.pmanagement.pmanagementbackend.application.configuration;

import com.pmanagement.pmanagementbackend.domain.entity.User;
import com.pmanagement.pmanagementbackend.domain.repository.UserRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * Handler for authentication success
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 4, 2019
 */
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    /**
     * {@inheritDoc }
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final User userAuthenticated = (User) authentication.getPrincipal();
        
        final User user = this.userRepository.findByUsername(userAuthenticated.getUsername()).get();
        user.setLastAcess(LocalDateTime.now());
        user.setLoginAttemps(null);
        this.userRepository.saveAndFlush(user);
    }
}