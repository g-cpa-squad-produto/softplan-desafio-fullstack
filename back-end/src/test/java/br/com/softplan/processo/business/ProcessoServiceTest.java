package br.com.softplan.processo.business;

import br.com.softplan.processo.entity.Processo;
import br.com.softplan.processo.exception.ProcessoExistenteException;
import br.com.softplan.processo.repository.ProcessoRepository;
import br.com.softplan.security.business.UsuarioLogadoService;
import br.com.softplan.security.entity.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProcessoServiceTest {

    @InjectMocks
    private ProcessoService processoService;

    @Mock
    private ProcessoRepository processoRepository;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void buscarTodosDelegarParecerTest(){

        when(usuarioLogadoService.usuarioPossuiPermissao("Processo.DelegarParecer")).thenReturn(Boolean.TRUE);

        processoService.buscarTodos();

        verify(processoRepository, times(1)).findAll();
        verify(processoRepository, never()).buscarProcessosComParecer(any());
    }

    @Test
    public void buscarTodosSemDelegarParecerTest(){


        Usuario usuario = new Usuario();
        usuario.setId(885L);

        when(usuarioLogadoService.usuarioPossuiPermissao("Processo.DelegarParecer")).thenReturn(Boolean.FALSE);
        when(usuarioLogadoService.getUsuarioLogado()).thenReturn(usuario);

        processoService.buscarTodos();

        verify(processoRepository, never()).findAll();
        verify(processoRepository, times(1)).buscarProcessosComParecer(usuario.getId());
    }

    @Test
    public void criarTest(){

        Processo processo = new Processo();
        processo.setNumero("7777");

        processoService.criar(processo);

        verify(processoRepository, times(1)).save(processo);
    }


    @Test(expected = ProcessoExistenteException.class)
    public void criarDuplicadoTest(){

        Processo processo = new Processo();
        processo.setNumero("7777");

        when(processoRepository.findByNumero(processo.getNumero())).thenReturn(processo);

        processoService.criar(processo);
    }

}
