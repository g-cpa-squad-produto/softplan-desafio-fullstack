package br.com.processos.usuario.implementation.usecase.persistencia;

import br.com.processos.processo.specification.IProcessoUsuario;
import br.com.processos.usuario.implementation.repository.UsuarioRepository;
import br.com.processos.usuario.specification.entity.Usuario;
import br.com.processos.usuario.specification.exception.UsuarioNaoExisteException;
import br.com.processos.usuario.specification.exception.UsuarioRelacionadoAProcessosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RemoverUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IProcessoUsuario iProcessoUsuario;

    public void executar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        validarUsuarioExistente(usuario);
        validarUsuarioRelacionadoAProcessos(id);
        usuarioRepository.delete(usuario);
    }

    private void validarUsuarioRelacionadoAProcessos(Long id) {
        if (iProcessoUsuario.verificarUsuarioRelacionadoProcessos(id)) {
            throw new UsuarioRelacionadoAProcessosException();
        }
    }

    private void validarUsuarioExistente(Usuario usuario) {
        if (Objects.isNull(usuario)) {
            throw new UsuarioNaoExisteException();
        }
    }
}
