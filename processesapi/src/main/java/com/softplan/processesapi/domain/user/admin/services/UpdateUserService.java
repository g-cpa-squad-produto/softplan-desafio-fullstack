package com.softplan.processesapi.domain.user.admin.services;

import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.repository.UserRepository;

public class UpdateUserService implements IUpdateUserService {

    private UserRepository userRepository;

    public UpdateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User put(User user) {
        return userRepository.save(user);
    }
}
