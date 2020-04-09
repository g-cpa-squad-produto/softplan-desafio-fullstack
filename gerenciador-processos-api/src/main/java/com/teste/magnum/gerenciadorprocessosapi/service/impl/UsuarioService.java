package com.teste.magnum.gerenciadorprocessosapi.service.impl;

import com.teste.magnum.gerenciadorprocessosapi.domain.Usuario;
import com.teste.magnum.gerenciadorprocessosapi.exception.UsuarioException;
import com.teste.magnum.gerenciadorprocessosapi.exception.http.NotFoundException;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.LoginDTO;
import com.teste.magnum.gerenciadorprocessosapi.model.dto.UsuarioDTO;
import com.teste.magnum.gerenciadorprocessosapi.repository.UsuarioRepository;
import com.teste.magnum.gerenciadorprocessosapi.service.IUsuarioService;
import com.teste.magnum.gerenciadorprocessosapi.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDTO> findAll() throws NotFoundException {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if(usuarios.isEmpty()) {
            throw new NotFoundException(MessageFormat.format("Nenhum usuário encontrado.", null));
        }
        return usuarios.stream().map(UsuarioService::buildUsuarioDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) throws UsuarioException {
        Optional<Usuario> usuario = usuarioRepository.getUsuarioByCpf(usuarioDTO.getCpf());
        if(usuario.isPresent()) {
            throw new UsuarioException(MessageFormat.format("Usuario já cadastrado no sistema.", null));
        }
        try {
            return buildUsuarioDTO(usuarioRepository.save(buildNovoUsuario(usuarioDTO)));
        } catch (NoSuchAlgorithmException e) {
            throw new UsuarioException(MessageFormat.format("Erro ao tentar gerar a senha do usuário. Favor contactar o administrador do sistema", null));
        }
    }

    @Override
    public UsuarioDTO edit(Long id) throws UsuarioException {
        Optional<Usuario> usuario = usuarioRepository.getUsuarioById(id);
        return usuario.map(
                value -> buildUsuarioDTO(usuarioRepository.save(value))).orElseThrow(
                        () -> new UsuarioException(MessageFormat.format("Erro ao tentar editar usuário.", null)));
    }

    @Override
    public UsuarioDTO findById(Long id) throws NotFoundException {
        Optional<Usuario> usuario = usuarioRepository.getUsuarioById(id);
        return usuario.map(UsuarioService::buildUsuarioDTO).orElseThrow(
                () -> new NotFoundException(MessageFormat.format("Usuário não cadastrado no sistema.", null)));
    }

    @Override
    public void delete(UsuarioDTO entity) {}

    @Override
    public void deleteById(Long id) throws UsuarioException {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO getUserByEmailAndPassword(LoginDTO loginDTO) throws NoSuchAlgorithmException, NotFoundException {
        Optional<Usuario> usuario = usuarioRepository
                .getUsuarioByEmailAndSenha(loginDTO.getEmail(), SecurityUtil.geradorSenhaMD5(loginDTO.getSenha()));
        return usuario.map(UsuarioService::buildUsuarioDTO)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Usuário não cadastrado no sistema.", null)));
    }

    private static UsuarioDTO buildUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .cpf(usuario.getCpf())
                .email(usuario.getEmail())
                .tipoUsuario(usuario.getTipoUsuario())
                .build();
    }

    private static Usuario buildNovoUsuario(UsuarioDTO usuarioDTO) throws NoSuchAlgorithmException {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .cpf(usuarioDTO.getCpf())
                .email(usuarioDTO.getEmail())
                .senha(SecurityUtil.geradorSenhaMD5(usuarioDTO.getSenha()))
                .dataRegistro(LocalDateTime.now())
                .tipoUsuario(usuarioDTO.getTipoUsuario())
                .build();
    }
}
