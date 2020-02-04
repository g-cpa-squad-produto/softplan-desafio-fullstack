package br.com.sofplan.processos.services;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sofplan.processos.dto.v1.ParecerDTO;
import br.com.sofplan.processos.dto.v1.UsuarioDTO;
import br.com.sofplan.processos.exceptions.NotFoundException;
import br.com.sofplan.processos.mappers.ParecerMapper;
import br.com.sofplan.processos.mappers.UsuarioMapper;
import br.com.sofplan.processos.models.Parecer;
import br.com.sofplan.processos.models.Usuario;
import br.com.sofplan.processos.repositories.ParecerRepository;
import br.com.sofplan.processos.repositories.ProcessoRepository;

@Service
public class ParecerServiceImpl implements ParecerService {

	private final ParecerRepository parecerRepository;
	private final ProcessoRepository processoRepository;
	private final ParecerMapper parecerMapper;
	private final UsuarioMapper usuarioMapper;

	public ParecerServiceImpl(ParecerRepository parecerRepository, ParecerMapper parecerMapper,
			UsuarioMapper usuarioMapper, ProcessoRepository processoRepository) {
		this.parecerRepository = parecerRepository;
		this.parecerMapper = parecerMapper;
		this.usuarioMapper = usuarioMapper;
		this.processoRepository = processoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public ParecerDTO findById(Long id) {
		return parecerRepository.findById(id).map(parecerMapper::toDTO).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ParecerDTO create(ParecerDTO request, UsuarioDTO usuarioJwt) {
		// verifica se o processo existe
		processoRepository.findById(request.getId()).orElseThrow(NotFoundException::new);
		
		Parecer parecer = parecerMapper.fromDTO(request);
		Usuario usuario = usuarioMapper.fromDTO(usuarioJwt);

		parecer.setCriadoPor(usuario);
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
