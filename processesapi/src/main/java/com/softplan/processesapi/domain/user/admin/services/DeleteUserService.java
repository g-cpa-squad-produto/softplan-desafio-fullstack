package com.softplan.processesapi.domain.user.admin.services;

import com.softplan.processesapi.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService implements IDeleteUserService {

    private UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
