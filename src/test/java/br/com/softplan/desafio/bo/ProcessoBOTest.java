package br.com.softplan.desafio.bo;

import br.com.softplan.desafio.models.Perfil;
import br.com.softplan.desafio.models.Processo;
import br.com.softplan.desafio.models.Status;
import br.com.softplan.desafio.models.Usuario;
import br.com.softplan.desafio.repository.ProcessoRepository;
import br.com.softplan.desafio.repository.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class ProcessoBOTest {

    @InjectMocks
    private ProcessoBO processoBO;

    @Mock
    private ProcessoRepository processoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAllProcessos() {

        Processo processo1 = new Processo();
        processo1.setCodigo(12345L);
        processo1.setStatus(Status.PDT);

        Processo processo2 = new Processo();
        processo2.setCodigo(12345L);
        processo2.setStatus(Status.PDT);

        Processo processo3 = new Processo();
        processo3.setCodigo(12345L);
        processo3.setStatus(Status.PDT);

        when(processoRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(processo1, processo2, processo3)));

        List<Processo> processos = processoBO.findAll();

        assertNotNull(processos);
        assertFalse(processos.isEmpty());

        List<Long> codigos = processos.stream().map(Processo::getCodigo).collect(toList());

        assertTrue(codigos.contains(processo1.getCodigo()));
        assertTrue(codigos.contains(processo2.getCodigo()));
        assertTrue(codigos.contains(processo3.getCodigo()));

    }

    @Test
    public void shouldCreateProcesso() {

        Processo processo = new Processo();
        processo.setCodigo(12345L);
        processo.setStatus(Status.PDT);

        Usuario usuario = new Usuario("Milton Jacomini", Perfil.TRI);

        when(processoRepository.save(eq(processo))).thenReturn(processo);
        when(usuarioRepository.findByCodigoAndPerfilTriador(eq(usuario.getCodigo()))).thenReturn(Optional.of(usuario));

        Processo saved = processoBO.criar(processo, usuario.getCodigo());

        assertNotNull(saved);
        assertNotNull(saved.getDataCadastro());
        assertEquals(Status.PDT, saved.getStatus());

    }

    @Test(expected = RuntimeException.class)
    public void shouldNotCreateProcesso() {

        Processo processo = new Processo();
        processo.setCodigo(12345L);
        processo.setStatus(Status.PDT);

        Usuario usuario = new Usuario("Milton Jacomini", Perfil.TRI);

        when(usuarioRepository.findByCodigoAndPerfilFinalizador(eq(usuario.getCodigo()))).thenReturn(Optional.ofNullable(null));
        when(processoRepository.save(eq(processo))).thenReturn(processo);

        Processo saved = processoBO.criar(processo, usuario.getCodigo());

        assertNotNull(saved);
        assertNotNull(saved.getDataCadastro());
        assertEquals(Status.PDT, saved.getStatus());

    }

    @Test
    public void shouldFinishProcesso() {

        Processo processo = new Processo();
        processo.setCodigo(12345L);
        processo.setStatus(Status.PDT);

        Usuario usuario = new Usuario("Milton Jacomini", Perfil.FIN);

        when(usuarioRepository.findByCodigoAndPerfilFinalizador(eq(usuario.getCodigo()))).thenReturn(Optional.of(usuario));
        when(processoRepository.getByCodigo(eq(processo.getCodigo()))).thenReturn(Optional.of(processo));
        when(processoRepository.save(eq(processo))).thenReturn(processo);

        Processo updated = processoBO.finalizar(processo, usuario.getCodigo());

        assertNotNull(updated);
        assertEquals(Status.FNL, updated.getStatus());
        assertNotNull(updated.getDataFinalizado());
        assertEquals(usuario, updated.getUsuarioFinalizador());

    }

    @Test(expected = RuntimeException.class)
    public void shouldNotFinishProcesso() {

        Processo processo = new Processo();
        processo.setCodigo(12345L);
        processo.setStatus(Status.PDT);

        Usuario usuario = new Usuario("Milton Jacomini", Perfil.TRI);

        when(usuarioRepository.findByCodigoAndPerfilFinalizador(eq(usuario.getCodigo()))).thenReturn(Optional.ofNullable(null));
        when(processoRepository.getByCodigo(eq(processo.getCodigo()))).thenReturn(Optional.of(processo));
        when(processoRepository.save(eq(processo))).thenReturn(processo);

        processoBO.finalizar(processo, usuario.getCodigo());

    }
}
