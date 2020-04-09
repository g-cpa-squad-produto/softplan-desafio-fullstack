package com.softplan.processmanagerapi.services;

import com.softplan.processmanagerapi.payload.ApiResponse;
import com.softplan.processmanagerapi.payload.SignUpRequest;
import com.softplan.processmanagerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ApiResponse validateSignUpRequest(SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ApiResponse(false, "Nome de usuário já existe na aplicação!");
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ApiResponse(false, "Email já existe na aplicação!");
        }
        return new ApiResponse(true, "Usuário registrado com sucesso");
    }
}
