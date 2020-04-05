package com.softplan.processesapi.application.controllers.publiccontrollers;

import com.softplan.processesapi.infrastructure.responsestatus.ResourceNotFoundException;
import com.softplan.processesapi.infrastructure.responsestatus.WrongCredentialsException;
import com.softplan.processesapi.domain.user.admin.models.Admin;
import com.softplan.processesapi.domain.user.admin.services.ICreateUserService;
import com.softplan.processesapi.domain.user.admin.services.IGetUserService;
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
    public User register(@RequestBody Admin admin) {
        return this.createUserService.post(admin);
    }

    @PostMapping("/login")
    public User login(HttpServletResponse response, @RequestBody Admin admin) throws ResourceNotFoundException,
            WrongCredentialsException {

        if (admin.getPassword() == null || admin.getPassword().isEmpty() || admin.getEmail() == null ||
                admin.getEmail().isEmpty()) {
            throw new WrongCredentialsException("E-mail and password are required fields!");
        }

        User user = getUserService.getByEmail(admin.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Admin not found with email " + admin.getEmail()));

        if (!user.getPassword().equals(admin.getPassword())) {
            throw new WrongCredentialsException("Wrong password!");
        }

        response.addHeader("Authorization", "token");

        return user;
    }
}
