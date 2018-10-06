package br.com.softplan.process.service;

import br.com.softplan.process.model.Process;
import br.com.softplan.process.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessService {

    private ProcessRepository repository;

    @Autowired
    public ProcessService(ProcessRepository repository) {
        this.repository = repository;
    }

    public void save(Process Process) {
        this.repository.save(Process);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public List<Process> findAll() {
        return this.repository.findAll();
    }

    public Process findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }
}
