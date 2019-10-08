package br.com.softplan.processmanagement.services;

import br.com.softplan.processmanagement.domain.Opinion;
import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.User;
import br.com.softplan.processmanagement.repositories.OpinionsRepository;
import br.com.softplan.processmanagement.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpinionsService {

    @Autowired
    private OpinionsRepository opinionsRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProcessesService processesService;

    public Opinion findByProcessAndUser(Long idUser, Long idProcess){
        User user = usersService.searchById(idUser);
        Process process = processesService.searchById(idProcess);
        Optional<Opinion> opinion = opinionsRepository.findByUserAndProcess(user, process);
        return opinion.orElse(null);
    }

}