package com.luanrubensf.challenge.validators;

import com.luanrubensf.challenge.core.validation.AbstractValidation;
import com.luanrubensf.challenge.model.Parecer;
import com.luanrubensf.challenge.repository.ParecerRepository;

public class ParecerValidator {

    public static AbstractValidation checkUserJaVinculado(ParecerRepository repository) {
        return new AbstractValidation<Parecer>("O usuário já está vinculado") {
            @Override
            public boolean validateEntity(Parecer candidate) {
                setMessage(String.format("O usuário %s já esta vinculado a esse processo", candidate.getUser().getName()));
                if (candidate.getId() != null) {
                    return !repository.existsByUserAndProcessoAndIdIsNot(candidate.getUser(),
                            candidate.getProcesso(),
                            candidate.getId());
                }
                return !repository.existsByUserAndProcesso(candidate.getUser(), candidate.getProcesso());
            }
        };
    }
}
