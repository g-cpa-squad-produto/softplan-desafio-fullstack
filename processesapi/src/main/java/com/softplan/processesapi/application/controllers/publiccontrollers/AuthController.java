package com.softplan.processesapi.application.controllers.publiccontrollers;

import com.softplan.processesapi.domain.user.admin.models.Admin;
import com.softplan.processesapi.domain.user.admin.services.ICreateUserService;
import com.softplan.processesapi.domain.user.admin.services.IGetUserService;
import com.softplan.processesapi.domain.user.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public User post(@RequestBody Admin user) {
        return this.createUserService.post(user);
    }
}
