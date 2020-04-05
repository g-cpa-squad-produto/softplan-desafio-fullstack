package com.softplan.processesapi.domain.user.repository;

import com.softplan.processesapi.domain.user.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
