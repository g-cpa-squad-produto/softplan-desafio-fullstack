package com.backendapp.controller;

import com.backendapp.model.Parecer;
import com.backendapp.model.Usuario;
import com.backendapp.repository.ParecerRepository;
import com.backendapp.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = {"/login/{email}/{senha}"})
    public ResponseEntity   loginByUserAndPass(@PathVariable String email, @PathVariable String senha){
        return repository.findByEmailAndSenha(email, senha)
                .map(record ->ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.ok().body(new Usuario()));
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }


    @PutMapping(path ="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Usuario usuario) {
        return repository.findById(id)
                .map(u -> {
                    u.setNome(usuario.getNome());
                    u.setEmail(usuario.getEmail());
                    u.setSenha(usuario.getSenha());
                    u.setTipo(usuario.getTipo());
                    Usuario updated = repository.save(u);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
