package br.com.softplan.processmanagement.repositories.helpers;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.Opinion;
import br.com.softplan.processmanagement.services.exceptions.ProcessNotFoundException;
import br.com.softplan.processmanagement.services.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class OpinionRepositoryImpl implements OpinionRepositoryQueries {

    private static final Logger logger = LoggerFactory.getLogger(OpinionRepositoryImpl.class);

    @PersistenceContext
    EntityManager manager;

    @Override
    public Optional<Opinion> findOpinionByUserAndProcess(UserSystem userSystem, Process process) {
        List<Opinion> opinions = manager.createQuery("SELECT p FROM Opinion p WHERE p.userSystem.id = :userId AND p.process.id = :processId", Opinion.class).setParameter("userId", userSystem.getId()).setParameter("processId", process.getId()).getResultList();
        if(opinions.size() > 0){
            return Optional.of(opinions.get(0));
        }
        return Optional.empty();
    }

}
