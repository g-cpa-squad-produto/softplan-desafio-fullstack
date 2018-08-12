package br.com.fwom.api.controllers;

import br.com.fwom.api.exceptions.ResourceNotFoundException;
import br.com.fwom.api.models.User;
import br.com.fwom.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Optional<User> getUser(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping()
    public Page<User> listUser(Pageable pageable) {
            return userService.findAll(pageable);
    }

    @PostMapping()
    public User addAnswer(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{userId}")
    public User updateAnswer(@PathVariable Long userId,
                             @Valid @RequestBody User user) {
        Optional<User> rawUser = userService.findById(userId);
        if (!rawUser.isPresent()) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        return rawUser
                .map(u -> {
                    u.setEmail(user.getEmail());
                    u.setName(user.getName());
                    u.setRoleId(user.getRoleId());
                    return userService.save(u);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long userId) {
        Optional<User> rawUser = userService.findById(userId);
        if (rawUser.isPresent()) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        return rawUser
                .map(u -> {
                    userService.delete(userId);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + userId));

    }
}
