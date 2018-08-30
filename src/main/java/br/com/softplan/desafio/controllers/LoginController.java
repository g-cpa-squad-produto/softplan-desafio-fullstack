package br.com.softplan.desafio.controllers;

import br.com.softplan.desafio.bo.UsuarioBO;
import br.com.softplan.desafio.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioBO usuarioBO;

    @PostMapping
    public Usuario login(@RequestBody Usuario usuario) {

        return usuarioBO.findByNome(usuario.getNome());

    }

}
