package br.com.processo.prjdemo.service;

import java.util.List;

import br.com.processo.prjdemo.model.EnumPermissaoUsuario;
import br.com.processo.prjdemo.model.Usuario;

/**
 * 
 * @author Guilherme Sena
 * Interface para os servicos do Usuario
 */
public interface UsuarioService {
	List<Usuario> getTodosUsuarios();
	List<Usuario> getTodosUsuariosPermissao(String tipoPermissao);
	String getUsuarioLoginSenha(Usuario usuario);
	Usuario salvarUsuario(Usuario usuario);
	Usuario getUsuarioPorId(Long id);
	boolean removerUsuario(Long id);
	List<EnumPermissaoUsuario> lstEnumPermissao();
}
