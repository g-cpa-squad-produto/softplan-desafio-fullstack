package com.softplan.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.softplan.backend.enumeration.Role;
import com.softplan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.softplan.backend.entity.User user = this.userRepository.findByUsername(username);
        if (user.getUsername().equals(username)) {
            return new User(user.getUsername(), user.getPassword(), mapToGrantedAuthorities(user.getRole()));
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities ;
    }
}