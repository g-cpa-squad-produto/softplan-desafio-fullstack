package com.softplan.processesapi.domain.user.subdomains.triator.services;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.models.ProcessUser;
import com.softplan.processesapi.domain.process.repository.ProcessRepository;
import com.softplan.processesapi.domain.process.repository.ProcessUserRepository;
import com.softplan.processesapi.domain.user.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetProcessService implements IGetProcessService {

    private ProcessRepository processRepository;
    private ProcessUserRepository processUserRepository;

    public GetProcessService(ProcessRepository processRepository, ProcessUserRepository processUserRepository) {
        this.processRepository = processRepository;
        this.processUserRepository = processUserRepository;
    }

    @Override
    public List<Process> getAll() {
        return (List<Process>) processRepository.findAll();
    }

    @Override
    public Optional<Process> getOne(Long processId) {
        return processRepository.findById(processId);
    }

    @Override
    public Optional<ProcessUser> getOneByUserAndProcess(Process process, User user) {
        return processUserRepository.findOneByUserAndProcess(user, process);
    }
}
