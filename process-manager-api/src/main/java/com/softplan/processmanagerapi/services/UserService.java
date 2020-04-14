package com.softplan.processmanagerapi.services;

import com.softplan.processmanagerapi.excpetion.AppException;
import com.softplan.processmanagerapi.excpetion.BadRequestException;
import com.softplan.processmanagerapi.factory.UserFactory;
import com.softplan.processmanagerapi.models.Role;
import com.softplan.processmanagerapi.models.User;
import com.softplan.processmanagerapi.models.enums.RoleType;
import com.softplan.processmanagerapi.payload.ApiResponse;
import com.softplan.processmanagerapi.payload.SignUpRequest;
import com.softplan.processmanagerapi.payload.response.UserResponse;
import com.softplan.processmanagerapi.repository.RoleRepository;
import com.softplan.processmanagerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserFactory userFactory;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Boolean validateSignUpRequest(SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Nome de usuário já existe na aplicação!");
        }
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException( "Email já existe na aplicação!");
        }
        return true;
    }

    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(userFactory::getUserResponse).collect(Collectors.toList());
    }

    public User registerUser(SignUpRequest signUpRequest) {
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roleNames = new HashSet<>();
        for (RoleType roleName: signUpRequest.getRole()) {
            roleNames.add(roleRepository.findByName(roleName)
                    .orElseThrow(() -> new AppException("Papel do usuário não definido.")));
        }
        user.setRoles(roleNames);
        User result = userRepository.save(user);
        return result;
    }

}
