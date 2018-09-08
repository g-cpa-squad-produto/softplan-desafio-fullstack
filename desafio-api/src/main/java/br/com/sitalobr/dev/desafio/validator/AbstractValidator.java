package br.com.sitalobr.dev.desafio.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe responsável por determinar o comportamento padrão das classes de validação
 */
public abstract class AbstractValidator implements Validator {
    @Override
    public abstract boolean supports(Class<?> aClass);

    @Override
    public abstract void validate(Object o, Errors errors);

    /**
     * Checa se uma determinada string passada por parâmetro é vazia
     * @param input String a ser verificada
     * @return true caso seja vazia, false caso contrário
     */
    boolean checkEmptyString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
