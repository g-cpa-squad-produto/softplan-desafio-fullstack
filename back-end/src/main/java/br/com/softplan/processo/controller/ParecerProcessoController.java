package br.com.softplan.processo.controller;

import br.com.softplan.processo.business.ParecerProcessoService;
import br.com.softplan.processo.dto.ParecerProcessoDTO;
import br.com.softplan.processo.entity.ParecerProcesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/processos/{processoId}/pareceres")
public class ParecerProcessoController {

    @Autowired
    private ParecerProcessoService service;

    @GetMapping
    public List<ParecerProcessoDTO> buscar(@PathVariable("processoId") Long processoId){
        return service.buscarTodos(processoId);
    }

    @PostMapping
    public ParecerProcesso criar(@PathVariable("processoId") Long processoId, @RequestBody ParecerProcesso parecerProcesso){
        return service.criar(processoId, parecerProcesso);
    }

    @PutMapping("/{id}")
    public ParecerProcesso editar(@PathVariable("id") Long id, @RequestBody ParecerProcesso parecerProcesso){
        return service.editar(id, parecerProcesso);
    }
}
