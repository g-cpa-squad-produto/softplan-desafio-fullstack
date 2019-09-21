package br.com.processos.usuario.implementation.usecase.persistencia;

import br.com.processos.usuario.implementation.repository.UsuarioRepository;
import br.com.processos.usuario.specification.entity.Usuario;
import br.com.processos.usuario.specification.exception.EmailJaCadastradoException;
import br.com.processos.usuario.specification.exception.UsuarioNaoExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AtualizarUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario executar(Long id, Usuario usuarioModificado) {
        Usuario usuarioAtual = usuarioRepository.findById(id).orElse(null);
        validarUsuarioExistente(usuarioAtual);
        validarEmailDuplicado(id, usuarioModificado.getEmail());
        atualizarDadosUsuario(usuarioAtual, usuarioModificado);
        return usuarioRepository.save(usuarioAtual);
    }

    private void validarUsuarioExistente(Usuario usuarioAtual) {
        if (Objects.isNull(usuarioAtual)) {
            throw new UsuarioNaoExisteException();
        }
    }

    private void validarEmailDuplicado(Long id, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (Objects.nonNull(usuario) && !usuario.getId().equals(id)) {
            throw new EmailJaCadastradoException();
        }
    }

    private void atualizarDadosUsuario(Usuario usuarioAtual, Usuario usuarioModificado) {
        usuarioAtual.setNome(usuarioModificado.getNome());
        usuarioAtual.setEmail(usuarioModificado.getEmail());
        usuarioAtual.setTipo(usuarioModificado.getTipo());
    }
}
