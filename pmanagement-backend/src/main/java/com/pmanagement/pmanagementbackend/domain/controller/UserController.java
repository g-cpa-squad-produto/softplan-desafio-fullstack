package com.pmanagement.pmanagementbackend.domain.controller;

import com.pmanagement.pmanagementbackend.domain.entity.User;
import com.pmanagement.pmanagementbackend.domain.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for the {@link User}
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Listing all {@link User}
     * 
     * @return all the system {@link User}
     */
    @GetMapping("/all")
    public List<User> listAllUsers() {
        return this.userService.listUsers();
    }
}