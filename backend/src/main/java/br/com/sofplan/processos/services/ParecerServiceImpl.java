package br.com.sofplan.processos.services;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sofplan.processos.dto.v1.ParecerDTO;
import br.com.sofplan.processos.exceptions.NotFoundException;
import br.com.sofplan.processos.mappers.ParecerMapper;
import br.com.sofplan.processos.models.Parecer;
import br.com.sofplan.processos.repositories.ParecerRepository;

@Service
public class ParecerServiceImpl implements ParecerService {

	private final ParecerRepository parecerRepository;
	private final ParecerMapper parecerMapper;

	public ParecerServiceImpl(ParecerRepository parecerRepository, ParecerMapper parecerMapper) {
		this.parecerRepository = parecerRepository;
		this.parecerMapper = parecerMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public ParecerDTO findById(Long id) {
		return parecerRepository.findById(id).map(parecerMapper::toDTO).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ParecerDTO create(ParecerDTO request) {
		Parecer parecer = parecerMapper.fromDTO(request);
		
		//TODO: adicionar usuário da requisição (JWT)
//		parecer.setCriadoPor(criadoPor);
		parecer.setDataCriacao(OffsetDateTime.now());
		parecer = parecerRepository.save(parecer);
		
		return parecerMapper.toDTO(parecer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ParecerDTO update(Long id, ParecerDTO request) {
		Parecer parecer = getById(id);
		Parecer parecerRequest = parecerMapper.fromDTO(request);
		
		parecer = parecerMapper.copy(parecerRequest, parecer);
		parecer = parecerRepository.save(parecer);
		
		return parecerMapper.toDTO(parecer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		Parecer parecer = getById(id);
		parecerRepository.delete(parecer);
	}
	
	private Parecer getById(Long id) {
		return parecerRepository.findById(id).orElseThrow(NotFoundException::new);
	}

}
