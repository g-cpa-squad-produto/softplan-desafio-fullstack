package br.com.sofplan.processos.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.sofplan.processos.dto.v1.ProcessoDTO;
import br.com.sofplan.processos.exceptions.NotFoundException;
import br.com.sofplan.processos.mappers.ProcessoMapper;
import br.com.sofplan.processos.mappers.UsuarioMapper;
import br.com.sofplan.processos.models.Processo;
import br.com.sofplan.processos.models.ProcessoUsuario;
import br.com.sofplan.processos.models.ProcessoUsuarioID;
import br.com.sofplan.processos.repositories.ProcessoRepository;
import br.com.sofplan.processos.repositories.ProcessoUsuarioRepository;

@Service
public class ProcessoServiceImpl implements ProcessoService {

	private final ProcessoRepository processoRepository;
	private final ProcessoUsuarioRepository processoUsuarioRepository;

	private final ProcessoMapper processoMapper;
	private final UsuarioMapper usuarioMapper;

	public ProcessoServiceImpl(ProcessoRepository processoRepository, ProcessoMapper processoMapper,
			UsuarioMapper usuarioMapper, ProcessoUsuarioRepository processoUsuarioRepository) {
		this.processoRepository = processoRepository;
		this.processoMapper = processoMapper;
		this.usuarioMapper = usuarioMapper;
		this.processoUsuarioRepository = processoUsuarioRepository;
	}

	@Override
	public List<ProcessoDTO> find() {
		// TODO: adicionar filtro e paginação
		return processoRepository.findAll().stream().map(processoMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public ProcessoDTO findById(Long id) {
		return processoRepository.findById(id).map(processoMapper::toDTO).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public ProcessoDTO create(ProcessoDTO request) {
		// persist o processo
		final Processo processo = processoMapper.fromDTO(request);
		processoRepository.save(processo);

		// cria os relacionamentos
		if (request.getResponsaveis() != null) {
			List<ProcessoUsuario> responsaveis = mapProcessoUsuario(request, processo);
			processoUsuarioRepository.saveAll(responsaveis);
		}

		return processoMapper.toDTO(processo);
	}

	@Override
	public ProcessoDTO update(Long id, ProcessoDTO request) {
		Processo processo = processoRepository.findById(id).orElseThrow(NotFoundException::new);

		// mapeia o DTO
		Processo processoRequest = processoMapper.fromDTO(request);

		// copia os dados que vieram da requisição
		processoMapper.copy(processoRequest, processo);

		// salva o processo
		processo = processoRepository.save(processo);

		return processoMapper.toDTO(processo);
	}

	@Override
	public ProcessoDTO delete(Long id) {
		Processo processo = processoRepository.findById(id).orElseThrow(NotFoundException::new);

		// remove o processo
		processoRepository.delete(processo);

		return processoMapper.toDTO(processo);
	}

	private List<ProcessoUsuario> mapProcessoUsuario(ProcessoDTO request, final Processo processo) {
		final OffsetDateTime dateTime = OffsetDateTime.now();

		return request.getResponsaveis().stream().map(usuarioDTO -> new ProcessoUsuario(
				// TODO: adicionar usuário da requisição (JWT)
				new ProcessoUsuarioID(processo, usuarioMapper.fromDTO(usuarioDTO)), null, dateTime))
				.collect(Collectors.toList());
	}

}
