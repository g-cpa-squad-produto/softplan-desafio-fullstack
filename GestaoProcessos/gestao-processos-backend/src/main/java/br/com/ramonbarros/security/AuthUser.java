package br.com.ramonbarros.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.ramonbarros.enuns.PerfilEnum;

public class AuthUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public AuthUser() {
	}
	
	public AuthUser(Integer id, String nome, String senha, Set<PerfilEnum> perfis) {
		super();
		this.id = id;
		this.senha = senha;
		this.nome = nome;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return nome;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasRole(PerfilEnum perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}
}
