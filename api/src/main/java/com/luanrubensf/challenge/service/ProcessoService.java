package com.luanrubensf.challenge.service;

import com.luanrubensf.challenge.core.validation.Validator;
import com.luanrubensf.challenge.model.Processo;
import com.luanrubensf.challenge.repository.ProcessoRepository;
import com.luanrubensf.challenge.validators.ProcessoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private Validator validator;

    public Processo save(Processo entity) {
        validate(entity);
        return processoRepository.save(entity);
    }

    private void validate(Processo entity) {
        validator.validate(entity, ProcessoValidator.checkNomeDuplicado(processoRepository));
    }
}
