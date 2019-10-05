package com.isadora.backendapi.controller;

import com.isadora.backendapi.domain.Usuario;
import com.isadora.backendapi.services.MapValidationErrorService;
import com.isadora.backendapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Iterable<Usuario> getAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }

//    @GetMapping("/{usuarioEmail}")
//    public ResponseEntity<?> getUsuarioByEmail(@PathVariable String usuarioEmail) {
//        Usuario usuario = usuarioService.findByEmail(usuarioEmail);
//
//        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
//    }

    @GetMapping("/{usuarioId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long usuarioId) {
        Optional<Usuario> usuario = usuarioService.findById(usuarioId);

        return new ResponseEntity<Optional<Usuario>>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{usuarioId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long usuarioId) {
        usuarioService.deleteUsuarioById(usuarioId);

        return new ResponseEntity<String>("Usuario '" + usuarioId + "' deletado.", HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createNewUsuario(@Valid @RequestBody Usuario usuario, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationServive(result);
        if (errorMap != null) {
            return errorMap;
        }
        usuario.setpassword(passwordEncoder.encode(usuario.getpassword()));
        Usuario usuarioNovo = usuarioService.save(usuario);
        return new ResponseEntity<Usuario>(usuarioNovo, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, @PathVariable Long id, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationServive(result);
        if (errorMap != null) {
            return errorMap;
        }
        usuario = usuarioService.update(id, usuario);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
    }
}
