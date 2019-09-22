package br.com.processos.processo.implementation.usecase;

import br.com.processos.processo.implementation.repository.ParecerRepository;
import br.com.processos.processo.specification.entity.EnumParecerSituacao;
import br.com.processos.processo.specification.entity.Parecer;
import br.com.processos.processo.specification.entity.Processo;
import br.com.processos.processo.specification.exception.ParecerNaoExistenteException;
import br.com.processos.usuario.specification.entity.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RealizarParecerTest {

    @Mock
    private ParecerRepository parecerRepository;

    @InjectMocks
    private RealizarParecer realizarParecer;

    @Test
    public void deveRealizarOParecerComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Processo processo = new Processo();
        processo.setId(1L);

        String textoParecer = "Texto parecer";

        Parecer parecer = new Parecer();
        parecer.setUsuario(usuario);
        parecer.setProcesso(processo);
        parecer.setSituacao(EnumParecerSituacao.PENDENTE);
        parecer.setDataAtualizacao(null);

        when(parecerRepository.findByUsuarioIdAndProcessoId(processo.getId(), usuario.getId())).thenReturn(Optional.of(parecer));
        when(parecerRepository.save(parecer)).thenReturn(parecer);

        realizarParecer.executar(processo.getId(), usuario.getId(), textoParecer);

        assertEquals(parecer.getTexto(), textoParecer);
        assertEquals(parecer.getSituacao(), EnumParecerSituacao.REALIZADO);
        assertNotNull(parecer.getDataAtualizacao());
    }

    @Test(expected = ParecerNaoExistenteException.class)
    public void naoDeveRealizarOParecerDeUmProcessoQuandoOParecerNaoForEncontrado() {
        Long usuarioId = 1L;
        Long processoId = 1L;
        String textoParecer = "Texto parecer";

        realizarParecer.executar(processoId, usuarioId, textoParecer);

        verifyNoMoreInteractions(parecerRepository.save(any(Parecer.class)));
    }
}
