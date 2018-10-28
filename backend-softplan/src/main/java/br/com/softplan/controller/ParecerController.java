package br.com.softplan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.entity.Parecer;
import br.com.softplan.entity.Processo;
import br.com.softplan.entity.ResponseEntity;
import br.com.softplan.service.ParecerService;
import br.com.softplan.service.ProcessoService;


@RestController
@CrossOrigin(value="http://localhost:4200")
@RequestMapping(value="/pareceres")
public class ParecerController {
	
	@Autowired
	private ParecerService parecerService;
	
	@Autowired
	private ProcessoService processoService;
	
	@GetMapping
	public @ResponseBody List<Processo> listaProcessosSemParecer()
	{
		return parecerService.listProcessosSemParecer();
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity salvar(@RequestBody Processo processo) {
		return parecerService.salvar(processo);
	}
	
	@RequestMapping(value="/incluir/{id}", method=RequestMethod.GET)
	public @ResponseBody Processo getProcesso(@PathVariable("id") String id) {
		return processoService.findById(id);
	}
	
	@RequestMapping(value="/getbyprocesso/{id_processo}", method=RequestMethod.GET)
	public @ResponseBody Parecer getParecerByProcesso(@PathVariable("id_processo") String id) {
		return parecerService.getByProcessoId(id);
	}

}
