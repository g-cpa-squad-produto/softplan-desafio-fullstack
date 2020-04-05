package com.softplan.processesapi.domain.user.subdomains.finisher.services;

import com.softplan.processesapi.domain.process.models.Process;

public interface IFinishProcessService {
    Process finishProcess(Process process, String description);
}
