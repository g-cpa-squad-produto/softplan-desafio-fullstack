package com.softplan.backend.resource;

import com.softplan.backend.entity.Process;
import com.softplan.backend.entity.User;
import com.softplan.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.findAllUsers(pageable));
    }

    @PostMapping(value = "/")
    public ResponseEntity<User> newUser(@Valid @RequestBody User user) throws Exception {
        return ResponseEntity.ok(this.userService.newUser(user));
    }

    @PutMapping(value = "/")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(this.userService.updateUser(user));
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<?> deleteUser(@RequestBody User user) throws Exception {
        this.userService.deleteUser(user.getId());
        return ResponseEntity.noContent().header("id", user.getId().toString()).build();
    }

}