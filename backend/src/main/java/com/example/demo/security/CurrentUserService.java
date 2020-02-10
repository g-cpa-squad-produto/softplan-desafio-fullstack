package com.example.demo.security;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserService {

    @Autowired
    private UserRepository userRepository;

    public com.example.demo.entity.User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User currentUser = (User) securityContext.getAuthentication().getPrincipal();

        com.example.demo.entity.User user = userRepository.findByLogin(currentUser.getUsername()).get();
        return user;
    }
}
