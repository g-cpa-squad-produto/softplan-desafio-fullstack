package com.isadora.backendapi.services;

import com.isadora.backendapi.repositories.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessoService {
    @Autowired
    private ProcessoRepository processoRepository;
}
