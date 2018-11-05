package com.process.processmanagerapi.repository;

import com.process.processmanagerapi.entity.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends CrudRepository<UserType, Long> {

    UserType findByUserTypeName(final String userTypeName);
}
