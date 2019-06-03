package com.softplan.processos.features.usuarios;

import com.softplan.processos.common.AbstractController;
import com.softplan.processos.common.PasswordBuilder;
import com.softplan.processos.exceptions.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController extends AbstractController<Usuario, Long> {

    @PostMapping(value = "/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        UsuarioRepository usuarioRepository = (UsuarioRepository) repository;

        Usuario usuarioLogin = usuarioRepository
                .findByEmailAndSenha(usuario.getEmail(), PasswordBuilder.toHash(usuario.getSenha()));
        if (nonNull(usuarioLogin)) {
            return new ResponseEntity(usuarioLogin, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/consulta", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Usuario>> findAllByPerfil(@RequestParam(value = "perfil") Perfil perfil) {
        UsuarioRepository usuarioRepository = (UsuarioRepository) repository;
        List<Usuario> usuariosByPerfil = usuarioRepository.findByPerfil(perfil);
        return new ResponseEntity(usuariosByPerfil, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario entity) {
        validate(entity);
        entity.setSenha(nonNull(entity.getSenha()) ? PasswordBuilder.toHash(entity.getSenha()) : null);
        return super.save(entity);
    }

    @Override
    protected void validate(Usuario entity) {
        super.validate(entity);
        UsuarioRepository usuarioRepository = (UsuarioRepository) repository;
        boolean emailInUse = usuarioRepository.existsByEmail(entity.getEmail());
        if (emailInUse) {
            throw new DomainException(String.format("O email informado %s já está em uso.", entity.getEmail()));
        }
    }

}
