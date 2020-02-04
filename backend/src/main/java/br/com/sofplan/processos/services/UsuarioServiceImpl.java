package br.com.sofplan.processos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sofplan.processos.dto.v1.CreateUsuarioDTO;
import br.com.sofplan.processos.dto.v1.UsuarioDTO;
import br.com.sofplan.processos.exceptions.NotFoundException;
import br.com.sofplan.processos.mappers.UsuarioMapper;
import br.com.sofplan.processos.models.Usuario;
import br.com.sofplan.processos.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;
	private final PasswordEncoder passwordEncoder;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper,
			PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.usuarioMapper = usuarioMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDTO> find() {
		// TODO: adicionar filtro e paginação
		return usuarioRepository.findAll().stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		return usuarioRepository.findById(id).map(usuarioMapper::toDTO).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO findByEmail(String email) {
		return usuarioRepository.findByEmail(email).map(usuarioMapper::toDTO).orElseThrow(NotFoundException::new);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UsuarioDTO create(CreateUsuarioDTO request) {
		Usuario usuario = usuarioMapper.fromDTO(request);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

		usuario = usuarioRepository.save(usuario);

		return usuarioMapper.toDTO(usuario);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UsuarioDTO update(Long id, UsuarioDTO request) {
		Usuario usuario = getById(id);
		Usuario usuarioRequest = usuarioMapper.fromDTO(request);

		usuario = usuarioMapper.copy(usuarioRequest, usuario);
		usuarioRepository.save(usuario);

		return usuarioMapper.toDTO(usuario);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(long id) {
		Usuario usuario = getById(id);
		usuarioRepository.delete(usuario);
	}

	private Usuario getById(Long id) {
		return usuarioRepository.findById(id).orElseThrow(NotFoundException::new);
	}

}
