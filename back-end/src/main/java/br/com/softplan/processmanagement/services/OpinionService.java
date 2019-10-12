package br.com.softplan.processmanagement.services;

import br.com.softplan.processmanagement.domain.Opinion;
import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.repositories.OpinionRepository;
import br.com.softplan.processmanagement.services.exceptions.OpinionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OpinionService {

    public static final Logger logger = LoggerFactory.getLogger(Opinion.class);

    @Autowired
    private OpinionRepository opinionRepository;

    @Autowired
    private ProcessesService processesService;

    @Autowired
    private UsersService usersService;

    public List<Opinion> list() {
        return opinionRepository.findAll();
    }

    @Transactional
    public Opinion save(Opinion opinion) {
        opinion.setId(null);
        opinion.setCreatedAt(LocalDateTime.now());
        return opinionRepository.save(opinion);
    }

    public Opinion searchById(Long id) {
        Optional<Opinion> opinion = opinionRepository.findById(id);
        if (!opinion.isPresent()) {
            throw new OpinionNotFoundException("Opinion not found");
        }
        return opinion.get();
    }

    public Optional<Opinion> findByUserAndProcess(UserSystem user, Process process){
        Optional<Opinion> opinion = opinionRepository.findOpinionByUserAndProcess(user, process);
        return opinion;
    }

    public List<Opinion> findAllByProcess(Process process){
        return opinionRepository.findAllByProcess(process);
    }

    @Transactional
    public Opinion update(Long idProcess, Opinion opinion) {
        checkExistence(opinion);

        Process process = processesService.searchById(idProcess);
        UserSystem userSystem = usersService.searchById(opinion.getUserSystem().getId());

        opinion.setProcess(process);
        opinion.setUserSystem(userSystem);

        logger.info(opinion.toString());
        return opinionRepository.save(opinion);
    }

    @Transactional
    public void delete(Long id) {
        try {
            opinionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new OpinionNotFoundException("Opinion not found");
        }
    }

    public void checkExistence(Opinion opinion) {
        searchById(opinion.getId());
    }

    public List<Opinion> getOpinionsByProcess(Long idProcess) {
        Optional<Process> process = Optional.of(processesService.searchById(idProcess));
        List<Opinion> opinions = new ArrayList<>();
        if (process.isPresent()) {
            opinions = opinionRepository.findAllByProcessAndTextNotNull(process.get());
        }
        return opinions;
    }

    public void removeAll(List<Opinion> collect) {
        opinionRepository.deleteAll(collect);
    }
}
