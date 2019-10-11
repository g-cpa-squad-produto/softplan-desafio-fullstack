package br.com.softplan.processmanagement.controllers;

import br.com.softplan.processmanagement.domain.UserAuthentication;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserType;
import br.com.softplan.processmanagement.security.ApiResponse;
import br.com.softplan.processmanagement.security.LoginRequest;
import br.com.softplan.processmanagement.security.SignUpRequest;
import br.com.softplan.processmanagement.security.jwt.JwtAuthenticationResponse;
import br.com.softplan.processmanagement.security.jwt.JwtTokenProvider;
import br.com.softplan.processmanagement.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsersService usersService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserAuthentication principal = (UserAuthentication) authentication.getPrincipal();
        UserType type = principal.getUser().getType();
        String token = jwtTokenProvider.generateToken(principal, type);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody SignUpRequest signUpRequest) throws Exception {
        logger.info("Registering signUpRequest: " + signUpRequest);
        if (usersService.verifyExistence(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }
        usersService.saveUserFromRequest(signUpRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/signin").build().toUri();
        return ResponseEntity.created(uri).body(new ApiResponse(true, "User registered successfully"));
    }

}
