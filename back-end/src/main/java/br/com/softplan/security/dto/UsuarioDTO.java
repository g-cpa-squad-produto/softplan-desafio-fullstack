package br.com.softplan.security.dto;

import br.com.softplan.security.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private PapelDTO papel;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.papel = new PapelDTO();
        this.papel.setId(usuario.getPapel().getId());
        this.papel.setDescricao(usuario.getPapel().getDescricao());
    }

    public static List<UsuarioDTO> getListDto(List<Usuario> usuarios) {

        List<UsuarioDTO> usuaiosDto = new ArrayList<>();
        usuarios.forEach(usuario -> {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
            usuaiosDto.add(usuarioDTO);
        });

        return usuaiosDto;
    }
}
