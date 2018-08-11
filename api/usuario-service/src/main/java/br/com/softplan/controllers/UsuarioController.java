package br.com.softplan.controllers;

import br.com.softplan.dto.UsuarioDTO;
import br.com.softplan.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/{codigo}")
    public UsuarioDTO buscarUauario(@PathVariable("codigo") Integer codigo) {
        return service.buscarUsuario(codigo);
    }

    @PostMapping
    public void salvarUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        service.salvarUsuario(usuario);
    }
}
