package com.softplan.processesapi.domain.user.subdomains.admin.services;

import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
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
