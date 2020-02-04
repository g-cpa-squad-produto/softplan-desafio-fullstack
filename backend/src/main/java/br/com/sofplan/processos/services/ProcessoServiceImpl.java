package br.com.sofplan.processos.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sofplan.processos.dto.v1.ProcessoDTO;
import br.com.sofplan.processos.dto.v1.UsuarioDTO;
import br.com.sofplan.processos.exceptions.NotFoundException;
import br.com.sofplan.processos.mappers.ProcessoMapper;
import br.com.sofplan.processos.mappers.UsuarioMapper;
import br.com.sofplan.processos.models.Processo;
import br.com.sofplan.processos.models.ProcessoUsuario;
import br.com.sofplan.processos.models.ProcessoUsuarioID;
import br.com.sofplan.processos.models.Usuario;
import br.com.sofplan.processos.repositories.ProcessoRepository;
import br.com.sofplan.processos.repositories.ProcessoUsuarioRepository;
import br.com.sofplan.processos.repositories.UsuarioRepository;

@Service
public class ProcessoServiceImpl implements ProcessoService {

	private final ProcessoRepository processoRepository;
	private final ProcessoUsuarioRepository processoUsuarioRepository;
	private final UsuarioRepository usuarioRepository;

	private final ProcessoMapper processoMapper;
	private final UsuarioMapper usuarioMapper;

	public ProcessoServiceImpl(ProcessoRepository processoRepository, ProcessoMapper processoMapper,
			UsuarioMapper usuarioMapper, ProcessoUsuarioRepository processoUsuarioRepository,
			UsuarioRepository usuarioRepository) {
		this.processoRepository = processoRepository;
		this.processoMapper = processoMapper;
		this.usuarioMapper = usuarioMapper;
		this.processoUsuarioRepository = processoUsuarioRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProcessoDTO> find(UsuarioDTO usuarioJwt) {
		// TODO: filtrar por associações ao usuário do jwt
		return processoRepository.findAll().stream().map(processoMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public ProcessoDTO findById(Long id) {
		return processoRepository.findById(id).map(processoMapper::toDTO).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProcessoDTO create(ProcessoDTO request, UsuarioDTO usuarioJwt) {
		// persist o processo
		final Processo processo = processoMapper.fromDTO(request);
		processoRepository.save(processo);

		// cria os relacionamentos
		if (request.getResponsaveis() != null) {
			List<ProcessoUsuario> responsaveis = mapProcessoUsuario(request, processo, usuarioJwt);
			processoUsuarioRepository.saveAll(responsaveis);
		}

		return processoMapper.toDTO(processo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProcessoDTO update(Long id, ProcessoDTO request) {
		Processo processo = getProcessoById(id);

		// mapeia o DTO
		Processo processoRequest = processoMapper.fromDTO(request);

		// copia os dados que vieram da requisição
		processoMapper.copy(processoRequest, processo);

		// salva o processo
		processo = processoRepository.save(processo);

		return processoMapper.toDTO(processo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		Processo processo = getProcessoById(id);

		// remove o processo
		processoRepository.delete(processo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addResponsavel(Long processoId, Long responsavelId, UsuarioDTO usuarioJwt) {
		Processo processo = getProcessoById(processoId);
		Usuario usuario = getUsuarioById(responsavelId);

		ProcessoUsuario processoUsuario = new ProcessoUsuario(processo, usuario, usuarioMapper.fromDTO(usuarioJwt),
				OffsetDateTime.now());

		processoUsuarioRepository.save(processoUsuario);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteResponsavel(Long processoId, Long responsavelId) {
		Processo processo = getProcessoById(processoId);
		Usuario usuario = getUsuarioById(responsavelId);

		ProcessoUsuario processoUsuario = processoUsuarioRepository.findById(new ProcessoUsuarioID(processo, usuario))
				.orElseThrow(NotFoundException::new);

		processoUsuarioRepository.delete(processoUsuario);
	}

	private Processo getProcessoById(Long id) {
		return processoRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	private Usuario getUsuarioById(Long responsavelId) {
		return usuarioRepository.findById(responsavelId).orElseThrow(NotFoundException::new);
	}

	private List<ProcessoUsuario> mapProcessoUsuario(ProcessoDTO request, final Processo processo,
			UsuarioDTO usuarioJwt) {
		final OffsetDateTime dateTime = OffsetDateTime.now();

		return request
				.getResponsaveis().stream().map(usuarioDTO -> new ProcessoUsuario(processo,
						usuarioMapper.fromDTO(usuarioDTO), usuarioMapper.fromDTO(usuarioJwt), dateTime))
				.collect(Collectors.toList());
	}

}
