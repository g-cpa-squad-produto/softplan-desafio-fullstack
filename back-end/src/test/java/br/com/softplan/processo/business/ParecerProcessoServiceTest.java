package br.com.softplan.processo.business;

import br.com.softplan.processo.dto.ParecerProcessoDTO;
import br.com.softplan.processo.entity.ParecerProcesso;
import br.com.softplan.processo.entity.Processo;
import br.com.softplan.processo.exception.ParecerProcessoNaoExistenteException;
import br.com.softplan.processo.exception.ProcessoNaoExistenteException;
import br.com.softplan.processo.exception.UsuarioJaPossuiParecerProcessoException;
import br.com.softplan.processo.repository.ParecerProcessoRepository;
import br.com.softplan.processo.repository.ProcessoRepository;
import br.com.softplan.security.business.UsuarioLogadoService;
import br.com.softplan.security.dto.UsuarioDTO;
import br.com.softplan.security.entity.Papel;
import br.com.softplan.security.entity.Usuario;
import br.com.softplan.security.exception.UsuarioNaoExistenteException;
import br.com.softplan.security.repository.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParecerProcessoServiceTest {

    @InjectMocks
    private ParecerProcessoService parecerProcessoService;

    @Mock
    private ParecerProcessoRepository repository;

    @Mock
    private ProcessoRepository processoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void buscarTodosTest(){

        Long processoId = 88L;

        ParecerProcessoDTO parecerProcessoDTO = new ParecerProcessoDTO();
        parecerProcessoDTO.setId(774L);

        List<ParecerProcessoDTO> pareceresConsulta = Arrays.asList(parecerProcessoDTO);

        when(repository.buscar(processoId)).thenReturn(pareceresConsulta);

        List<ParecerProcessoDTO> pareceres = parecerProcessoService.buscarTodos(processoId);

        assertEquals(pareceres.size(), pareceresConsulta.size());
        assertEquals(pareceres.get(0), pareceresConsulta.get(0));
    }

    @Test
    public void incluirParecerTest(){

        Long parecerId = 88L;

        ParecerProcessoDTO parecerProcessoDTO = new ParecerProcessoDTO();
        parecerProcessoDTO.setParecer("Parecer Test");

        ParecerProcesso parecerProcesso = new ParecerProcesso();
        parecerProcesso.setId(parecerId);
        parecerProcesso.setUsuario(new Usuario());
        parecerProcesso.getUsuario().setPapel(new Papel());
        parecerProcesso.setProcesso(new Processo());

        when(repository.findById(parecerId)).thenReturn(Optional.of(parecerProcesso));

        parecerProcessoService.incluirParecer(parecerId, parecerProcessoDTO);

        assertEquals(parecerProcesso.getParecer(), parecerProcessoDTO.getParecer());
        verify(repository, times(1)).save(parecerProcesso);
    }

    @Test(expected = ParecerProcessoNaoExistenteException.class)
    public void incluirParecerErrorTest() {

        Long parecerId = 88L;

        ParecerProcessoDTO parecerProcessoDTO = new ParecerProcessoDTO();

        when(repository.findById(parecerId)).thenReturn(Optional.empty());

        parecerProcessoService.incluirParecer(parecerId, parecerProcessoDTO);
    }

    @Test
    public void buscarAtualTest(){

        Processo processo = new Processo();
        processo.setId(774L);

        Usuario usuario = new Usuario();
        usuario.setId(88L);

        ParecerProcesso parecerProcesso = new ParecerProcesso();
        parecerProcesso.setId(555L);
        parecerProcesso.setUsuario(new Usuario());
        parecerProcesso.getUsuario().setPapel(new Papel());
        parecerProcesso.setProcesso(new Processo());

        when(processoRepository.findById(processo.getId())).thenReturn(Optional.of(processo));
        when(usuarioLogadoService.getUsuarioLogado()).thenReturn(usuario);
        when(repository.buscarPorUsuario(processo.getId(), usuario.getId())).thenReturn(parecerProcesso);

        ParecerProcessoDTO parecerProcessoDTO = parecerProcessoService.buscarAtual(processo.getId());

        assertEquals(parecerProcessoDTO.getId(), parecerProcessoDTO.getId());
    }

    @Test
    public void adicionarUsuarioTest(){

        Processo processo = new Processo();
        processo.setId(774L);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(88L);

        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());

        when(usuarioRepository.findById(usuarioDTO.getId())).thenReturn(Optional.of(usuario));
        when(processoRepository.findById(processo.getId())).thenReturn(Optional.of(processo));

        parecerProcessoService.adicionarUsuario(processo.getId(), usuarioDTO);

        ArgumentCaptor<ParecerProcesso> parecerProcessoCaptor = ArgumentCaptor.forClass(ParecerProcesso.class);

        verify(repository, times(1)).save(parecerProcessoCaptor.capture());

        assertEquals(parecerProcessoCaptor.getValue().getProcesso().getId(), processo.getId());
        assertEquals(parecerProcessoCaptor.getValue().getUsuario().getId(), usuario.getId());
    }

    @Test(expected= UsuarioJaPossuiParecerProcessoException.class)
    public void adicionarUsuarioJaExistenteTest(){

        Processo processo = new Processo();
        processo.setId(774L);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(88L);

        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());


        when(usuarioRepository.findById(usuarioDTO.getId())).thenReturn(Optional.of(usuario));
        when(processoRepository.findById(processo.getId())).thenReturn(Optional.of(processo));
        when(repository.buscarPorUsuario(processo.getId(), usuario.getId())).thenReturn(new ParecerProcesso());

        parecerProcessoService.adicionarUsuario(processo.getId(), usuarioDTO);
    }

    @Test(expected= ProcessoNaoExistenteException.class)
    public void adicionarUsuarioProcessoInvalidoTest(){

        Processo processo = new Processo();
        processo.setId(774L);

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        when(processoRepository.findById(processo.getId())).thenReturn(Optional.empty());

        parecerProcessoService.adicionarUsuario(processo.getId(), usuarioDTO);
    }

    @Test(expected= UsuarioNaoExistenteException.class)
    public void adicionarUsuarioUsuarioInvalidoTest(){

        Processo processo = new Processo();
        processo.setId(774L);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(88L);

        when(processoRepository.findById(processo.getId())).thenReturn(Optional.of(processo));
        when(usuarioRepository.findById(usuarioDTO.getId())).thenReturn(Optional.empty());

        parecerProcessoService.adicionarUsuario(processo.getId(), usuarioDTO);
    }

}

