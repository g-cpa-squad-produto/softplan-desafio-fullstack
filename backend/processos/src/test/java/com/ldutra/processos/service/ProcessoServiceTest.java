package com.ldutra.processos.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ldutra.processos.exception.RegraNegocioException;
import com.ldutra.processos.model.entity.Processo;
import com.ldutra.processos.model.entity.Usuario;
import com.ldutra.processos.model.enums.StatusProcesso;
import com.ldutra.processos.model.repository.ProcessoRepository;
import com.ldutra.processos.model.repository.ProcessoRepositoryTest;
import com.ldutra.processos.service.impl.ProcessoServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ProcessoServiceTest {

	@SpyBean
	ProcessoServiceImpl service;
	@MockBean
	ProcessoRepository repository;
	
	@Test
	public void deveSalvarUmProcesso() {
		//cenário
		Processo processoASalvar = ProcessoRepositoryTest.criarProcesso();
		doNothing().when(service).validar(processoASalvar);
		
		Processo processoSalvo = ProcessoRepositoryTest.criarProcesso();
		processoSalvo.setId(1l);
		processoSalvo.setStatus(StatusProcesso.PENDENTE);
		when(repository.save(processoASalvar)).thenReturn(processoSalvo);
		
		//execucao
		Processo processo = service.salvar(processoASalvar);
		
		//verificação
		assertThat( processo.getId() ).isEqualTo(processoSalvo.getId());
		assertThat(processo.getStatus()).isEqualTo(StatusProcesso.PENDENTE);
	}
	
	@Test
	public void naoDeveSalvarUmProcessoQuandoHouverErroDeValidacao() {
		//cenário
		Processo processoASalvar = ProcessoRepositoryTest.criarProcesso();
		doThrow( RegraNegocioException.class ).when(service).validar(processoASalvar);
		
		//execucao e verificacao
		catchThrowableOfType( () -> service.salvar(processoASalvar), RegraNegocioException.class );
		verify(repository, never()).save(processoASalvar);
	}
	
	@Test
	public void deveAtualizarUmProcesso() {
		//cenário
		Processo processoSalvo = ProcessoRepositoryTest.criarProcesso();
		processoSalvo.setId(1l);
		processoSalvo.setStatus(StatusProcesso.PENDENTE);

		doNothing().when(service).validar(processoSalvo);
		
		when(repository.save(processoSalvo)).thenReturn(processoSalvo);
		
		//execucao
		service.atualizar(processoSalvo);
		
		//verificação
		verify(repository, times(1)).save(processoSalvo);
		
	}
	
	@Test
	public void deveLancarErroAoTentarAtualizarUmProcessoQueAindaNaoFoiSalvo() {
		//cenário
		Processo processo = ProcessoRepositoryTest.criarProcesso();
		
		//execucao e verificacao
		catchThrowableOfType( () -> service.atualizar(processo), NullPointerException.class );
		verify(repository, never()).save(processo);
	}
	
	@Test
	public void deveDeletarUmProcesso() {
		//cenário
		Processo processo = ProcessoRepositoryTest.criarProcesso();
		processo.setId(1l);
		
		//execucao
		service.deletar(processo);
		
		//verificacao
		verify( repository ).delete(processo);
	}
	
	@Test
	public void deveLancarErroAoTentarDeletarUmProcessoQueAindaNaoFoiSalvo() {
		
		//cenário
		Processo processo = ProcessoRepositoryTest.criarProcesso();
		
		//execucao
		catchThrowableOfType( () -> service.deletar(processo), NullPointerException.class );
		
		//verificacao
		verify( repository, never() ).delete(processo);
	}
	
	
	@Test
	public void deveFiltrarProcessos() {
		//cenário
		Processo processo = ProcessoRepositoryTest.criarProcesso();
		processo.setId(1l);
		
		List<Processo> lista = Arrays.asList(processo);
		when( repository.findAll(any(Example.class)) ).thenReturn(lista);
		
		//execucao
		List<Processo> resultado = service.buscar(processo);
		
		//verificacoes
		assertThat(resultado)
			.isNotEmpty()
			.hasSize(1)
			.contains(processo);
		
	}
	
	@Test
	public void deveAtualizarOStatusDeUmProcesso() {
		//cenário
		Processo processo = ProcessoRepositoryTest.criarProcesso();
		processo.setId(1l);
		processo.setStatus(StatusProcesso.PENDENTE);
		
		StatusProcesso novoStatus = StatusProcesso.EFETIVADO;
		doReturn(processo).when(service).atualizar(processo);
		
		//execucao
		service.atualizarStatus(processo, novoStatus);
		
		//verificacoes
		assertThat(processo.getStatus()).isEqualTo(novoStatus);
		verify(service).atualizar(processo);
		
	}
	
	@Test
	public void deveObterUmProcessoPorID() {
		//cenário
		Long id = 1l;
		
		Processo processo = ProcessoRepositoryTest.criarProcesso();
		processo.setId(id);
		
		when(repository.findById(id)).thenReturn(Optional.of(processo));
		
		//execucao
		Optional<Processo> resultado =  service.obterPorId(id);
		
		//verificacao
		assertThat(resultado.isPresent()).isTrue();
	}
	
	@Test
	public void deveREtornarVazioQuandoOProcessoNaoExiste() {
		//cenário
		Long id = 1l;
		
		Processo processo = ProcessoRepositoryTest.criarProcesso();
		processo.setId(id);
		
		when( repository.findById(id) ).thenReturn( Optional.empty() );
		
		//execucao
		Optional<Processo> resultado =  service.obterPorId(id);
		
		//verificacao
		assertThat(resultado.isPresent()).isFalse();
	}
	
	@Test
	public void deveLancarErrosAoValidarUmProcesso() {
		Processo processo = new Processo();
		
		Throwable erro = Assertions.catchThrowable( () -> service.validar(processo) );
		assertThat(erro).isInstanceOf(RegraNegocioException.class).hasMessage("Informe uma Descrição válida.");
		
		processo.setParecer("");
		
		erro = Assertions.catchThrowable( () -> service.validar(processo) );
		assertThat(erro).isInstanceOf(RegraNegocioException.class).hasMessage("Informe uma Descrição válida.");
		
		processo.setParecer("Salario");
		
		erro = Assertions.catchThrowable( () -> service.validar(processo) );
		assertThat(erro).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um Mês válido.");
		
		processo.setAno(0);
		
		erro = catchThrowable( () -> service.validar(processo) );
		assertThat(erro).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um Mês válido.");
		
		processo.setAno(13);
		
		erro = catchThrowable( () -> service.validar(processo) );
		assertThat(erro).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um Mês válido.");
		
		processo.setMes(1);
		
		erro = catchThrowable( () -> service.validar(processo) );
		assertThat(erro).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um Ano válido.");
		
		processo.setAno(202);
		
		erro = catchThrowable( () -> service.validar(processo) );
		assertThat(erro).isInstanceOf(RegraNegocioException.class).hasMessage("Informe um Ano válido.");
		
		
		
		
	}
	

	
}
