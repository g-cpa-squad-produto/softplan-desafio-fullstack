package com.challenge.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.challenge.api.documents.User;

public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(String username);

}
