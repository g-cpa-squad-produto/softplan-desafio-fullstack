package br.com.softplan.usuario.service;

import java.util.List;
import java.util.Optional;

import br.com.softplan.security.enums.PerfilEnum;
import br.com.softplan.usuario.modelos.Usuario;

/**
 * @author emanuel
 *
 */
public interface UsuarioService {

	public Optional<Usuario> buscarPorLogin(String email);

	public Usuario atualizarOuSalvar(Usuario usuario);

	public Usuario excluirUsuario(Integer id, Integer idUsuarioLogado);

	public List<Usuario> listarUsuarios(PerfilEnum perfil);

	public List<Usuario> listarTodosUsuarios();
}
