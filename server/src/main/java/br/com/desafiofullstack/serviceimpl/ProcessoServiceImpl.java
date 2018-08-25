/**
 * 
 */
package br.com.desafiofullstack.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiofullstack.domain.Processo;
import br.com.desafiofullstack.repository.ProcessoRepository;
import br.com.desafiofullstack.service.ProcessoService;

/**
 * @author Delano Jr
 *
 */
@Service
public class ProcessoServiceImpl implements ProcessoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProcessoRepository processoRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.desafiofullstack.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public Optional<Processo> save(Processo processo) {
		return Optional.of(processoRepository.save(processo));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.desafiofullstack.service.BaseService#saveAll(java.util.List)
	 */
	@Override
	public Optional<List<Processo>> saveAll(List<Processo> processos) {
		return Optional.of(processoRepository.saveAll(processos));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.desafiofullstack.service.BaseService#findAll()
	 */
	@Override
	public Optional<List<Processo>> findAll() {
		return Optional.of(processoRepository.findAll());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.desafiofullstack.service.BaseService#findOne(java.lang.Object)
	 */
	@Override
	public Optional<Processo> findOne(Long id) {
		return Optional.of(processoRepository.getOne(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.desafiofullstack.service.BaseService#delete(java.lang.Object)
	 */
	@Override
	public void delete(Processo processo) {
		processoRepository.delete(processo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.desafiofullstack.service.BaseService#deleteAll(java.util.List)
	 */
	@Override
	public void deleteAll(List<Processo> processos) {
		processoRepository.deleteAll(processos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.desafiofullstack.service.BaseService#deleteById(java.lang.Object)
	 */
	@Override
	public void deleteById(Long id) {
		processoRepository.deleteById(id);
	}

}
