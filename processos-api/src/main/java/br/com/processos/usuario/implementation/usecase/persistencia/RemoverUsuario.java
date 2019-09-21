package br.com.processos.usuario.implementation.usecase.persistencia;

import br.com.processos.usuario.implementation.repository.UsuarioRepository;
import br.com.processos.usuario.specification.entity.Usuario;
import br.com.processos.usuario.specification.exception.UsuarioNaoExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RemoverUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void executar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        validarUsuarioExistente(usuario);
        // TODO verificar usuario relacionado a processos
        usuarioRepository.delete(usuario);
    }

    private void validarUsuarioExistente(Usuario usuario) {
        if (Objects.isNull(usuario)) {
            throw new UsuarioNaoExisteException();
        }
    }
}
