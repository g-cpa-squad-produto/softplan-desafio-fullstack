package br.com.softplan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codigo;
    private String nome;
    private String email;
    private String observacao;
    private List<PerfilDTO> perfis;
}
