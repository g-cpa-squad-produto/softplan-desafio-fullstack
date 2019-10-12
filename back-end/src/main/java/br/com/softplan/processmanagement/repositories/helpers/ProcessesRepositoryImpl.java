package br.com.softplan.processmanagement.repositories.helpers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.services.exceptions.ProcessNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProcessesRepositoryImpl implements ProcessesRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<UserSystem> findUsersByProcess(Process process) {
        Long processId = process.getId();
        if (processId == null)
            throw new ProcessNotFoundException("Process not found");

        List<UserSystem> users = manager.createQuery("SELECT p.userSystem FROM Opinion p WHERE p.process.id = :processId", UserSystem.class)
                .setParameter("processId", processId).getResultList();

        return users;
    }
}
