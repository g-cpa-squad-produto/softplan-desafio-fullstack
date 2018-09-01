package com.luanrubensf.challenge.service;

import com.luanrubensf.challenge.core.validation.Validator;
import com.luanrubensf.challenge.model.Parecer;
import com.luanrubensf.challenge.repository.ParecerRepository;
import com.luanrubensf.challenge.validators.ParecerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParecerService {

    @Autowired
    private ParecerRepository parecerRepository;

    @Autowired
    private Validator validator;

    public Parecer save(Parecer entity) {
        validate(entity);
        return parecerRepository.save(entity);
    }

    private void validate(Parecer entity) {
        validator.validate(entity, ParecerValidator.checkUserJaVinculado(parecerRepository));
    }
}
