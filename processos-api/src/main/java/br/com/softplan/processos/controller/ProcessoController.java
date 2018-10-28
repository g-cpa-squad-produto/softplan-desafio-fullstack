package br.com.softplan.processos.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.softplan.processos.exception.GenericException;
import br.com.softplan.processos.model.Processo;
import br.com.softplan.processos.service.ServicoProcesso;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

    @Autowired
    private ServicoProcesso servicoProcesso;

    @GetMapping
    public ResponseEntity<List<Processo>> selecionarTodos() throws GenericException {
	// Seleciona todos os processo
	return ResponseEntity.ok().body(servicoProcesso.selecionarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<Processo> selecionarProcessoPorId(@PathVariable Long id) throws GenericException {
	// Pega o processo
	Processo processo = servicoProcesso.selecionarProcessoPorId(id);

	// Caso não encontre o processo retorna 404
	return processo != null ? ResponseEntity.ok(processo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Processo> adicionarProcesso(@Valid @RequestBody Processo processo) throws GenericException {
	// Seta a data de criação
	processo.setDataCriacao(LocalDate.now());

	Processo processoCriado = servicoProcesso.adicionarProcesso(processo);

	// Cria a uri que será retornada no location, mostrando como acessar o novo
	// conteúdo
	URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
	        .buildAndExpand(processoCriado.getCodigo()).toUri();

	return ResponseEntity.created(uri).body(processo);
    }
}
