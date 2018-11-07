package br.com.processo.prjdemo.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.processo.prjdemo.model.EnumPermissaoUsuario;
import br.com.processo.prjdemo.model.Usuario;
import br.com.processo.prjdemo.repository.UsuarioRepository;

/**
 * 
 * @author Guilherme Sena
 * Fachada de servicos para a classe de usuario
 *
 */
@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository usuarioRepository;
    private TokenService tokenService;
		
	@Autowired
	UsuarioServiceImpl(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }
	
	/**
	 * @return retorna a lista com todos os usuarios
	 */
	@Override
	public List<Usuario> getTodosUsuarios() {
		return usuarioRepository.findAll();
	}

	/**
	 * @return retorna o usuario com o ide gerado ao salvar o objeto
	 */
	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		//SETA DATA
		if(usuario.getDataCriacao() == null){
			usuario.setDataCriacao(new Date());
		}
		return usuarioRepository.save(usuario);
	}
	
	/**
	 * @return remove usuario e retorna true caso a exclusao seja realizada com suceso ou false caso contrario 
	 */
	@Override
	public boolean removerUsuario(Long id) {
		try {
			usuarioRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;		
	}

	/**
	 * @return valida o login e retorna o token de autenticacao 
	 */
	@Override
	public String getUsuarioLoginSenha(Usuario usuario) {
		usuario = usuarioRepository.getUsuarioLoginSenha(usuario.getLogin(), usuario.getSenha());
		if(usuario != null){
			return tokenService.createToken(usuario);
		}
		return null;
	}
	
	/**
	 * retorna lista de todas as permissoes
	 */
	@Override
	public List<EnumPermissaoUsuario> lstEnumPermissao(){
		return Arrays.asList(EnumPermissaoUsuario.values());
		
	}
	
	/**
	 * retorna usuario pelo id
	 */
	@Override
	public Usuario getUsuarioPorId(Long id) {
		Usuario usu = null;
		try {
			usu = usuarioRepository.findById(id).get();
			usu.setSenha(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usu;
	}

	@Override
	public List<Usuario> getTodosUsuariosPermissao(String tipoPermissao) {
		EnumPermissaoUsuario tipoEnum = null;
		for (EnumPermissaoUsuario enumP : EnumPermissaoUsuario.values()) {
		     if(enumP.toString().toUpperCase().equals(tipoPermissao.toUpperCase())){
		    	 tipoEnum = enumP;
		     }
		 }
		return usuarioRepository.getLstUsuarioPorPermissao(tipoEnum);
	}
}
