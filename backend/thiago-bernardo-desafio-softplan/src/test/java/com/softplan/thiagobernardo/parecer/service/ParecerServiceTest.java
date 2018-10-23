package com.softplan.thiagobernardo.parecer.service;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.softplan.thiagobernardo.exception.ParecerProcessoException;
import com.softplan.thiagobernardo.parecer.entity.Parecer;
import com.softplan.thiagobernardo.parecer.repository.ParecerRepository;
import com.softplan.thiagobernardo.processo.entity.Processo;
import com.softplan.thiagobernardo.processo.service.ProcessoService;
import com.softplan.thiagobernardo.usuario.entity.Usuario;
import com.softplan.thiagobernardo.util.ParecerStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ParecerServiceTest {
	
	@Mock
	private ProcessoService processoService;
	
	@Mock
	private ParecerRepository parecerRepository;
	
	@InjectMocks
	private ParecerService parecerService;
	
	@Test
	public void salvarParecerProcesso() throws Exception {
		when(parecerRepository.findByProcesso_id((Long) any())).thenReturn(null);
		when(processoService.alterarStatusParecer((Long) any(), (ParecerStatus) any())).thenReturn(new Processo());
		when(parecerService.criar((Parecer) any())).thenReturn(new Parecer());
		Usuario usuario = new Usuario();
		usuario.setId(Long.parseLong("2"));
		Processo processo = new Processo();
		processo.setId(Long.parseLong("2"));
		Parecer parecer = new Parecer();
		parecer.setTitulo("Teste");
		parecer.setDescricao("teste");
		parecer.setUsuario(usuario);
		parecer.setProcesso(processo);
		
		Parecer retorno = parecerService.salvarParecerProcesso(parecer);
		assertNotEquals(null, retorno);
		
		verify(parecerRepository, atLeastOnce()).findByProcesso_id((Long) any());
		verify(processoService, atLeastOnce()).alterarStatusParecer((Long) any(), (ParecerStatus) any());
	}
	
	@Test(expected = ParecerProcessoException.class)
	public void salvarParecerProcessoExisteParecer() throws Exception {
		when(parecerRepository.findByProcesso_id((Long) any())).thenReturn(new Parecer());
		when(processoService.alterarStatusParecer((Long) any(), (ParecerStatus) any())).thenReturn(new Processo());
		when(parecerService.criar((Parecer) any())).thenReturn(new Parecer());
		
		Usuario usuario = new Usuario();
		usuario.setId(Long.parseLong("2"));
		Processo processo = new Processo();
		processo.setId(Long.parseLong("2"));
		Parecer parecer = new Parecer();
		parecer.setTitulo("Teste");
		parecer.setDescricao("teste");
		parecer.setUsuario(usuario);
		parecer.setProcesso(processo);
		
		parecerService.salvarParecerProcesso(parecer);
		
		verify(parecerRepository, atLeastOnce()).findByProcesso_id((Long) any());
		verify(processoService, never()).alterarStatusParecer((Long) any(), (ParecerStatus) any());
	}
}
