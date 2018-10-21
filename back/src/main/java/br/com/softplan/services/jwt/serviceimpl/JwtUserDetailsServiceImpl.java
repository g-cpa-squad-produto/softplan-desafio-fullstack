package br.com.softplan.services.jwt.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.softplan.services.jwt.JwtUserFactory;
import br.com.softplan.usuario.modelos.Usuario;
import br.com.softplan.usuario.service.UsuarioService;

/**
 * @author emanuel
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> funcionario = usuarioService.buscarPorLogin(username);

		if (funcionario.isPresent())
			return JwtUserFactory.create(funcionario.get());
		else
			return null;
	}
}
