package br.com.softplan.processo.dto;

import br.com.softplan.processo.entity.ParecerProcesso;
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
    private ProcessoDTO processo;

    public ParecerProcessoDTO() {
    }

    public ParecerProcessoDTO(Long id, String parecer, Usuario usuario) {
        this.id = id;
        this.parecer = parecer;
        this.usuario = new UsuarioDTO();
        this.usuario.setNome(usuario.getNome());
    }

    public ParecerProcessoDTO(ParecerProcesso parecerProcesso) {
        this.id = parecerProcesso.getId();
        this.parecer = parecerProcesso.getParecer();
        this.usuario = new UsuarioDTO(parecerProcesso.getUsuario());
        this.processo = new ProcessoDTO(parecerProcesso.getProcesso());
    }
}
