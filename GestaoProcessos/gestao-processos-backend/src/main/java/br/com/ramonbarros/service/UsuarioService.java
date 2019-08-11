package br.com.ramonbarros.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ramonbarros.entity.Usuario;
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

	public void salvar(Usuario usuario) {
		if(usuario.getId() == null) {
			usuario.setSenha(PasswordUtils.crypt(usuario.getSenha()));
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
		novoUsuario.setNome(usuario.getNome());
		novoUsuario.getPerfis().addAll(usuario.getPerfis());
	}

	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).get();
	}
}
