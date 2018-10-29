package br.com.evaluation.process.manager.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.evaluation.process.manager.entity.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User u = (User) target;
		
		if(u.getName() == null || u.getName().isEmpty()) {
			errors.rejectValue("name", "user.name.invalid", "Nome do usuário inválido");
		}
		
		if(u.getLogin() == null || u.getLogin().isEmpty()) {
			errors.rejectValue("login", "user.login.invalid", "Login do usuário inválido");
		}
		
		if(u.getPassword() == null || u.getPassword().isEmpty()) {
			errors.rejectValue("password", "user.password.invalid", "Password do usuário inválido");
		}
		
	}
    
}
