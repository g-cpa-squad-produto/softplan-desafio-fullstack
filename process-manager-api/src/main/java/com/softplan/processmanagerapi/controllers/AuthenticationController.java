package com.softplan.processmanagerapi.controllers;

import com.softplan.processmanagerapi.excpetion.BadRequestException;
import com.softplan.processmanagerapi.models.enums.RoleType;
import com.softplan.processmanagerapi.payload.ApiResponse;
import com.softplan.processmanagerapi.payload.LoginRequest;
import com.softplan.processmanagerapi.payload.SignUpRequest;
import com.softplan.processmanagerapi.payload.response.RoleResponse;
import com.softplan.processmanagerapi.security.CurrentUser;
import com.softplan.processmanagerapi.security.UserPrincipal;
import com.softplan.processmanagerapi.services.AuthenticationService;
import com.softplan.processmanagerapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.authenticateUser(loginRequest));
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(RoleType.getAllRoles());
    }
}
