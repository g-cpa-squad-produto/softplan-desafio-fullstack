/**
 * 
 */
package br.com.desafiofullstack.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.desafiofullstack.domain.Usuario;
import br.com.desafiofullstack.repository.UsuarioRepository;
import br.com.desafiofullstack.service.UsuarioService;

/**
 * @author Delano Jr
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> save(Usuario usuario) {
		usuario.setUsuarioAtivo(true);
		return Optional.of(usuarioRepository.save(usuario));
	}

	@Override
	public Optional<List<Usuario>> saveAll(List<Usuario> usuarios) {
		return Optional.of(usuarioRepository.saveAll(usuarios));
	}

	@Override
	public Optional<List<Usuario>> findAll() {
		return Optional.of(usuarioRepository.findAll());
	}

	@Override
	public Optional<Usuario> findOne(Long id) {
		return Optional.of(usuarioRepository.getOne(id));
	}

	@Override
	public void delete(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	@Override
	public void deleteAll(List<Usuario> usuarios) {
		usuarioRepository.deleteAll(usuarios);
	}

	@Override
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public Optional<List<Usuario>> consultaUsuariosAtivos() {
		return Optional.of(usuarioRepository.findAll(Example.of(new Usuario(null, null, null, null, true, null))));
	}

}
