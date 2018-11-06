package com.process.processmanagerapi.controller;

import com.process.processmanagerapi.entity.User;
import com.process.processmanagerapi.service.UserService;
import com.process.processmanagerapi.vo.CreateUserVO;
import com.process.processmanagerapi.vo.EditUserVO;
import com.process.processmanagerapi.vo.ViewAllUsersVO;
import com.process.processmanagerapi.vo.ViewUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "User APIs")
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/create")
    @ApiOperation(value = "Assign Identity Provider for a given client")
    public User createUser(final @Valid @RequestBody CreateUserVO createUserVO) {
        return userService.createUser(createUserVO);
    }

    @PostMapping(path = "/users")
    @ApiOperation(value = "Get all users on database")
    public List<User> getAllUser(final @Valid @RequestBody ViewAllUsersVO viewAllUsersVO) {
        return userService.getAllUsers(viewAllUsersVO);
    }

    @PostMapping(path = "/user")
    @ApiOperation(value = "Get a specific users on database by user name")
    public User getUserByName(final @Valid @RequestBody ViewUserVO viewUserVO) {
        return userService.getUserByUserName(viewUserVO);
    }

    @PatchMapping(path = "/editUser")
    @ApiOperation(value = "Edit a specific users on database by user name")
    public User editUser(final @Valid @RequestBody EditUserVO editUserVO) {
        return userService.editUserByUserName(editUserVO);
    }

}
