package com.softplan.processesapi.domain.user.subdomains.triator.services;

import com.softplan.processesapi.domain.process.models.Process;
import com.softplan.processesapi.domain.user.models.User;

public interface IAddFinisherService {
    Process addFinisher(Process process, User finisher);
}
