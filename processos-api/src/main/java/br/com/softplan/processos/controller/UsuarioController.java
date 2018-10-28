package br.com.softplan.processos.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.softplan.processos.exception.GenericException;
import br.com.softplan.processos.model.Usuario;
import br.com.softplan.processos.service.ServicoUsuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private ServicoUsuario servicoUsuario;

    @GetMapping
    public ResponseEntity<List<Usuario>> selecionarTodos() throws GenericException {
	// Seleciona todos os usuários
	return ResponseEntity.ok().body(servicoUsuario.selecionarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> selecionarUsuarioPorId(@PathVariable Long id) throws GenericException {
	// Pega o usuário
	Usuario usuario = servicoUsuario.selecionarUsuarioPorId(id);

	// Caso não encontre o usuário retorna 404
	return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@Valid @RequestBody Usuario usuario) throws GenericException {
	Usuario usuarioCriado = servicoUsuario.adicionarUsuario(usuario);

	// Cria a uri que será retornada no location, mostrando como acessar o novo
	// conteúdo
	URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
	        .buildAndExpand(usuarioCriado.getCodigo()).toUri();

	return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario)
            throws GenericException {
	// Seta o código ao usuário
	usuario.setCodigo(id);

	// Atualiza os dados do usuário
	Usuario usuarioAtualizado = servicoUsuario.atualizarUsuario(id, usuario);

	return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario(@PathVariable Long id) throws GenericException {
	servicoUsuario.excluirUsuario(id);
    }

    @GetMapping("/finalizador")
    public void selecionarUsuariosFinalizador() {

    }
}
