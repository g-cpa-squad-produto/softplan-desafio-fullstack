package br.com.softplan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.models.Proccess;
import br.com.softplan.services.ProcessService;


@RestController
@RequestMapping("/process")
public class ProcessController  extends GenericController<Proccess, Long> {

	@Autowired
	private ProcessService service;
	
	@Override
	@CrossOrigin
	@PutMapping
	public void update(@RequestBody Proccess t) {
		this.service.assining(t);
	}
	
}

