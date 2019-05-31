package br.com.softplan.marcusvoltolim.controller;

import br.com.softplan.marcusvoltolim.model.Processo;
import br.com.softplan.marcusvoltolim.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("processo")
public class ProcessoController extends AbstractController<Processo> {
	
	@SuppressWarnings({"FieldCanBeLocal", "unused"})
	private ProcessoService processoService;
	
	@Autowired
	ProcessoController(ProcessoService processoService) {
		super(processoService);
		this.processoService = processoService;
	}
	
}
