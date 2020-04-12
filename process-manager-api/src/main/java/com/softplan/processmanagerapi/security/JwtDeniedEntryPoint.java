package com.softplan.processmanagerapi.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtDeniedEntryPoint implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.error("Responding with unauthorized error. Message - {}", accessDeniedException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuário não autorizado para essa ação.");

    }
}