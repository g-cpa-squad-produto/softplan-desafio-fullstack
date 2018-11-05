package com.process.processmanagerapi.repository;

import com.process.processmanagerapi.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    User findByName(final String name);

    List<User> findAll();

}
