package br.com.softplan.mapper;

import br.com.softplan.dto.PerfilDTO;
import br.com.softplan.entidades.Perfil;

public class PerfilDTOMapper {

    public static PerfilDTO mapFrom(Perfil perfil) {
        return PerfilDTO.builder()
                .codigo(perfil.getCodigo())
                .descricaoPerfil(perfil.getDescricaoPerfil())
                .nomePerfil(perfil.getNomePerfil())
                .build();
    }
}
