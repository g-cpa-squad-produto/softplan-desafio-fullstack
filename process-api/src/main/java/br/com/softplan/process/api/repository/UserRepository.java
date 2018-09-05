package br.com.softplan.process.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.softplan.process.api.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);
}
