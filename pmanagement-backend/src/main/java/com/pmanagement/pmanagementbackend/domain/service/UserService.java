package com.pmanagement.pmanagementbackend.domain.service;

import com.pmanagement.pmanagementbackend.domain.entity.User;
import com.pmanagement.pmanagementbackend.domain.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service for the {@link User}
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@Service
public class UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    /**
     * List all {@link User}
     *
     * @return the {@link User}
     */
    public List<User> listUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Find the {@link User} by the id
     *
     * @param id from the {@link User}
     * @return the {@link User}
     */
    public User findUserById(long id) {
        return this.userRepository.getOne(id);
    }

    /**
     * Find the {@link User} by the username
     *
     * @param username from the {@link User}
     * @return the {@link Optional} for the {@link User}
     */
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    /**
     * Persist the {@link User}
     *
     * @param user the {@link User} to persist
     * @return the persisted {@link User}
     */
    public User saveUser(User user) {
        final String encodedPassword = this.passwordEncoder.encode(user.getPassword());
                
        user.setPassword(encodedPassword);
        
        return this.userRepository.saveAndFlush(user);
    }

    /**
     * Delete the {@link User}
     *
     * @param user the {@link User} to delete
     */
    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }
}
