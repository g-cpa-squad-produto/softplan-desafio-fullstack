package br.com.ramonbarros.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ramonbarros.entity.Usuario;
import br.com.ramonbarros.enuns.PerfilEnum;
import br.com.ramonbarros.repository.UsuarioRepository;
import br.com.ramonbarros.utils.PasswordUtils;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario loadUserByUsername(String username) /* throws UsernameNotFoundException */ {

      final Optional<Usuario> user = this.usuarioRepository.findByLoginIgnoreCase(username);

      if ( user.isPresent() ) {
    	  return user.get();
      }else {
//    	  throw new UsernameNotFoundException("Usuário não encontrado!");
      }
	return null;
      
	}

	public Usuario findByLogin(String cdLogin) {
		Optional<Usuario> result = this.usuarioRepository.findByLoginIgnoreCase(cdLogin);
		
		if(result.isPresent()) {
			Usuario usuario = result.get();
			
			return usuario;
			
		} else {
			return null;			
		}
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public void salvar(Usuario usuario) throws Exception {
		if(usuario.getId() == null) {
//			Criar validação de senha
//			TODO - Alterar algoritmo de hash para senha. Está demorando. 
//			usuario.setSenha(PasswordUtils.crypt(usuario.getSenha()));
			boolean senhaPreenchida = null != usuario.getSenha() && !usuario.getSenha().isBlank();
			boolean confirmaSenhaPreenchida = null != usuario.getConfirmaSenha() && !usuario.getConfirmaSenha().isBlank();
			if(!(senhaPreenchida && confirmaSenhaPreenchida && usuario.getSenha().equals(usuario.getConfirmaSenha()))) {
				throw new Exception("Os Campos Senha e confirma Senha não podem ser diferentes");
			}
		}
		usuarioRepository.save(usuario);
		
	}

	public @Valid Usuario alterar(Usuario usuario) {
		Usuario usuarioAlteracao = usuarioRepository.findById(usuario.getId()).get();
		updateData(usuarioAlteracao, usuario);
		return usuarioRepository.save(usuarioAlteracao);
	}

	public void delete(Long id) {
		usuarioRepository.deleteById(id);
		
	}
	
	private void updateData(Usuario novoUsuario, Usuario usuario) {
		if(null != usuario.getNome() && !usuario.getNome().isEmpty()) {
			novoUsuario.setNome(usuario.getNome());
		}
		novoUsuario.clearPerfis();
		for(PerfilEnum p : usuario.getPerfis()) {
			novoUsuario.addPerfil(p);
		}
	}

	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).get();
	}
}
