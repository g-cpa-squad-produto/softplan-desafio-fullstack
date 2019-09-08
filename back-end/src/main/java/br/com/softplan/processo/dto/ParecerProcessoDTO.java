package br.com.softplan.processo.dto;

import br.com.softplan.security.dto.UsuarioDTO;
import br.com.softplan.security.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParecerProcessoDTO {

    private Long id;
    private String parecer;
    private UsuarioDTO usuario;

    public ParecerProcessoDTO(Long id, String parecer, Usuario usuario) {
        this.id = id;
        this.parecer = parecer;
        this.usuario = new UsuarioDTO();
        this.usuario.setNome(usuario.getNome());
    }
}
