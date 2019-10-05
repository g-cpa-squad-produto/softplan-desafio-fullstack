package com.isadora.backendapi.security.service;

import com.isadora.backendapi.domain.Usuario;
import com.isadora.backendapi.security.jwt.JwtUserFactory;
import com.isadora.backendapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UsuarioService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    		Usuario user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Usuario nao encontrado '%s'.", email));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}