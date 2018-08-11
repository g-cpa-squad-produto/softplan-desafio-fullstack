package br.com.softplan.services;

import br.com.softplan.dto.UsuarioDTO;
import br.com.softplan.entidades.Usuario;
import br.com.softplan.mapper.UsuarioDTOMapper;
import br.com.softplan.mapper.UsuarioMapper;
import br.com.softplan.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioDTO buscarUsuario(Long codigo) {
        Optional<Usuario> usuarioOpational = repository.findById(codigo);
        Usuario usuario = usuarioOpational.get();
        return UsuarioDTOMapper.mapFrom(usuario);
    }

    public void salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.mapFrom(usuarioDTO);
        repository.save(usuario);
    }

    public void excluirUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.mapFrom(usuarioDTO);
        repository.delete(usuario);
    }

    public List<UsuarioDTO> listarUsuarios() {
        Iterable<Usuario> usuarioIterable = repository.findAll();
        Stream<Usuario> usuarioStream = StreamSupport.stream(usuarioIterable.spliterator(), false);
        return usuarioStream.map(usuario -> UsuarioDTOMapper.mapFrom(usuario)).collect(Collectors.toList());
    }
}



