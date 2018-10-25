package br.com.softplan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.entity.Processo;
import br.com.softplan.entity.ResponseEntity;
import br.com.softplan.service.ProcessoService;

@RestController
@CrossOrigin(value="http://localhost:4200")
@RequestMapping(value="/processos")
public class ProcessoController {

	@Autowired
	private ProcessoService processoService;
	
	@GetMapping
	public List<Processo> listAll(){
		return processoService.listAll();
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity salvar(@RequestBody Processo processo)
	{
		return processoService.salvar(processo);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity atualizar(@RequestBody Processo processo) {
		return processoService.salvar(processo);
	}
	
	@DeleteMapping(value="excluir/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity deletar(@PathVariable("id") String id) {
		return processoService.delete(id);
	}
	
	@RequestMapping(value="/editar/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Processo getById(@PathVariable("id") String id) {
		return processoService.findById(id);
	}
	
	@GetMapping(value="/{numero}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Processo getByNumero(@PathVariable("numero") Integer numero) {
		return processoService.findByNumero(numero);
	}
}
