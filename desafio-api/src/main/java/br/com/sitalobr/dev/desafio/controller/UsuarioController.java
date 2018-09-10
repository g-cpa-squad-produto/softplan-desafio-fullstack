package br.com.sitalobr.dev.desafio.controller;

import br.com.sitalobr.dev.desafio.dto.UsuarioCadastroDTO;
import br.com.sitalobr.dev.desafio.entity.Role;
import br.com.sitalobr.dev.desafio.entity.Usuario;
import br.com.sitalobr.dev.desafio.service.RoleService;
import br.com.sitalobr.dev.desafio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final RoleService roleService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, RoleService roleService) {
        this.usuarioService = usuarioService;
        this.roleService = roleService;
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

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(name = "role", required = false) String roleName) {
        Iterable<Usuario> result;

        if (roleName == null || roleName.equals(""))
            result = this.getService().findAll();
        else
            result = this.getService().findAllByRoleName(roleName);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/roles")
    public ResponseEntity<?> findAllRoles() {
        Iterable<Role> result = this.roleService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Usuario result = this.getService().findById(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<?> deleteById(@PathVariable Long id, Principal principal) {
        this.getService().delete(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}
