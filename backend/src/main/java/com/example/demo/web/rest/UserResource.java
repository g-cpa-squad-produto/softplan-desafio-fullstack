package com.example.demo.web.rest;


import com.example.demo.entity.User;
import com.example.demo.entity.enumeration.Role;
import com.example.demo.repository.UserRepository;
import com.example.demo.web.rest.errors.BadRequestAlertException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller para gerenciar {@link com.example.demo.entity.User}
 */
@RestController
@RequestMapping("/api")
@Transactional
@Log4j2
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/usuarios")
    public ResponseEntity<User> createUsuario(@Valid @RequestBody User user) throws URISyntaxException {
        log.debug("REST request to save User : {}", user);
        if (user.getId() != null) {
            throw new BadRequestAlertException("Um novo usuario nao pode ter um ID", "usuario", "idExists");
        }
        User result = userRepository.save(user);
        return ResponseEntity.created(new URI("/api/usuarios/" + result.getId()))
                .header("id", result.getId().toString())
                .body(result);
    }

    @PutMapping("/usuarios")
    public ResponseEntity<User> updateUsuario(@Valid @RequestBody User user) throws URISyntaxException {
        log.debug("REST request to update User : {}", user);
        if (user.getId() == null) {
            throw new BadRequestAlertException("Id inválido", "usuario", "idNull");
        }
        User result = userRepository.save(user);
        return ResponseEntity.ok()
                .header("id", result.getId().toString())
                .body(result);
    }

    @GetMapping("/usuarios")
    public List<User> getAllUsuarios() {
        log.debug("REST request to get all Usuarios");
        return userRepository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<User> getUsuario(@PathVariable Long id) {
        log.debug("REST request to get Usuario : {}", id);

        return userRepository.findById(id)
                .map((user) -> ResponseEntity.ok().body(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        log.debug("REST request to delete Usuario : {}", id);
        userRepository.deleteById(id);
        return ResponseEntity
                .noContent()
                .header("id", id.toString())
                .build();
    }

    @GetMapping("/usuarios/finalizador/")
    public ResponseEntity<List<User>> getUsuarioFinalizador() {
        log.debug("REST request to get all Usuarios by role finalizador");
        return ResponseEntity.ok().body( userRepository.findAllByRole(Role.FINALIZADOR) );
    }
}
