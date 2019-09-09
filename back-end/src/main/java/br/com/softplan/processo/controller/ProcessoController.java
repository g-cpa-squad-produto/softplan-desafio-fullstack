package br.com.softplan.processo.controller;

import br.com.softplan.processo.business.ProcessoService;
import br.com.softplan.processo.entity.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService service;

    @PreAuthorize("hasRole('Processo.Visualizar')")
    @GetMapping
    public List<Processo> buscar(){
        return service.buscarTodos();
    }

    @PreAuthorize("hasRole('Processo.Criar')")
    @PostMapping
    public Processo criar(@RequestBody Processo processo){
        return service.criar(processo);
    }
}
