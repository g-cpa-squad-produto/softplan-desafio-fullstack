package com.softplan.processmanagerapi.controllers;

import com.softplan.processmanagerapi.excpetion.BadRequestException;
import com.softplan.processmanagerapi.payload.ApiResponse;
import com.softplan.processmanagerapi.payload.SignUpRequest;
import com.softplan.processmanagerapi.security.CurrentUser;
import com.softplan.processmanagerapi.security.UserPrincipal;
import com.softplan.processmanagerapi.services.AuthenticationService;
import com.softplan.processmanagerapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        userService.validateSignUpRequest(signUpRequest);
        return ResponseEntity.created(userService.registerUser(signUpRequest)).body(new ApiResponse(true,"Usuário registrado com sucesso"));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }
}
