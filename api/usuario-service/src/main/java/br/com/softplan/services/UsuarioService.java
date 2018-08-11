package br.com.softplan.services;

import br.com.softplan.dto.UsuarioDTO;
import br.com.softplan.entidades.Usuario;
import br.com.softplan.mapper.UsuarioDTOMapper;
import br.com.softplan.mapper.UsuarioMapper;
import br.com.softplan.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioDTO buscarUsuario(Integer codigo) {
        Optional<Usuario> teste = repository.findById(codigo);
        Usuario usuario = teste.get();
        return UsuarioDTOMapper.mapFrom(usuario);
    }

    public void salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.mapFrom(usuarioDTO);
        repository.save(usuario);
    }
}



