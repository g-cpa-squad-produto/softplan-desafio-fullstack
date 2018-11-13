package com.miratanlehmkuhl.backend.model;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.miratanlehmkuhl.backend.dto.UserNewDTO;
import com.miratanlehmkuhl.backend.dto.UserUpdateDTO;
import com.miratanlehmkuhl.backend.enums.Role;

@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@Column(unique = true)
	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<UserAuthority> authorities = new HashSet<>(0);

	public User() {
		
	}
	
	public User(String email, String password, Set<UserAuthority> authorities) {
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public User(UserNewDTO user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.grantRole(Role.valueOf(user.getRole()));
	}

	public User(UserUpdateDTO user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.grantRole(Role.valueOf(user.getRole()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	@Override
	public Set<UserAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<UserAuthority> authorities) {
		this.authorities = authorities;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return email;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	public Set<Role> getRoles() {
		Set<Role> roles = EnumSet.noneOf(Role.class);
		if (authorities != null) {
			for (UserAuthority authority : authorities) {
				roles.add(Role.valueOf(authority));
			}
		}
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		for (Role role : roles) {
			grantRole(role);
		}
	}

	public void grantRole(Role role) {
		if (authorities == null) {
			authorities = new HashSet<UserAuthority>();
		}
		authorities.add(role.asAuthorityFor(this));
	}

	public void revokeRole(Role role) {
		if (authorities != null) {
			authorities.remove(role.asAuthorityFor(this));
		}
	}

	public boolean hasRole(Role role) {
		return authorities.contains(role.asAuthorityFor(this));
	}

	@JsonProperty("role")
	public String role() {
		return getRoles().toString().replace("[", "").replace("]", "");
	}

}
