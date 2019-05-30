package com.softplan.processos.features.usuarios;

import com.softplan.processos.common.AbstractController;
import com.softplan.processos.common.PasswordBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController extends AbstractController<Usuario, Long> {

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario entity) {
        entity.setSenha(PasswordBuilder.toHash(entity.getSenha()));
        return super.save(entity);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        UsuarioRepository usuarioRepository = (UsuarioRepository) repository;

        Usuario usuarioLogin = usuarioRepository
                .findByLoginAcessoAndSenha(usuario.getLoginAcesso(), PasswordBuilder.toHash(usuario.getSenha()));
        if (nonNull(usuarioLogin)) {
            return new ResponseEntity(usuarioLogin, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

}
