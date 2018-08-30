package br.com.softplan.desafio.bo;

import br.com.softplan.desafio.models.Perfil;
import br.com.softplan.desafio.models.Usuario;
import br.com.softplan.desafio.models.UsuarioDTO;
import br.com.softplan.desafio.repository.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class UsuarioBOTest {

    @InjectMocks
    private UsuarioBO usuarioBO;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAllUsuarios() {

        Usuario milton_jacomini = new Usuario("Milton Jacomini", Perfil.ADM);
        Usuario gandalf = new Usuario("Gandalf", Perfil.TRI);
        Usuario dart_vader = new Usuario("Dart Vader", Perfil.FIN);

        when(usuarioRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(milton_jacomini, gandalf, dart_vader)));

        List<UsuarioDTO> usuarios = usuarioBO.search();

        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());

        List<String> nomes = usuarios.stream().map(usuario -> usuario.getNome()).collect(Collectors.toList());

        assertTrue(nomes.contains(milton_jacomini.getNome()));
        assertTrue(nomes.contains(gandalf.getNome()));
        assertTrue(nomes.contains(dart_vader.getNome()));

    }

    @Test
    public void shouldSaveUsuario() {

        Usuario milton_jacomini = new Usuario("Milton Jacomini", Perfil.ADM);

        when(usuarioRepository.save(eq(milton_jacomini))).thenReturn(milton_jacomini);

        Usuario result = usuarioBO.salva(milton_jacomini);

        assertNotNull(result);
        assertNotNull(result.getDataCadastro());

    }

    @Test
    public void shouldUpdateUsuario() {

        Usuario milton_jacomini = new Usuario("Milton Jacomini", Perfil.ADM);

        when(usuarioRepository.save(eq(milton_jacomini))).thenReturn(milton_jacomini);

        Usuario saved = usuarioBO.salva(milton_jacomini);

        assertNotNull(saved);
        assertNotNull(saved.getDataCadastro());
        assertNull(saved.getDataAlteracao());

        usuarioBO.atualiza(saved);

        assertNotNull(saved.getDataAlteracao());

    }

    @Test
    public void shouldDeleteUsuario() {

        Usuario milton_jacomini = new Usuario("Milton Jacomini", Perfil.ADM);

        when(usuarioRepository.save(eq(milton_jacomini))).thenReturn(milton_jacomini);

        Usuario saved = usuarioBO.salva(milton_jacomini);

        assertNotNull(saved);
        assertNotNull(saved.getDataCadastro());
        assertNull(saved.getDataAlteracao());

        when(usuarioRepository.findOne(eq(saved.getCodigo()))).thenReturn(milton_jacomini);

        Usuario deleted = usuarioBO.deleta(saved.getCodigo());

        assertNotNull(deleted.getDataExclusao());

    }


}
