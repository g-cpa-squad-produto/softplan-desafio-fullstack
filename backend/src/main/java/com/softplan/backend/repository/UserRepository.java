package com.softplan.backend.repository;

import com.softplan.backend.entity.User;
import com.softplan.backend.enumeration.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByRole(Role role);
    Page<User> findAll(Pageable pageable);
}