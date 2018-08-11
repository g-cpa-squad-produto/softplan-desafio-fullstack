package br.com.softplan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long codigo;

    @NotNull(message = "O campo nome do perfil é obrigatório")
    private String nomePerfil;

    private String descricaoPerfil;
}
