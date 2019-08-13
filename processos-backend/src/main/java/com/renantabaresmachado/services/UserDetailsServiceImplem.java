package com.renantabaresmachado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.renantabaresmachado.domains.Usuario;
import com.renantabaresmachado.repositories.UsuarioRepository;
import com.renantabaresmachado.security.UserSSecurity;

@Service
public class UserDetailsServiceImplem  implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository; 

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email);
		if(usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSSecurity(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
	}
	

}
