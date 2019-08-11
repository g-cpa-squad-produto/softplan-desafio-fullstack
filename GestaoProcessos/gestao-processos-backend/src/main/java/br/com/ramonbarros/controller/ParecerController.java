package br.com.ramonbarros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ramonbarros.entity.Parecer;
import br.com.ramonbarros.service.ParecerService;

@RestController
@RequestMapping("${parecer.servlet.path}")
public class ParecerController {
	
	@Autowired
	ParecerService service;
	
	@GetMapping(value="/{idProcesso}")
    public ResponseEntity<List<Parecer>> buscarPorProcesso(@PathVariable Long idProcesso) {
        final List<Parecer> lista = service.buscarPorProcesso(idProcesso);
        return ResponseEntity.ok().body(lista);
    }

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Parecer parecer){
		service.salvar(parecer);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
}
