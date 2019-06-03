package com.pmanagement.pmanagementbackend.domain.repository;

import com.pmanagement.pmanagementbackend.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to persist the {@link User}
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find the {@link User} by the username
     * 
     * @param username from the {@link User}
     * @return the {@link Optional} for the {@link User}
     */
    Optional<User> findByUsername(final String username);
}