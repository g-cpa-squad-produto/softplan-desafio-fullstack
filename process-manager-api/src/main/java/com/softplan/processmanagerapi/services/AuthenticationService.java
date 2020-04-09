package com.softplan.processmanagerapi.services;

import com.softplan.processmanagerapi.excpetion.AppException;
import com.softplan.processmanagerapi.models.Role;
import com.softplan.processmanagerapi.models.User;
import com.softplan.processmanagerapi.models.enums.RoleName;
import com.softplan.processmanagerapi.payload.ApiResponse;
import com.softplan.processmanagerapi.payload.JwtAuthenticationResponse;
import com.softplan.processmanagerapi.payload.LoginRequest;
import com.softplan.processmanagerapi.payload.SignUpRequest;
import com.softplan.processmanagerapi.repository.RoleRepository;
import com.softplan.processmanagerapi.repository.UserRepository;
import com.softplan.processmanagerapi.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new JwtAuthenticationResponse(tokenProvider.generateToken(authentication));
    }

    public URI registerUser(SignUpRequest signUpRequest) {
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roleNames = new HashSet<>();
        for (RoleName roleName: signUpRequest.getRole()) {
            roleNames.add(roleRepository.findByName(roleName)
                    .orElseThrow(() -> new AppException("Papel do usuário não definido.")));
        }

        user.setRoles(roleNames);

        User result = userRepository.save(user);

        return ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
    }
}
