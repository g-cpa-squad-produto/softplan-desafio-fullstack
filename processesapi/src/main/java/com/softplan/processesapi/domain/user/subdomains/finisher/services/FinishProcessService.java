package com.softplan.processesapi.domain.user.subdomains.finisher.services;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.repository.ProcessRepository;
import com.softplan.processesapi.infrastructure.responsestatus.ResourceNotFoundException;
import com.softplan.processesapi.infrastructure.responsestatus.WrongCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class FinishProcessService implements IFinishProcessService {

    private ProcessRepository processRepository;

    public FinishProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @Override
    public Process finishProcess(Process process, String description) {
        process.setDescription(description);
        return processRepository.save(process);
    }
}
