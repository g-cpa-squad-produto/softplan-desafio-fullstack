package com.process.processmanagerapi.controller;

import com.process.processmanagerapi.entity.User;
import com.process.processmanagerapi.service.UserService;
import com.process.processmanagerapi.vo.CreateUserVO;
import com.process.processmanagerapi.vo.EditUserVO;
import com.process.processmanagerapi.vo.ViewAllUsersVO;
import com.process.processmanagerapi.vo.ViewUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User createUser(final @RequestBody CreateUserVO createUserVO) {
        return userService.createUser(createUserVO);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getAllUser(final @RequestBody ViewAllUsersVO viewAllUsersVO) {
        return userService.getAllUsers(viewAllUsersVO);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUserByName(final @RequestBody ViewUserVO viewUserVO) {
        return userService.getUserByUserName(viewUserVO);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User editUser(final @RequestBody EditUserVO editUserVO) {
        return userService.editUserByUserName(editUserVO);
    }

}
