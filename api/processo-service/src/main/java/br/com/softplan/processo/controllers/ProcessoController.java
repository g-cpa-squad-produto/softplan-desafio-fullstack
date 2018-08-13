package br.com.softplan.processo.controllers;

import br.com.softplan.processo.entidades.ParecerProcesso;
import br.com.softplan.processo.entidades.Processo;
import br.com.softplan.processo.services.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/processo")
public class ProcessoController {

    @Autowired
    private ProcessoService service;

    @GetMapping("/{codigo}")
    public Processo buscarProcesso(@PathVariable("codigo") Long codigo) {
        return service.buscarProcesso(codigo);
    }

    @PostMapping
    public void salvarProcesso(@Valid @RequestBody Processo processo) {
        service.salvarProcesso(processo);
    }

    @PutMapping
    public void atualizarProcesso(@Valid @RequestBody Processo processo) {
        service.salvarProcesso(processo);
    }

    @DeleteMapping
    public void excluirProcesso(@Valid @RequestBody Processo processo) {
        service.excluirProcesso(processo);
    }

    @PostMapping("/parecer")
    public void salvarParecer(@Valid @RequestBody ParecerProcesso parecer) {
        service.salvarParecer(parecer);
    }

}
