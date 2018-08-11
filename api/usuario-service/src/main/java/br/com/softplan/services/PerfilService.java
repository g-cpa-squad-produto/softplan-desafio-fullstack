package br.com.softplan.services;

import br.com.softplan.dto.PerfilDTO;
import br.com.softplan.entidades.Perfil;
import br.com.softplan.mapper.PerfilMapper;
import br.com.softplan.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository repository;

    public void salvarPerfil(PerfilDTO perfilDTO) {
        Perfil perfil = PerfilMapper.mapFrom(perfilDTO);
        repository.save(perfil);
    }
}
