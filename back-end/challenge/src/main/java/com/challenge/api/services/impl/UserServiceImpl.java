package com.challenge.api.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenge.api.documents.User;
import com.challenge.api.documents.Role;
import com.challenge.api.repositories.RoleRepository;
import com.challenge.api.repositories.UserRepository;
import com.challenge.api.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<User> listAll() {
		return this.userRepository.findAll();
	}

	@Override
	public User listByID(String id) {
		return this.userRepository.findById(id).orElse(null);
	}

	@Override
	public User create(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		for(Iterator<Role> iter = user.getRoles().iterator(); iter.hasNext();) {
             Role role = iter.next();
             Role userRole = roleRepository.findByRole(role.getRole().toUpperCase());
     		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        }
		
		return this.userRepository.save(user);
	}

	@Override
	public User update(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		for(Iterator<Role> iter = user.getRoles().iterator(); iter.hasNext();) {
             Role role = iter.next();
             Role userRole = roleRepository.findByRole(role.getRole().toUpperCase());
     		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        }
		
		return this.userRepository.save(user);
	}

	@Override
	public void remove(String id) {
		this.userRepository.deleteById(id);
	}
	
	public User findUserByUsername(String username) {
	    return userRepository.findByUsername(username);
	}

	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
	    if(user != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
	        return buildUserForAuthentication(user, authorities);
	    } else {
	        throw new UsernameNotFoundException("username not found");
	    }
	}
}
