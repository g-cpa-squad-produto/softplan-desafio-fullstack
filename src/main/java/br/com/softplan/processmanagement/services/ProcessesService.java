package br.com.softplan.processmanagement.services;

import br.com.softplan.processmanagement.domain.Process;
import br.com.softplan.processmanagement.domain.User;
import br.com.softplan.processmanagement.repositories.ProcessesRepository;
import br.com.softplan.processmanagement.services.exceptions.ProcessNotFoundException;
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
    private UsersService usersService;

    //CRUD
    public Process save(Process process) {
        process.setId(null);
        return processesRepository.save(process);
    }

    public List<Process> list() {
        return processesRepository.findAll();
    }

    public List<Process> listByCreator(Long idUser) {
        User user = usersService.searchById(idUser);
        return processesRepository.findAllByCreator(user);
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

}
