package com.softplan.processmanagerapi.controllers;

import com.softplan.processmanagerapi.excpetion.AppException;
import com.softplan.processmanagerapi.excpetion.BadRequestException;
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
import com.softplan.processmanagerapi.services.AuthenticationService;
import com.softplan.processmanagerapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        ApiResponse response = userService.validateSignUpRequest(signUpRequest);
        if(!response.getSuccess()) {
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        URI location = authenticationService.registerUser(signUpRequest);
        return ResponseEntity.created(location).body(response);
    }
}
