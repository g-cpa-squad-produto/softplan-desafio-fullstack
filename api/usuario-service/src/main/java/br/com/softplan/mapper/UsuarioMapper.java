package br.com.softplan.mapper;

import br.com.softplan.dto.PerfilDTO;
import br.com.softplan.dto.UsuarioDTO;
import br.com.softplan.entidades.Perfil;
import br.com.softplan.entidades.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario mapFrom(UsuarioDTO usuario) {
        return Usuario.builder()
                .observacao(usuario.getObservacao())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .codigo(usuario.getCodigo())
                .perfis(obterPerfis(usuario.getPerfis()))
                .build();
    }

    private static List<Perfil> obterPerfis(List<PerfilDTO> perfis) {
       return perfis.stream().map(perfil -> PerfilMapper.mapFrom(perfil)).collect(Collectors.toList());
    }
}
