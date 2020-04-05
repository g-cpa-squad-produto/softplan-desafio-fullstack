package com.softplan.processesapi.domain.user.subdomains.triator.services;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.repository.ProcessRepository;

import java.util.List;
import java.util.Optional;

public class GetProcessService implements IGetProcessService {

    private ProcessRepository processRepository;

    public GetProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @Override
    public List<Process> getAll() {
        return (List<Process>) processRepository.findAll();
    }

    @Override
    public Optional<Process> getOne(Long processId) {
        return processRepository.findById(processId);
    }
}
