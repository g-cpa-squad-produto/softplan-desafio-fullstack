package br.com.softplan.processmanagement.services;

import br.com.softplan.processmanagement.domain.*;
import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.dto.OpinionDTO;
import br.com.softplan.processmanagement.repositories.ProcessesRepository;
import br.com.softplan.processmanagement.repositories.UserSystemProcessRepository;
import br.com.softplan.processmanagement.repositories.UsersSystemRepository;
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
    private UsersSystemRepository usersSystemRepository;

    @Autowired
    private UserSystemProcessRepository userSystemProcessRepository;

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
        UserSystem userSystem = usersService.searchById(idUser);
        UserType type = userSystem.getType();

        if(type == UserType.FINALIZADOR){//FINALIZADOR VE APENAS PROCESSOS ATRIBUIDOS A ELE.
            return usersSystemRepository.findProcessByUser(userSystem);
        }else if(type == UserType.TRIADOR){//TRIADOR VE APENAS SEUS PROCESSOS.
            return processesRepository.findAllByCreator(userSystem);
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

    public UserSystemProcess saveOpinion(Long id, OpinionDTO opinionDTO) {
        Optional<Process> process = Optional.of(this.searchById(id));
        Optional<UserSystem> user = Optional.of(usersService.searchById(opinionDTO.getIdUser()));

        if(process.isPresent() && user.isPresent()){

            Optional<UserSystemProcess> userProcessOptional = userSystemProcessRepository.findUserSystemProcessByUserSystemProcessIdUserSystemAndUserSystemProcessIdProcess(user.get(), process.get());

            if(userProcessOptional.isPresent()){
                UserSystemProcess userSystemProcess = userProcessOptional.get();
                userSystemProcess.setText(opinionDTO.getText());
                userSystemProcess.setCreatedAt(opinionDTO.getCreatedAt());
                return userSystemProcessRepository.save(userSystemProcess);
            }

        }
        throw new UserProcessNotFoundException("Problem finding user link with process");
    }
}
