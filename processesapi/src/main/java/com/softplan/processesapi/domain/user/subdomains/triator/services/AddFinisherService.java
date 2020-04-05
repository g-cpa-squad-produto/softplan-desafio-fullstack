package com.softplan.processesapi.domain.user.subdomains.triator.services;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.models.ProcessUser;
import com.softplan.processesapi.domain.process.repository.ProcessRepository;
import com.softplan.processesapi.domain.user.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddFinisherService implements IAddFinisherService {

    private ProcessRepository processRepository;

    public AddFinisherService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public Process addFinisher(Process process, User finisher) {
        ProcessUser processUser = new ProcessUser();
        processUser.setProcess(process);
        processUser.setUser(finisher);

        List<ProcessUser> processUsers = process.getProcessUsers();
        processUsers.add(processUser);

        process.setProcessUsers(processUsers);

        return processRepository.save(process);
    }
}
