package br.com.softplan.feature.processo;

import br.com.softplan.feature.processo.model.Processo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProcessoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Processo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "uusario.nome.obrigatorio");
    }

}