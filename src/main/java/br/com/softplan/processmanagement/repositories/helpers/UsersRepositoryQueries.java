package br.com.softplan.processmanagement.repositories.helpers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.User;

import java.util.List;

public interface UsersRepositoryQueries {
    List<Process> findProcessByUser(User user);
}
