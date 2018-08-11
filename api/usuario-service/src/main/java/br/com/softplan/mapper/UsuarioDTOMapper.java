package br.com.softplan.mapper;

import br.com.softplan.dto.UsuarioDTO;
import br.com.softplan.entidades.Usuario;

public class UsuarioDTOMapper {

    public static UsuarioDTO mapFrom(Usuario usuario) {
        return UsuarioDTO.builder()
                .observacao(usuario.getObservacao())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .codigo(usuario.getCodigo())
                .build();
    }
}
