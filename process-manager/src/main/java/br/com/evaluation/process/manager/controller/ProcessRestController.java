package br.com.evaluation.process.manager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.evaluation.process.manager.entity.JudicialProcess;
import br.com.evaluation.process.manager.service.JudicialProcessService;
import br.com.evaluation.process.manager.validator.JudicialProcessValidator;

@RestController
@RequestMapping("/judicialProcess")
@CrossOrigin
public class ProcessRestController {

	@Autowired
	private JudicialProcessService service;
	
	@Autowired
	private JudicialProcessValidator validator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/save")
	public JudicialProcess save(@RequestBody @Valid  JudicialProcess process) {
		return service.save(process);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findAll")
	public Iterable<JudicialProcess> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public void remove(@PathVariable(value = "id") Long id) {
		service.delete(id);
	}

}
