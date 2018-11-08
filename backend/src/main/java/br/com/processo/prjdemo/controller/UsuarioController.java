package br.com.processo.prjdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.processo.prjdemo.model.EnumPermissaoUsuario;
import br.com.processo.prjdemo.model.Usuario;
import br.com.processo.prjdemo.service.UsuarioService;

/**
 * 
 * @author Guilherme Sena
 * Rest's referentes a funcionalidades relacionadas ao usuario
 *
 */
@CrossOrigin(origins = "http://localhost:4200")////Permite chamadas de outra porta que nao a default 8080
@RequestMapping("/usuario")
@RestController
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * 
	 * @return recupera todos os usuarios cadastrados
	 */
	@GetMapping("/todos-usuario")
    public List<Usuario> getTodosUsuarios() {
		return usuarioService.getTodosUsuarios();
	}
	
	/**
	 * 
	 * @param id
	 * @return recupera usuario pelo id
	 */
	@GetMapping("/usuario-por-id/{id}")
    public Usuario getUsuarioPorId(@PathVariable Long id) {
		return usuarioService.getUsuarioPorId(id);
	}
	
	/**
	 * 
	 * @return retorna todas as permissoes cadastradas no enum
	 */
	@GetMapping("/todas-permissoes")
    public List<EnumPermissaoUsuario> getlstEnumPermissao() {
		return usuarioService.lstEnumPermissao();
	}
	
	/**
	 * 
	 * @param permissao
	 * @return retorna todos os usuarios com um determinado tipo de permissao
	 */
	@GetMapping("/todos-usuarios-permissao/{permissao}")
	public List<Usuario> getTodosUsuariosPermissao(@PathVariable String permissao) {
		return usuarioService.getTodosUsuariosPermissao(permissao);
	}
	
	/**
	 * 
	 * @param usuario
	 * @return metodo responsavel por validar login e se autorizado retorna um token valido
	 */
	@PostMapping("/login-usuario")
    public String geUsuarioLoginSenha(@RequestBody Usuario usuario) {
		String token = usuarioService.getUsuarioLoginSenha(usuario);
		return token;
	}
    
	/**
	 * 
	 * @param usuario
	 * @return retorno o usuario salvo
	 */
    @PostMapping("/usuario-salvar")
	public Usuario salvarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.salvarUsuario(usuario);
	}
    
    /**
     * 
     * @param id
     * @return remove usuario pelo id
     */
    @DeleteMapping("/usuario-remover/{id}")
	public boolean removerUsuario(@PathVariable Long id) {
    	return usuarioService.removerUsuario(id);
	}
}
