package br.com.processos.usuario.implementation.usecase.busca;

import br.com.processos.usuario.implementation.repository.UsuarioRepository;
import br.com.processos.usuario.specification.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuscarUsuarioPorId {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario executar(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }
}
