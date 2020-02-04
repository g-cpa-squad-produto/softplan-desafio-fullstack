package br.com.sofplan.processos.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sofplan.processos.models.Usuario;
import br.com.sofplan.processos.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository userRepository) {
        this.usuarioRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
    	
    	Optional<Usuario> optional = usuarioRepository.findByEmail(email);
    	Usuario usuario = optional.orElseThrow(() -> new UsernameNotFoundException(email));
    	
        return User//
                .withUsername(usuario.getEmail())//
                .password(usuario.getSenha())//
                .authorities(List.of(usuario.getPerfil()))//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
