package com.softplan.processesapi.infrastructure.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String auth = request.getHeader("Authorization");
        String uri = request.getRequestURI();

        if ((uri == null || !uri.contains("auth")) && (auth == null || auth.isEmpty())) {
            response.setStatus(401);
            return false;
        }

        return true;
    }
}
