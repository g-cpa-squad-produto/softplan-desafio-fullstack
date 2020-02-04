package br.com.sofplan.processos.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sofplan.processos.dto.v1.LoginDTO;
import br.com.sofplan.processos.dto.v1.LoginResponseDTO;
import br.com.sofplan.processos.exceptions.UnauthorizedException;
import br.com.sofplan.processos.mappers.UsuarioMapper;
import br.com.sofplan.processos.models.Usuario;
import br.com.sofplan.processos.repositories.UsuarioRepository;
import br.com.sofplan.processos.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final UsuarioRepository usuarioRepository;
	private final UsuarioMapper usuarioMapper;

	public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider,
			UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
		this.usuarioRepository = usuarioRepository;
		this.usuarioMapper = usuarioMapper;
	}

	@Override
	public LoginResponseDTO login(LoginDTO request) {
		Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow(UnauthorizedException::new);
		
		if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
			throw new UnauthorizedException();
		}
		
		String token = jwtTokenProvider.createToken(usuarioMapper.toDTO(usuario));
		
		var loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO.setEmail(request.getEmail());
		loginResponseDTO.setToken(token);
		loginResponseDTO.setExpiresIn(String.valueOf(jwtTokenProvider.getValidityInMilliseconds()));
		
		return loginResponseDTO;
	}

}
