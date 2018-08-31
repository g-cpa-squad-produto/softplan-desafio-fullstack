package com.luanrubensf.challenge.repository;

import com.luanrubensf.challenge.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAndEnabledIsTrue(String email);

    List<User> findAllBy();

    User findUserById(Long id);
}
