package br.com.sitalobr.dev.desafio.validator;

import br.com.sitalobr.dev.desafio.entity.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Classe responsável por determinar como será realizada a validação dos dados ao cadastrar uma {@link Usuario}
 */
@Component
public class UsuarioValidator extends AbstractValidator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Usuario.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Usuario pessoa = (Usuario) o;

        if (this.checkEmptyString(pessoa.getUsername()))
            errors.rejectValue("username", "", "Username não informado");
        if (this.checkEmptyString(pessoa.getNome()))
            errors.rejectValue("nome", "", "Nome não informado");
        if (pessoa.getPassword() == null)
            errors.rejectValue("password", "", "Password não informado");
    }
}
