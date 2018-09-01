package com.luanrubensf.challenge.validators;

import com.luanrubensf.challenge.core.validation.AbstractValidation;
import com.luanrubensf.challenge.model.Processo;
import com.luanrubensf.challenge.repository.ProcessoRepository;

public class ProcessoValidator {

    public static AbstractValidation checkNomeDuplicado(ProcessoRepository repository) {
        return new AbstractValidation<Processo>("Já existe um processo com este nome") {
            @Override
            public boolean validateEntity(Processo candidate) {
                if (candidate.getId() != null) {
                    return !repository.existsByNameAndIdIsNot(candidate.getName(), candidate.getId());
                }
                return !repository.existsByName(candidate.getName());
            }
        };
    }
}
