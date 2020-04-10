package com.teste.magnum.gerenciadorprocessosapi.resource;

import com.teste.magnum.gerenciadorprocessosapi.exception.ProcessoException;
import com.teste.magnum.gerenciadorprocessosapi.exception.UsuarioException;
import com.teste.magnum.gerenciadorprocessosapi.exception.http.NotFoundException;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.LoginDTO;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.ProcessoDTO;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.UsuarioDTO;
import com.teste.magnum.gerenciadorprocessosapi.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioDTO usuarioDTO) throws UsuarioException {
        return ResponseEntity.ok(usuarioService.save(usuarioDTO));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() throws NotFoundException {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UsuarioDTO> getUserByEmailAndPassword(@RequestBody LoginDTO loginDTO) throws NotFoundException, NoSuchAlgorithmException {
        return ResponseEntity.ok(usuarioService.getUserByEmailAndPassword(loginDTO));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usuarioService.getUsuario(id));
    }

}
