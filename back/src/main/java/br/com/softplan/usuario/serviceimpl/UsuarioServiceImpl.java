package br.com.softplan.usuario.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.security.utils.SenhaUtils;
import br.com.softplan.usuario.modelos.Usuario;
import br.com.softplan.usuario.repository.UsuarioRepository;
import br.com.softplan.usuario.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

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
		usuario.setSenha(SenhaUtils.gerarBCrypt(usuario.getSenha()));
		Usuario usuarioSalvo = repository.save(usuario);
		return usuarioSalvo;
	}

	@Override
	public Usuario excluirUsuario(Integer id) {
		Usuario usuarioDoBanco = repository.findOne(id);
		usuarioDoBanco.setAtivo(Boolean.FALSE);
		usuarioDoBanco.setDeletado(Boolean.TRUE);
		return atualizarOuSalvar(usuarioDoBanco);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		List<Usuario> todosOsUsuarios = repository.listarUsuarios();
		return todosOsUsuarios;
	}

	@Override
	public List<Usuario> listarTodosUsuarios() {
		List<Usuario> todosOsUsuarios = repository.findAll();
		return todosOsUsuarios;
	}

}
