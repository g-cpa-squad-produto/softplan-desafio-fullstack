package br.com.softplan.desafiojava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.desafiojava.business.ParecerBusiness;
import br.com.softplan.desafiojava.entity.Parecer;

@RestController
public class ParecerController {
	
	@Autowired
	ParecerBusiness business;

	@GetMapping("/parecer/{id}")
	public Parecer consultar(@PathVariable Long id) {
		return business.consultar(id);
	}
	
	/**
	 * Pareceres de um determinado processo
	 * @param id Identificador do processo
	 * @return Lista de pareceres
	 */
	@GetMapping("/parecer/processo/{id}")
	public List<Parecer> listarPorProcesso(@PathVariable Long id) {
		return business.listarPorProcesso(id);
	}

	@PostMapping("/parecer")
	public Parecer incluir(@RequestBody Parecer parecer) {
		return business.incluir(parecer);
	}
}
