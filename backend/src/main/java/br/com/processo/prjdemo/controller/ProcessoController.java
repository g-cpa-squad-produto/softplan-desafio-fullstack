package br.com.processo.prjdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.processo.prjdemo.model.Parecer;
import br.com.processo.prjdemo.model.Processo;
import br.com.processo.prjdemo.service.ProcessoService;

/**
 * 
 * @author Guilherme Sena
 * REST's relacionados ao processo
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)//Permite chamadas de outra porta que nao a default 8080
@RequestMapping("/processo")
@RestController
public class ProcessoController {
	@Autowired
	private ProcessoService processoService;
	
	/**
	 * 
	 * @return retorna todos os processos
	 */
	@GetMapping("/todos-processos")
    public List<Processo> getTodosProcessos() {
		return processoService.getTodosProcessos();
	}
	
	/**
	 * 
	 * @param id
	 * @return retorna um processo pelo id
	 */
	@GetMapping("/processo-por-id/{id}")
    public Processo getProcessoPorId(@PathVariable Long id) {
		return processoService.getProcessoById(id);
	}
	
    /**
     * 
     * @param processo
     * @return retorna o processo salvo
     */
    @PostMapping("/processo-salvar")
	public Processo salvarProcesso(@RequestBody Processo processo) {
		return processoService.salvarProcesso(processo);
	}
    
    /**
     * @param Parecer
     * @return insere parecer no processo
     */
    @PatchMapping("/processo-parecer-salvar")
    public Parecer salvarParecer(@RequestBody Parecer parecer) {
		return processoService.salvarProcessoParecer(parecer);
	}
    
}
