package com.ldutra.processos.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ldutra.processos.exception.RegraNegocioException;
import com.ldutra.processos.model.entity.Processo;
import com.ldutra.processos.model.enums.StatusProcesso;
import com.ldutra.processos.model.repository.ProcessoRepository;
import com.ldutra.processos.service.ProcessoService;

@Service
public class ProcessoServiceImpl implements ProcessoService {
	private ProcessoRepository repository;
	
	public ProcessoServiceImpl(ProcessoRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Processo salvar(Processo processo) {
		validar(processo);
		processo.setStatus(StatusProcesso.PENDENTE);
		return repository.save(processo);
	}

	@Override
	@Transactional
	public Processo atualizar(Processo processo) {
		Objects.requireNonNull(processo.getId());
		validar(processo);
		return repository.save(processo);
	}

	@Override
	@Transactional
	public void deletar(Processo processo) {
		Objects.requireNonNull(processo.getId());
		repository.delete(processo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Processo> buscar(Processo processoFiltro) {
		Example example = Example.of( processoFiltro, 
				ExampleMatcher.matching()
					.withIgnoreCase()
					.withStringMatcher(StringMatcher.CONTAINING) );
		
		return repository.findAll(example);
	}

	@Override
	public void atualizarStatus(Processo processo, StatusProcesso status) {
		processo.setStatus(status);
		atualizar(processo);
	}

	@Override
	public void validar(Processo processo) {
		
		if(processo.getParecer() == null || processo.getParecer().trim().equals("")) {
			throw new RegraNegocioException("Informe uma Descrição válida.");
		}
		
		if(processo.getMes() == null || processo.getMes() < 1 || processo.getMes() > 12) {
			throw new RegraNegocioException("Informe um Mês válido.");
		}
		
		if(processo.getAno() == null || processo.getAno().toString().length() != 4 ) {
			throw new RegraNegocioException("Informe um Ano válido.");
		}
		
		
		
	}

	@Override
	public Optional<Processo> obterPorId(Long id) {
		return repository.findById(id);
	}

	


}
