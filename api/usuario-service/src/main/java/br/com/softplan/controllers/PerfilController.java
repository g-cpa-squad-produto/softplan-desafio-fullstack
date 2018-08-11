package br.com.softplan.controllers;

import br.com.softplan.dto.PerfilDTO;
import br.com.softplan.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService service;

    @PostMapping
    public void salvarPerfil(@Valid @RequestBody PerfilDTO perfil) {
        service.salvarPerfil(perfil);
    }
}
