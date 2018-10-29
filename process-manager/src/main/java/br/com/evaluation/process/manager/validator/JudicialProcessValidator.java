package br.com.evaluation.process.manager.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.evaluation.process.manager.entity.JudicialProcess;

@Component
public class JudicialProcessValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return JudicialProcess.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		JudicialProcess p = (JudicialProcess) target;
		
		if(p.getProcessNumber() == null || p.getProcessNumber().isEmpty()) {
			errors.rejectValue("processNumber", "judicialProcess.processNumber.invalid", "Numero do processo é inválidos");
		}
		
	}
    
}
