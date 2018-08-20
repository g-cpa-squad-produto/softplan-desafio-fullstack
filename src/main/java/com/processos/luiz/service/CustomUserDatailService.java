package com.processos.luiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.processos.luiz.models.Usuario;
import com.processos.luiz.repository.UsuarioRepository;
@Component
public class CustomUserDatailService implements UserDetailsService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	public CustomUserDatailService(UsuarioRepository usuarioRepository) {
		usuarioRepository = usuarioRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(login);
		if(usuario == null){
			new UsernameNotFoundException("Usuario nao encontrado");
		}
		if(usuario.getRoles().isEmpty()){
			new UsernameNotFoundException("Roles nao encontrados");
		}
		System.out.println("LOGIN:::: "+usuario.getLogin());
	    List<GrantedAuthority> grantedAuthorities = usuario.getRoles().stream()
			    .map(authority -> new SimpleGrantedAuthority(authority.getNomeRole()))
			    .collect(Collectors.toList());
		return new User(usuario.getLogin(),usuario.getSenha(),grantedAuthorities);
	}

}
