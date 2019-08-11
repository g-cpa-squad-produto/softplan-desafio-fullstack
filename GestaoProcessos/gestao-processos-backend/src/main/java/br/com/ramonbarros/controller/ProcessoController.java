package br.com.ramonbarros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ramonbarros.entity.Processo;
import br.com.ramonbarros.service.ProcessoService;

@RestController
@RequestMapping("${processo.servlet.path}")
public class ProcessoController {
	
	@Autowired
	ProcessoService service;
	
	@GetMapping
    public ResponseEntity<List<Processo>> buscarTodos() {
        final List<Processo> lista = service.listar();
        return ResponseEntity.ok().body(lista);
    }

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Processo processo){
		service.salvar(processo);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody Processo processo, @PathVariable Long id) {
		processo.setId(id);
		processo = service.alterar(processo);
		return ResponseEntity.noContent().build();
	}
	
}
