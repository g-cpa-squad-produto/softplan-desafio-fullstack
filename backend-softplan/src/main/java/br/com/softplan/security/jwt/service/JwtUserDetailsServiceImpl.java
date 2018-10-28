package br.com.softplan.security.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.softplan.entity.Usuario;
import br.com.softplan.security.jwt.JwtUserFactory;
import br.com.softplan.service.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioService.findByEmail(username);
		if(usuario == null)
			throw new UsernameNotFoundException("Usuário não cadastrado com o email " + username);

		return JwtUserFactory.create(usuario);
	}

}
