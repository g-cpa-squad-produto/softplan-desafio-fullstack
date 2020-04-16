package com.softplan.backend.repository;

import com.softplan.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
    User findByUsername(String email);
    Page<User> findAll(Pageable pageable);
}