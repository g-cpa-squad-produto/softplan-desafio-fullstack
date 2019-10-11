package br.com.softplan.processmanagement.repositories.helpers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserSystemProcess;
import br.com.softplan.processmanagement.services.exceptions.ProcessNotFoundException;
import br.com.softplan.processmanagement.services.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class UserSystemProcessRepositoryImpl implements UserSystemProcessRepositoryQueries {

    private static final Logger logger = LoggerFactory.getLogger(UserSystemProcessRepositoryImpl.class);

    @PersistenceContext
    EntityManager manager;

    @Override
    public Optional<UserSystemProcess> findUserProcessByUserUserAndProcess(UserSystem userSystem, Process process) {

        List<UserSystemProcess> userSystemProcess = manager.createQuery("SELECT p FROM UserSystemProcess p WHERE p.userSystemProcessId.userSystem.id = :userId AND p.userSystemProcessId.process.id = :processId", UserSystemProcess.class)
                .setParameter("userId", userSystem.getId())
                .setParameter("processId", process.getId()).getResultList();

        logger.info(String.valueOf(userSystemProcess));
        return Optional.of(userSystemProcess.get(0));
    }

    @Override
    public Optional<UserSystemProcess> findRelationByUserAndProcess(UserSystem userSystem, Process process) {
        Long userId = userSystem.getId();
        if(userId == null) throw new UserNotFoundException("User not found");

        Long processId = process.getId();
        if(processId == null) throw new ProcessNotFoundException("Process not found");

        List<Process> processes = manager.createQuery("SELECT p.userSystemProcessId.process FROM UserSystemProcess p WHERE p.userSystemProcessId.userSystem.id = :userId and p.userSystemProcessId.process.id = :processId", Process.class)
                .setParameter("userId", userId)
                .setParameter("processId", processId)
                .getResultList();

        return Optional.empty();
    }
}
