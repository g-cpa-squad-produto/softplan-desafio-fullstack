package com.isadora.backendapi.services;

import com.isadora.backendapi.domain.Processo;
import com.isadora.backendapi.exceptions.ProcessoException;
import com.isadora.backendapi.repositories.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcessoService {
    @Autowired
    private ProcessoRepository processoRepository;

    public Optional<Processo> findById(Long id) {
        Optional<Processo> processo = processoRepository.findById(id);
        if (!processo.isPresent()) {
            throw new ProcessoException("Processo com ID '" + id + "' não existe.");
        }
        return processo;
    }

    public Iterable<Processo> findAllProcessos() {
        return processoRepository.findAll();
    }

    public Processo save(Processo processo){
        try {
            processo.setNumero(processo.getNumero());
            return processoRepository.save(processo);
        }catch (Exception e){
            throw new ProcessoException("Processo de numero '"+processo.getNumero()+"' já existe.");
        }
    }

    public void deleteProcessoById(Long id){
        Optional<Processo> processo = processoRepository.findById(id);
        if (!processo.isPresent()) {
            throw new ProcessoException("Processo com ID '" + id + "' não existe.");
        }
        processoRepository.deleteById(id);
    }

    public Iterable<Processo>  findByParecerNull(){
        return processoRepository.findByParecerNull();
    }


}
