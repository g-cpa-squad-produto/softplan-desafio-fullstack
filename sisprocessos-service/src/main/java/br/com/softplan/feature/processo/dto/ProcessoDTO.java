package br.com.softplan.feature.processo.dto;

import br.com.softplan.feature.processo.model.StatusProcesso;
import br.com.softplan.feature.usuario.model.Usuario;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProcessoDTO {

    private Long id;
    private Long numero;
    private LocalDate data;
    private String descricao;
    private String parecer;
    private Usuario usuarioParecer;
    private StatusProcesso status;
    private List<Usuario> usuariosPermissao;

}
