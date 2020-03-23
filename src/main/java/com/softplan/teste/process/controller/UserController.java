package com.softplan.teste.process.controller;

import com.softplan.teste.process.exception.AppException;
import com.softplan.teste.process.exception.ResourceNotFoundException;
import com.softplan.teste.process.model.Role;
import com.softplan.teste.process.model.RoleName;
import com.softplan.teste.process.model.User;
import com.softplan.teste.process.payload.*;
import com.softplan.teste.process.repository.ProcessRepository;
import com.softplan.teste.process.repository.UserRepository;
import com.softplan.teste.process.repository.ReviewRepository;
import com.softplan.teste.process.security.UserPrincipal;
import com.softplan.teste.process.service.ProcessService;
import com.softplan.teste.process.security.CurrentUser;
import com.softplan.teste.process.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProcessService processService;
    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long processCount = processRepository.countByCreatedBy(user.getId());
        long reviewCount = reviewRepository.countByUserId(user.getId());

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), processCount, reviewCount);

        return userProfile;
    }

    @GetMapping("/users/{username}/processes")
    public PagedResponse<ProcessResponse> getProcessesCreatedBy(@PathVariable(value = "username") String username,
                                                         @CurrentUser UserPrincipal currentUser,
                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return processService.getProcessCreatedBy(username, currentUser, page, size);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserSummary> getUsers() {
        List<UserSummary> usersSumary = new ArrayList<UserSummary>();
        List<User> users = userRepository.findAll();

        for(User user : users) {
            usersSumary.add(new UserSummary(user.getId(), user.getUsername(), user.getName()));
        }
        return usersSumary;
    }
    @PostMapping("/users/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editUser(@Valid @RequestBody SignUpRequest signUpRequest, @PathVariable Long id) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Fething user's account

        Optional<User> auxUser = userRepository.findById(id);
        if(!auxUser.isEmpty()) {
            User user = auxUser.get();
            user.setName(signUpRequest.getName());
            user.setUsername(signUpRequest.getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User result = userRepository.save(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/users/{username}")
                    .buildAndExpand(result.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "User updated successfully"));
        }

        throw new UsernameNotFoundException("Usuário não encontrado com o id: "+id);

    }

    @GetMapping("/users/{username}/reviews")
    public PagedResponse<ProcessResponse> getProcessReviewedBy(@PathVariable(value = "username") String username,
                                                       @CurrentUser UserPrincipal currentUser,
                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return processService.getProcessesReviewedBy(username, currentUser, page, size);
    }

}
