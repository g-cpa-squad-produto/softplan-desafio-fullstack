package br.com.softplan.processos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.processos.model.Usuario;
import br.com.softplan.processos.service.ServicoUsuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @GetMapping
    public ResponseEntity<Iterable<Usuario>> selecionarTodos() {
	return ResponseEntity.ok().body(servicoUsuario.selecionarTodos());
    }
}
