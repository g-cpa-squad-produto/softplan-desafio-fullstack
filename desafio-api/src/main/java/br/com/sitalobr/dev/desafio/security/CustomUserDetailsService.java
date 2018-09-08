package br.com.sitalobr.dev.desafio.security;

import br.com.sitalobr.dev.desafio.entity.Usuario;
import br.com.sitalobr.dev.desafio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        Usuario user = this.usuarioRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado a partir do username: " + username)
                );

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        Usuario user = this.usuarioRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Usuário não encontrado a partir do id: " + id)
        );

        return UserPrincipal.create(user);
    }
}
