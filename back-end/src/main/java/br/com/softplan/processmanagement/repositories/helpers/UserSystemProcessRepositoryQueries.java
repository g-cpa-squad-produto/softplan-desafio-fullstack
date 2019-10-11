package br.com.softplan.processmanagement.repositories.helpers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserSystemProcess;

import java.util.Optional;

public interface UserSystemProcessRepositoryQueries {
    Optional<UserSystemProcess> findUserProcessByUserUserAndProcess(UserSystem userSystem, Process process);
    Optional<UserSystemProcess> findRelationByUserAndProcess(UserSystem userSystem, Process process);
}
