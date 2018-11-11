package com.miratanlehmkuhl.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.miratanlehmkuhl.backend.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);

}
