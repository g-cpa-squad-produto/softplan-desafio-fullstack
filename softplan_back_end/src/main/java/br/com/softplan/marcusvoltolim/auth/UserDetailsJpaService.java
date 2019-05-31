package br.com.softplan.marcusvoltolim.auth;

import br.com.softplan.marcusvoltolim.model.Usuario;
import br.com.softplan.marcusvoltolim.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsJpaService implements UserDetailsService {
	
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UserDetailsJpaService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		
		return new User(usuario.getLogin(), usuario.getSenha(), authorities(usuario));
	}
	
	private Collection<? extends GrantedAuthority> authorities(Usuario usuario) {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + usuario.getPermissao()));
	}
	
}
