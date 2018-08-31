package com.luanrubensf.challenge.service;

import com.luanrubensf.challenge.model.Role;
import com.luanrubensf.challenge.model.User;
import com.luanrubensf.challenge.repository.RoleRepository;
import com.luanrubensf.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User entity, String role) {
        validate(entity);

        entity.setRole(getRole(role));
        entity.setPassword(encryptPassword(entity.getPassword()));

        return userRepository.save(entity);
    }

    private String encryptPassword(String pass) {
        return passwordEncoder.encode(pass);
    }

    private Role getRole(String role) {
        Objects.requireNonNull(role, "É necessário informar a role do usuário");
        return roleRepository.findRoleByNameIs(role);
    }

    private void validate(User entity) {

    }
}
