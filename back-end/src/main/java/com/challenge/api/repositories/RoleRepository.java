package com.challenge.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.challenge.api.documents.Role;


public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
}

