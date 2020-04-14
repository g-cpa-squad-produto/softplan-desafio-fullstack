package com.softplan.processmanagerapi.controllers;

import com.softplan.processmanagerapi.payload.ApiResponse;
import com.softplan.processmanagerapi.payload.SignUpRequest;
import com.softplan.processmanagerapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

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
