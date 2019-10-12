package br.com.softplan.processmanagement.repositories.helpers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.Opinion;

import java.util.Optional;

public interface OpinionRepositoryQueries {
    Optional<Opinion> findOpinionByUserAndProcess(UserSystem userSystem, Process process);
}
