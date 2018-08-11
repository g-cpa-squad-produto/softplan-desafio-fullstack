package br.com.softplan.controllers;

import br.com.softplan.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/{codigo}")
    public String buscarUauario(@PathVariable("codigo") Integer codigo) {
        return service.buscarUsuario(codigo);
    }
}
