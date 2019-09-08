package br.com.softplan.processo.controller;

import br.com.softplan.processo.business.ParecerProcessoService;
import br.com.softplan.processo.dto.ParecerProcessoDTO;
import br.com.softplan.security.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/atual")
    public ParecerProcessoDTO buscarAtual(@PathVariable("processoId") Long processoId){
        return service.buscarAtual(processoId);
    }

    @PostMapping("/adicionarUsuario")
    public void adicionarUsuario(@PathVariable("processoId") Long processoId, @RequestBody UsuarioDTO usuarioDTO){
        service.adicionarUsuario(processoId, usuarioDTO);
    }

    @PostMapping("/{id}/incluirParecer")
    public ParecerProcessoDTO incluirParecer(@PathVariable("id") Long id, @RequestBody ParecerProcessoDTO parecerProcesso){
        return service.incluirParecer(id, parecerProcesso);
    }
}
