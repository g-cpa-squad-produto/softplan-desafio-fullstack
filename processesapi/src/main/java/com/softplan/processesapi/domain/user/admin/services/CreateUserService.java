package com.softplan.processesapi.domain.user.admin.services;

import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements ICreateUserService {

    private UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User post(User user) {
        return userRepository.save(user);
    }
}
