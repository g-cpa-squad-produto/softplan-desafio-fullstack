package com.softplan.processmanagerapi.services;

import com.softplan.processmanagerapi.excpetion.AppException;
import com.softplan.processmanagerapi.models.Role;
import com.softplan.processmanagerapi.models.User;
import com.softplan.processmanagerapi.models.enums.RoleType;
import com.softplan.processmanagerapi.payload.JwtAuthenticationResponse;
import com.softplan.processmanagerapi.payload.LoginRequest;
import com.softplan.processmanagerapi.payload.SignUpRequest;
import com.softplan.processmanagerapi.repository.RoleRepository;
import com.softplan.processmanagerapi.repository.UserRepository;
import com.softplan.processmanagerapi.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
}
