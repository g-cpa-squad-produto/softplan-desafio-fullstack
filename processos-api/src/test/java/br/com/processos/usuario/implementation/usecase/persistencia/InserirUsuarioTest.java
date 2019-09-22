package br.com.processos.usuario.implementation.usecase.persistencia;

import br.com.processos.usuario.implementation.repository.UsuarioRepository;
import br.com.processos.usuario.specification.entity.Usuario;
import br.com.processos.usuario.specification.exception.EmailJaCadastradoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InserirUsuarioTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private InserirUsuario inserirUsuario;

    @Test
    public void deveInserirOUsuarioComSucesso() {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("Usuário Teste");
        novoUsuario.setEmail("usuarioteste@teste.com.br");

        when(usuarioRepository.save(novoUsuario)).thenReturn(novoUsuario);

        Usuario usuarioPersistido = inserirUsuario.executar(novoUsuario);

        assertEquals(usuarioPersistido, novoUsuario);
    }

    @Test(expected = EmailJaCadastradoException.class)
    public void naoDeveInserirUsuarioComEmailDuplicado() {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("Usuário Teste");
        novoUsuario.setEmail("usuarioteste@teste.com.br");

        Usuario usuarioExistente = new Usuario();
        novoUsuario.setNome("Usuário Existente");
        novoUsuario.setEmail("usuarioteste@teste.com.br");

        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuarioExistente));

        inserirUsuario.executar(novoUsuario);
        verifyNoMoreInteractions(usuarioRepository.save(novoUsuario));
    }
}
