package br.com.softplan.process.service;

import br.com.softplan.process.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public User getAuthentication() {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
