package br.com.softplan.processmanagement.repositories.helpers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserSystemProcess;

import java.util.List;
import java.util.Optional;

public interface UsersSystemRepositoryQueries {
    List<Process> findProcessByUser(UserSystem userSystem);
}
