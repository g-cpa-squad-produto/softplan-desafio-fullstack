package com.softplan.processesapi.domain.user.subdomains.admin.services;

import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements ICreateUserService {

    private UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
