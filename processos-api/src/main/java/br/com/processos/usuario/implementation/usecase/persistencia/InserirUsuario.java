package br.com.processos.usuario.implementation.usecase.persistencia;

import br.com.processos.usuario.implementation.repository.UsuarioRepository;
import br.com.processos.usuario.specification.entity.Usuario;
import br.com.processos.usuario.specification.exception.EmailJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InserirUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario executar(Usuario novoUsuario) {
        validarEmailExistente(novoUsuario.getEmail());
        return usuarioRepository.save(novoUsuario);
    }

    private void validarEmailExistente(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            throw new EmailJaCadastradoException();
        }
    }
}
