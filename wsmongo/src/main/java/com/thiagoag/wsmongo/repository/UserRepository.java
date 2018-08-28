package com.thiagoag.wsmongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.thiagoag.wsmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{


}
