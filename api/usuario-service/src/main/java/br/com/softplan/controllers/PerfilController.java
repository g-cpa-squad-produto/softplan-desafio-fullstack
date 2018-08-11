package br.com.softplan.controllers;

import br.com.softplan.dto.PerfilDTO;
import br.com.softplan.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService service;

    @GetMapping("/{codigo}")
    public PerfilDTO buscarPerfil(@PathVariable("codigo") Long codigo) {
        return service.buscarPerfil(codigo);
    }

    @GetMapping
    public List<PerfilDTO> listarPerfis() {
        return service.listarPerfis();
    }

    @PostMapping
    public void salvarPerfil(@Valid @RequestBody PerfilDTO perfil) {
        service.salvarPerfil(perfil);
    }

    @PutMapping
    public void atualizarPerfil(@Valid @RequestBody PerfilDTO perfil) {
        service.salvarPerfil(perfil);
    }

    @DeleteMapping
    public void excluirPerfil(@Valid @RequestBody PerfilDTO perfil) {
        service.excluirPerfil(perfil);
    }
}
