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
	public ProcessoDTO delete(Long id) {
		Processo processo = getProcessoById(id);

		// remove o processo
		processoRepository.delete(processo);

		return processoMapper.toDTO(processo);
	}

	@Override
	public void addResponsavel(Long processoId, Long responsavelId) {
		Processo processo = getProcessoById(processoId);
		Usuario usuario = getUsuarioById(responsavelId);

		// TODO: adicionar usuário da requisição (JWT)
		ProcessoUsuario processoUsuario = new ProcessoUsuario(processo, usuario, null, OffsetDateTime.now());

		processoUsuarioRepository.save(processoUsuario);
	}

	@Override
	public void deleteResponsavel(Long processoId, Long responsavelId) {
		Processo processo = getProcessoById(processoId);
		Usuario usuario = getUsuarioById(responsavelId);

		ProcessoUsuario processoUsuario = processoUsuarioRepository.findById(new ProcessoUsuarioID(processo, usuario)).orElseThrow(NotFoundException::new);
		
		processoUsuarioRepository.delete(processoUsuario);
	}

	private Processo getProcessoById(Long id) {
		return processoRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	private Usuario getUsuarioById(Long responsavelId) {
		return usuarioRepository.findById(responsavelId).orElseThrow(NotFoundException::new);
	}

	private List<ProcessoUsuario> mapProcessoUsuario(ProcessoDTO request, final Processo processo) {
		final OffsetDateTime dateTime = OffsetDateTime.now();

		// TODO: adicionar usuário da requisição (JWT)
		return request.getResponsaveis().stream().map(usuarioDTO -> new ProcessoUsuario(
				processo, usuarioMapper.fromDTO(usuarioDTO), null, dateTime)).collect(Collectors.toList());
	}

}
