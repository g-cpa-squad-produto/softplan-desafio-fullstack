package br.com.softplan.processmanagement.repositories.helpers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;

import java.util.List;

public interface ProcessesRepositoryQueries {
    List<UserSystem> findUsersByProcess(Process process);
}
