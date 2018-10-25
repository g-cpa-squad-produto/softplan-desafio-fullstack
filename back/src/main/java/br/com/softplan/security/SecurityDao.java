package br.com.softplan.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.softplan.usuario.modelos.Usuario;
import br.com.softplan.usuario.repository.UsuarioRepository;

/**
 * @author emanuel
 *
 */
public class SecurityDao {

	@Autowired
	UsuarioRepository repository;

	public Optional<Usuario> obterUsuaruio(String login) {
		Usuario usuario = repository.buscarUsuarioPeloLogin(login);
		return Optional.ofNullable(usuario);
	}
}
