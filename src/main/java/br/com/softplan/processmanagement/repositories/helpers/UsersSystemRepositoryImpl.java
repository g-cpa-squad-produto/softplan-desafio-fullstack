package br.com.softplan.processmanagement.repositories.helpers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.services.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UsersSystemRepositoryImpl implements UsersSystemRepositoryQueries {

    private static final Logger logger = LoggerFactory.getLogger(UsersSystemRepositoryImpl.class);

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Process> findProcessByUser(UserSystem userSystem) {
        Long userId = userSystem.getId();
        if(userId == null) throw new UserNotFoundException("User not found");

        List<Process> processes = manager.createQuery("SELECT p.userSystemProcessId.process FROM UserSystemProcess p WHERE p.userSystemProcessId.user.id = :userId", Process.class)
                .setParameter("userId", userId).getResultList();

        logger.info(String.valueOf(processes));

        return processes;
    }

}
