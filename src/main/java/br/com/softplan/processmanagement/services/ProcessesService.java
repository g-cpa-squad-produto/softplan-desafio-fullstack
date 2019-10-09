package br.com.softplan.processmanagement.services;

import br.com.softplan.processmanagement.domain.*;
import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.dto.OpinionDTO;
import br.com.softplan.processmanagement.repositories.ProcessesRepository;
import br.com.softplan.processmanagement.repositories.UserProcessRepository;
import br.com.softplan.processmanagement.repositories.UsersRepository;
import br.com.softplan.processmanagement.services.exceptions.ProcessNotFoundException;
import br.com.softplan.processmanagement.services.exceptions.UserProcessNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessesService {

    @Autowired
    private ProcessesRepository processesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserProcessRepository userProcessRepository;

    @Autowired
    private UsersService usersService;

    //CRUD
    public Process save(Process process) {
        process.setId(null);
        return processesRepository.save(process);
    }

    public List<Process> list() {
        return processesRepository.findAll();
    }

    public List<Process> listByUser(Long idUser) {
        User user = usersService.searchById(idUser);
        UserType type = user.getType();

        if(type == UserType.FINALIZADOR){//FINALIZADOR VE APENAS PROCESSOS ATRIBUIDOS A ELE.
            return usersRepository.findProcessByUser(user);
        }else if(type == UserType.TRIADOR){//TRIADOR VE APENAS SEUS PROCESSOS.
            return processesRepository.findAllByCreator(user);
        }
        return this.list();//ADMIN VE TUDO
    }

    public Process searchById(Long id) {
        Optional<Process> user = processesRepository.findById(id);
        if (!user.isPresent()) {
            throw new ProcessNotFoundException("Process not found");
        }
        return user.get();
    }

    public Process update(Process process) {
        checkExistence(process);
        return processesRepository.save(process);
    }

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

    public UserProcess saveOpinion(Long id, OpinionDTO opinionDTO) {
        Optional<Process> process = Optional.of(this.searchById(id));
        Optional<User> user = Optional.of(usersService.searchById(opinionDTO.getIdUser()));

        if(process.isPresent() && user.isPresent()){

            Optional<UserProcess> userProcessOptional = userProcessRepository.findUserProcessesByUserProcessIdUserAndUserProcessIdProcess(user.get(), process.get());

            if(userProcessOptional.isPresent()){
                UserProcess userProcess = userProcessOptional.get();
                userProcess.setText(opinionDTO.getText());
                userProcess.setCreatedAt(opinionDTO.getCreatedAt());
                return userProcessRepository.save(userProcess);
            }

        }
        throw new UserProcessNotFoundException("Problem finding user link with process");
    }
}
