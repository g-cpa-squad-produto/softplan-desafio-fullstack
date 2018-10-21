package com.softplan.thiagobernardo.usuario.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.softplan.thiagobernardo.usuario.entity.Usuario;
import com.softplan.thiagobernardo.usuario.repository.UsuarioRepository;

@RestController
public class UsuarioApiController {

	@Autowired
	private UsuarioRepository usuarioRepository;


	@GetMapping("usuarios")
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("usuarios/{usuarioId}/")
	public Usuario trazerUsuario(@PathVariable Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
	}

	@PostMapping("usuarios")
	public Usuario criarUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@PutMapping("usuarios/{usuarioId}")
	public Usuario alterarUsuario(@PathVariable Long usuarioId, @Valid @RequestBody Usuario usuarioRequest) {
		return usuarioRepository.findById(usuarioId).map(usuario -> {
			usuario.setNome(usuarioRequest.getNome());
			usuario.setLogin(usuarioRequest.getLogin());
			usuario.setSenha(usuarioRequest.getSenha());
			return usuarioRepository.save(usuario);
		}).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
	}

	@DeleteMapping("/usuarios/{usuarioId}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Long usuarioId) {
		if (!usuarioRepository.existsById(usuarioId)) {
			throw new RuntimeException("Usuario não encontrado!");
		}

		return usuarioRepository.findById(usuarioId).map(usuario -> {
			usuarioRepository.delete(usuario);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
	}

}
