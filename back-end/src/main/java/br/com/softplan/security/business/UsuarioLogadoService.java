package br.com.softplan.security.business;

import br.com.softplan.security.entity.Usuario;
import br.com.softplan.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioLogadoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.buscar(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Usuario usuario) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        List<String> permissoes = repository.buscarPermissoes(usuario.getId());

        permissoes.forEach(permissao -> authorities.add(new SimpleGrantedAuthority("ROLE_" + permissao)));

        return authorities;
    }
}
