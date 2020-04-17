package com.softplan.backend.service;

import com.softplan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserService {

    @Autowired
    private UserRepository userRepository;

    public com.softplan.backend.entity.User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User currentUser = (User) securityContext.getAuthentication().getPrincipal();

        com.softplan.backend.entity.User user = userRepository.findByUsername(currentUser.getUsername());
        return user;
    }
}


