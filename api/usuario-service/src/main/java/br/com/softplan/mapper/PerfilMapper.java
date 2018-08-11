package br.com.softplan.mapper;

import br.com.softplan.dto.PerfilDTO;
import br.com.softplan.entidades.Perfil;

public class PerfilMapper {

    public static Perfil mapFrom(PerfilDTO perfil) {
        return Perfil.builder()
                .codigo(perfil.getCodigo())
                .nomePerfil(perfil.getNomePerfil())
                .descricaoPerfil(perfil.getDescricaoPerfil())
                .build();
    }
}
