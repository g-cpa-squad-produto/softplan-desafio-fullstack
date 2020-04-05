package com.softplan.processesapi.domain.user.subdomains.triator.services;

import com.softplan.processesapi.domain.process.models.Process;

import java.util.List;
import java.util.Optional;

public interface IGetProcessService {
    List<Process> getAll();
    Optional<Process> getOne(Long processId);
}
