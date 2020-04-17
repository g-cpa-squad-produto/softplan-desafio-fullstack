package com.softplan.backend.resource;

import com.softplan.backend.entity.User;
import com.softplan.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/user/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.findAllUsers(pageable));
    }

    @GetMapping("/finalizadores")
    public ResponseEntity<List<User>> getFinUsers() {
        return ResponseEntity.ok(userService.findFinUsers());
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<User> getAnUser(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping(value = "/user/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<User> newUser(@Valid @RequestBody User user) throws Exception {
        return ResponseEntity.ok(this.userService.newUser(user));
    }

    @PutMapping(value = "/user/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(this.userService.updateUser(user));
    }

    @DeleteMapping(value = "/user/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteUser(@PathVariable Long id) throws Exception {
        this.userService.deleteUser(id);
    }
}
