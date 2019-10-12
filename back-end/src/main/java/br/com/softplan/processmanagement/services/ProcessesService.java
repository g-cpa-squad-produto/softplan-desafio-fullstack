package br.com.softplan.processmanagement.services;

import br.com.softplan.processmanagement.domain.Opinion;
import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserType;
import br.com.softplan.processmanagement.repositories.OpinionRepository;
import br.com.softplan.processmanagement.repositories.ProcessesRepository;
import br.com.softplan.processmanagement.repositories.UsersSystemRepository;
import br.com.softplan.processmanagement.services.exceptions.ProcessNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcessesService {

    public static final Logger logger = LoggerFactory.getLogger(ProcessesService.class);

    @Autowired
    private ProcessesRepository processesRepository;

    @Autowired
    private UsersSystemRepository usersSystemRepository;

    @Autowired
    private OpinionService opinionService;

    @Autowired
    private UsersService usersService;

    @Transactional
    public Process save(Process process) {
        process.setId(null);
        process.setCreatedAt(LocalDateTime.now());

        Process processSaved = processesRepository.saveAndFlush(process);

        Optional<List<Opinion>> opinions = Optional.of(process.getOpinions());
        if(opinions.isPresent()){
            for (Opinion opinion: opinions.get()){
                UserSystem userSystem = usersService.searchById(opinion.getUserSystem().getId());
                opinion.setUserSystem(userSystem);
                opinion.setProcess(processSaved);
                opinionService.save(opinion);
            }
        }
        logger.info(String.valueOf(process));

        return processSaved;
    }

    public List<Process> list() {
        List<Process> processes = processesRepository.findAll();

        return processes;
    }

    public List<Process> listByUser(Long idUser) {
        UserSystem userSystem = usersService.searchById(idUser);
        UserType type = userSystem.getType();
        if (type == UserType.FINALIZADOR) {//FINALIZADOR VE APENAS PROCESSOS ATRIBUIDOS A ELE.
            return usersSystemRepository.findProcessByUser(userSystem);
        } else if (type == UserType.TRIADOR) {//TRIADOR VE APENAS SEUS PROCESSOS.
            return processesRepository.findAllByCreator(userSystem);
        }
        return this.list();//ADMIN VE TUDO

    }

    public Process searchById(Long id) {
        Optional<Process> process = processesRepository.findById(id);
        if (!process.isPresent()) {
            throw new ProcessNotFoundException("Process not found");
        }
        return process.get();
    }

    @Transactional
    public Process update(Process process) {
        checkExistence(process);
        Process processSaved = processesRepository.saveAndFlush(process);

        List<Opinion> actualOpinions = opinionService.findAllByProcess(process);

        //SAVE NEW SENT OPINION
        Optional<List<Opinion>> sentOpinions = Optional.of(process.getOpinions());
        if(sentOpinions.isPresent()){
            List<Opinion> sentOpinionsList = sentOpinions.get();
            for (Opinion opinion: sentOpinionsList){
                UserSystem userSystem = usersService.searchById(opinion.getUserSystem().getId());
                opinion.setUserSystem(userSystem);
                opinion.setProcess(processSaved);
                Optional<Opinion> byUserAndProcess = opinionService.findByUserAndProcess(userSystem, processSaved);
                if(!byUserAndProcess.isPresent()) {
                    opinionService.save(opinion);
                }
            }
            //REMOVE NOT SENT OPINION
            List<Opinion> collect = actualOpinions.stream().filter(opinion -> {
                return !sentOpinionsList.contains(opinion);
            }).collect(Collectors.toList());
            opinionService.removeAll(collect);
        }

        return processSaved;
    }

    @Transactional
    public void delete(Long id) {
        try {
            processesRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProcessNotFoundException("Process not found");
        }
    }

    public void checkExistence(Process process) {
        searchById(process.getId());
    }

}
