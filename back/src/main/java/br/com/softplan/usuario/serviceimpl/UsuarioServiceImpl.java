package br.com.softplan.usuario.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.security.enums.PerfilEnum;
import br.com.softplan.security.utils.SenhaUtils;
import br.com.softplan.usuario.modelos.Usuario;
import br.com.softplan.usuario.repository.UsuarioRepository;
import br.com.softplan.usuario.repository.UsuarioRepositoryDinamico;
import br.com.softplan.usuario.service.UsuarioService;

/**
 * @author emanuel
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioRepositoryDinamico repositoryDinamico;

	@Override
	public Optional<Usuario> buscarPorLogin(String login) {
		try {
			Usuario usuario = repository.buscarUsuarioPeloLogin(login);
			return Optional.ofNullable(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Usuario atualizarOuSalvar(Usuario usuario) {
		// Verifica se e um novo usuario
		if (usuario.getId() == null) {
			Optional<Usuario> usuarioDoBanco = buscarPorLogin(usuario.getLogin());
			if (!usuarioDoBanco.isPresent()) {
				usuario.setSenha(SenhaUtils.gerarBCrypt(usuario.getSenha()));
				repository.save(usuario);
			} else {
				// Verifica se nao tem usuarios repetidos
				return null;
			}
		} else {
			// Verifica se o usuario a ser editado tem senha
			if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
				Usuario usuarioDoBanco = repository.findOne(usuario.getId());
				usuario.setSenha(usuarioDoBanco.getSenha());
				repository.save(usuario);
			} else {
				// criptografa a nova senha
				usuario.setSenha(SenhaUtils.gerarBCrypt(usuario.getSenha()));
				repository.save(usuario);
			}
		}
		return usuario;
	}

	@Override
	public Usuario excluirUsuario(Integer id, Integer idUsuarioLogado) {
		// Usuario nao pode deletar a si mesmo
		if (id == idUsuarioLogado) {
			return null;
		}
		Usuario usuarioDoBanco = repository.findOne(id);
		usuarioDoBanco.setAtivo(Boolean.FALSE);
		usuarioDoBanco.setDeletado(Boolean.TRUE);
		return atualizarOuSalvar(usuarioDoBanco);
	}

	@Override
	public List<Usuario> listarUsuarios(PerfilEnum perfil) {
		List<Usuario> todosOsUsuarios = repositoryDinamico.listarUsuarios(perfil);
		return todosOsUsuarios;
	}

	@Override
	public List<Usuario> listarTodosUsuarios() {
		List<Usuario> todosOsUsuarios = repository.findAll();
		return todosOsUsuarios;
	}

}
