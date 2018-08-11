package br.com.softplan.services;

import br.com.softplan.dto.PerfilDTO;
import br.com.softplan.entidades.Perfil;
import br.com.softplan.mapper.PerfilDTOMapper;
import br.com.softplan.mapper.PerfilMapper;
import br.com.softplan.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository repository;

    public void salvarPerfil(PerfilDTO perfilDTO) {
        Perfil perfil = PerfilMapper.mapFrom(perfilDTO);
        repository.save(perfil);
    }

    public PerfilDTO buscarPerfil(Long codigo) {
        Optional<Perfil> perfilOptional = repository.findById(codigo);
        return PerfilDTOMapper.mapFrom(perfilOptional.get());
    }

    public List<PerfilDTO> listarPerfis() {
        Iterable<Perfil> perfilIterable = repository.findAll();
        Stream<Perfil> perfilStream = StreamSupport.stream(perfilIterable.spliterator(), false);
        return perfilStream.map(perfil -> PerfilDTOMapper.mapFrom(perfil)).collect(Collectors.toList());
    }

    public void excluirPerfil(PerfilDTO perfilDTO) {
        Perfil perfil = PerfilMapper.mapFrom(perfilDTO);
        repository.delete(perfil);
    }
}
