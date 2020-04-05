package com.softplan.processesapi.domain.user.subdomains.triator.services;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.repository.ProcessRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProcessService implements ICreateProcessService {

    private ProcessRepository processRepository;

    public CreateProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @Override
    public Process createProcess(Process process) {
        return processRepository.save(process);
    }
}
