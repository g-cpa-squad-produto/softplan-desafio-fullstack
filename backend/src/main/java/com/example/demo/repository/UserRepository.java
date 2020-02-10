package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio spring data para entidade usuario
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByLoginIgnoreCase(String login);

    Optional<User> findByLogin(String username);

    List<User> findAllByRole(Role role);
}
