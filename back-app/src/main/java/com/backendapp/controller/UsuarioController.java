package com.backendapp.controller;

import com.backendapp.model.Usuario;
import com.backendapp.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/usuarios"})
public class UsuarioController {
    private UsuarioRepository repository;

    UsuarioController(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

}
