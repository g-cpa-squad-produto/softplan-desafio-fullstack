package br.com.softplan.processmanagement.repositories.helpers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;

import java.util.List;

public interface UsersSystemRepositoryQueries {
    List<Process> findProcessByUser(UserSystem userSystem);
}
