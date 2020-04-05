package com.softplan.processesapi.application.controllers.publiccontrollers;

import com.softplan.processesapi.infrastructure.responsestatus.ResourceNotFoundException;
import com.softplan.processesapi.infrastructure.responsestatus.WrongCredentialsException;
import com.softplan.processesapi.domain.user.subdomains.admin.services.ICreateUserService;
import com.softplan.processesapi.domain.user.subdomains.admin.services.IGetUserService;
import com.softplan.processesapi.domain.user.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private ICreateUserService createUserService;
    private IGetUserService getUserService;

    public AuthController(ICreateUserService createUserService, IGetUserService getUserService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return this.createUserService.createUser(user);
    }

    @PostMapping("/login")
    public User login(HttpServletResponse response, @RequestBody User user) throws ResourceNotFoundException,
            WrongCredentialsException {

        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getEmail() == null ||
                user.getEmail().isEmpty()) {
            throw new WrongCredentialsException("E-mail and password are required fields!");
        }

        User finalUser = user;
        user = getUserService.getByEmail(user.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Admin not found with email " + finalUser.getEmail()));

        if (!user.getPassword().equals(user.getPassword())) {
            throw new WrongCredentialsException("Wrong password!");
        }

        response.addHeader("Authorization", "token");

        return user;
    }
}
