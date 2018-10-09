package br.com.softplan.process.service;

import br.com.softplan.process.model.Process;
import br.com.softplan.process.model.User;
import br.com.softplan.process.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProcessService {

    private ProcessRepository repository;
    private AuthService authService;

    @Autowired
    public ProcessService(ProcessRepository repository, AuthService authService) {
        this.repository = repository;
        this.authService = authService;
    }

    public void save(Process process) {
        this.repository.save(process);
    }

    public List<Process> findAll() {
        return this.repository.findAll();
    }

    public Process findById(Long id) throws Exception {
        return this.repository
                   .findById(id)
                   .orElseThrow(() -> new Exception("Processo inexistente"));
    }

    public List<Process> findByUserLogged() {
        return repository.findByUsers(Collections.singletonList(authService.getAuthentication()));
    }
}
