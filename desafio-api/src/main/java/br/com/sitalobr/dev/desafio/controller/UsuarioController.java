package br.com.sitalobr.dev.desafio.controller;

import br.com.sitalobr.dev.desafio.dto.UsuarioCadastroDTO;
import br.com.sitalobr.dev.desafio.entity.Usuario;
import br.com.sitalobr.dev.desafio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private UsuarioService getService() {
        return this.usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody UsuarioCadastroDTO usuarioCadastroDTO) {
        Usuario result = this.getService().create(usuarioCadastroDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/usuarios/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).build();
    }
}
