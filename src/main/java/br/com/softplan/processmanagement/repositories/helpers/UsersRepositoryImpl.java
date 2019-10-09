package br.com.softplan.processmanagement.repositories.helpers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.User;
import br.com.softplan.processmanagement.services.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UsersRepositoryImpl implements UsersRepositoryQueries {

    private static final Logger logger = LoggerFactory.getLogger(UsersRepositoryImpl.class);

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Process> findProcessByUser(User user) {
        Long userId = user.getId();
        if(userId == null) throw new UserNotFoundException("User not found");

        List<Process> processes = manager.createQuery("SELECT p.userProcessId.process FROM UserProcess p WHERE p.userProcessId.user.id = :userId", Process.class)
                .setParameter("userId", userId).getResultList();

        logger.info(String.valueOf(processes));

        return processes;
    }

}
