package br.com.softplan.process.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.softplan.process.api.entity.User;
import br.com.softplan.process.api.enums.ProfileEnum;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);
	
	User findById(String id);
	
	Iterable<User> findAllByProfile(ProfileEnum profile);	
}
