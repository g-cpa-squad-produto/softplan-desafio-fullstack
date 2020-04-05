package com.softplan.processesapi.domain.user.subdomains.triator.services;

import com.softplan.processesapi.domain.process.models.ProcessUser;
import com.softplan.processesapi.domain.process.repository.ProcessUserRepository;

public class AddFinisherService implements IAddFinisherService {

    private ProcessUserRepository processUserRepository;

    public AddFinisherService(ProcessUserRepository processUserRepository) {
        this.processUserRepository = processUserRepository;
    }

    @Override
    public ProcessUser addFinisher(ProcessUser processUser) {
        return processUserRepository.save(processUser);
    }
}
