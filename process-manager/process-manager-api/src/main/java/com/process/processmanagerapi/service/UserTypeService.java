package com.process.processmanagerapi.service;

import com.process.processmanagerapi.entity.UserType;
import com.process.processmanagerapi.exception.UserTypeServiceException;
import com.process.processmanagerapi.repository.UserTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserTypeService {

    @Autowired
    UserTypeRepository userTypeRepository;

    public UserType findUserTypeByUserTypeName(final String userTypeName) {
        log.info("find user type by user type name: {}", userTypeName);
        try {
            return userTypeRepository.findByUserTypeName(userTypeName);
        } catch (Exception e) {
            log.error("Error to find user type by user type name", e);
            throw new UserTypeServiceException("Error to find user type by user type name");
        }
    }
}
