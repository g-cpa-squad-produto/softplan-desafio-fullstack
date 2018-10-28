package br.com.softplan.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.softplan.entity.Usuario;
import br.com.softplan.enums.PerfilEnum;

public class JwtUserFactory {

	private JwtUserFactory() {
	}
	
	public static JwtUser create(Usuario user) {
		return new JwtUser(user.getId(), 
				user.getEmail(), 
				user.getSenha(), 
				mapToGrantedAuthorities(user.getPerfil()));
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfil) {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfil.toString()));
		return authorities;
	}
}
