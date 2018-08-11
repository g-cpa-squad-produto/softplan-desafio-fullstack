package br.com.softplan.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codigo;
    private String nomePerfil;
    private String descricaoPerfil;
}
