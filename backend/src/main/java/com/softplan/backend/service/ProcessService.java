package com.softplan.backend.service;

import com.softplan.backend.entity.Process;
import com.softplan.backend.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    public Page<Process> findAllProcesses(Pageable pageable){
        return processRepository.findAll(pageable);
    }

    public Process newProcess(Process process) throws Exception {
        try {
            return processRepository.save(process);
        }catch (Exception e){
            throw new Exception("Nome de usuário já existe");
        }
    }

    public Process updateProcess(Process process) throws Exception {
        try {
            return processRepository.save(process);
        }catch (Exception e){
            throw new Exception("ID não encontrado");
        }
    }

    public void deleteProcess(Long id) throws Exception {
        try {
            processRepository.deleteById(id);
        }catch (Exception e){
            throw new Exception("ID não encontrado");
        }
    }

}