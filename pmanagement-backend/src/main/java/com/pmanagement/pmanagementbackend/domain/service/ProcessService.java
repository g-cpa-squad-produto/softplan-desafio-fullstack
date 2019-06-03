package com.pmanagement.pmanagementbackend.domain.service;

import com.pmanagement.pmanagementbackend.domain.entity.Process;
import com.pmanagement.pmanagementbackend.domain.repository.ProcessRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for the {@link Process}
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Jun 2, 2019
 */
@Service
public class ProcessService {
    
    @Autowired
    private ProcessRepository processRepository;
    
    /**
     * List all {@link Process}
     * 
     * @return the {@link Process}
     */
    public List<Process> listProcesss() {
        return this.processRepository.findAll();
    }
    
    /**
     * Find the {@link Process} by the id
     * 
     * @param id from the {@link Process}
     * @return the {@link Process}
     */
    public Process findProcessById(long id) {
        return this.processRepository.getOne(id);
    }
    
    /**
     * Persist the {@link Process}
     * 
     * @param Process the {@link Process} to persist
     * @return the persisted {@link Process}
     */
    public Process saveProcess(Process Process) {
        return this.processRepository.saveAndFlush(Process);
    }
    
    /**
     * Delete the {@link Process}
     * 
     * @param Process the {@link Process} to delete
     */
    public void deleteProcess(Process Process) {
        this.processRepository.delete(Process);
    }
}