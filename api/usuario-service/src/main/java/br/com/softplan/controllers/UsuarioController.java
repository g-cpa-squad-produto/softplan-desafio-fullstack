package br.com.softplan.controllers;

import br.com.softplan.dto.UsuarioDTO;
import br.com.softplan.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/{codigo}")
    public UsuarioDTO buscarUauario(@PathVariable("codigo") Long codigo) {
        return service.buscarUsuario(codigo);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return service.listarUsuarios();
    }

    @PostMapping
    public void salvarUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        service.salvarUsuario(usuario);
    }

    @PutMapping
    public void atualizarUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        service.salvarUsuario(usuario);
    }

    @DeleteMapping
    public void excluirUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        service.excluirUsuario(usuario);
    }
}
