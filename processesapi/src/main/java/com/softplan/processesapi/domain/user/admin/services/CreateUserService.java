package com.softplan.processesapi.domain.user.admin.services;

import com.softplan.processesapi.domain.user.admin.models.Admin;
import com.softplan.processesapi.domain.user.finisher.models.Finisher;
import com.softplan.processesapi.domain.user.models.User;
import com.softplan.processesapi.domain.user.repository.UserRepository;
import com.softplan.processesapi.domain.user.triator.models.Triator;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements ICreateUserService {

    private UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createAdmin(Admin admin) {
        return userRepository.save(admin);
    }

    @Override
    public User createTriator(Triator triator) {
        return userRepository.save(triator);
    }

    @Override
    public User createFinisher(Finisher finisher) {
        return userRepository.save(finisher);
    }
}
