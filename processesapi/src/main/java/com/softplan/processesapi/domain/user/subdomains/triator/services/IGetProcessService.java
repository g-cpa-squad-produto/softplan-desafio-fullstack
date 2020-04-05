package com.softplan.processesapi.domain.user.subdomains.triator.services;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.process.models.ProcessUser;
import com.softplan.processesapi.domain.user.models.User;

import java.util.List;
import java.util.Optional;

public interface IGetProcessService {
    List<Process> getAll();
    Optional<Process> getOne(Long processId);
    Optional<ProcessUser> getOneByUserAndProcess(Process process, User user);
}
