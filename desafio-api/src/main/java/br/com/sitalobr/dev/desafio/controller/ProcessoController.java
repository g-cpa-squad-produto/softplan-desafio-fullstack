package br.com.sitalobr.dev.desafio.controller;

import br.com.sitalobr.dev.desafio.dto.ProcessoCadastroDTO;
import br.com.sitalobr.dev.desafio.dto.ProcessoFinalizadorDTO;
import br.com.sitalobr.dev.desafio.entity.Processo;
import br.com.sitalobr.dev.desafio.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/processos")
public class ProcessoController {
    private final ProcessoService processoService;

    @Autowired
    public ProcessoController(ProcessoService processoService) {
        this.processoService = processoService;
    }

    private ProcessoService getService() {
        return this.processoService;
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody ProcessoCadastroDTO processoCadastroDTO, Principal principal) {
        Processo result = this.getService().create(processoCadastroDTO, principal.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/processos/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("finalizar")
    public ResponseEntity<?> finalizar(@Valid @RequestBody ProcessoFinalizadorDTO processoFinalizadorDTO) {
        this.getService().finalizar(processoFinalizadorDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        Iterable<Processo> result = this.getService().findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/finalizadores")
    public ResponseEntity<?> findAllFinalizadores(Principal principal) {
        Iterable<Processo> result = this.getService().findAllByFinalizador(principal.getName());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Processo result = this.getService().findById(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        this.getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}
