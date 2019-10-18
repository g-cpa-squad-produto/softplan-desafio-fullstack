package com.desafiosoftplan.backend.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.desafiosoftplan.backend.model.Usuario;
import com.desafiosoftplan.backend.service.UsuarioService;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioService us;
	
	public ImplementsUserDetailsService() {
		if(us==null) {
			us = new UsuarioService();
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = us.get(login);
        UserBuilder builder = null;
		
		if(usuario != null){
            builder = org.springframework.security.core.userdetails.User.withUsername(usuario.getLogin());
            builder.password(usuario.getPassword());
            List<GrantedAuthority> listaDePermissoes = new ArrayList<>();
            usuario.getRoles().stream().forEach((permissao) -> {
            	GrantedAuthority perm = new SimpleGrantedAuthority(permissao.getNomeRole());
                listaDePermissoes.add(perm);
            });
            builder.authorities(listaDePermissoes);
		}else {
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		return builder.build();
	}

	public UsuarioService getUs() {
		return us;
	}

	public void setUs(UsuarioService us) {
		this.us = us;
	}

}
