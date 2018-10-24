package br.com.softplan.processos.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.softplan.processos.model.Usuario;
import br.com.softplan.processos.service.ServicoUsuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @GetMapping
    public ResponseEntity<Iterable<Usuario>> selecionarTodos() {
	// Seleciona todos os usuários
	return ResponseEntity.ok().body(servicoUsuario.selecionarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> selecionarUsuarioPorId(@PathVariable Long id) {
	// Pega o usuário
	Usuario usuario = servicoUsuario.selecionarUsuarioPorId(id);

	// Caso não encontre o usuário retorna 404
	return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@Valid @RequestBody Usuario usuario) {
	Usuario usuarioCriado = servicoUsuario.adicionarUsuario(usuario);

	URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
	        .buildAndExpand(usuarioCriado.getCodigo()).toUri();

	return ResponseEntity.created(uri).body(usuario);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario(@PathVariable Long id) {
	servicoUsuario.excluirUsuario(id);
    }
}
