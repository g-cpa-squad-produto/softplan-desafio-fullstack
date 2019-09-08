package br.com.softplan.security.business;

import br.com.softplan.security.dto.UsuarioLogadoDTO;
import br.com.softplan.security.entity.Usuario;
import br.com.softplan.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Loggeduser {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioLogadoDTO getUsuarioLogado(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        auth.getPrincipal();

        Usuario usuario = usuarioRepository.buscar(auth.getName());

        return UsuarioLogadoDTO.builder()
                .email(usuario.getEmail())
                .id(usuario.getId())
                .nome(usuario.getNome())
                .build();
    }
}
