package br.com.softplan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long codigo;

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @NotBlank(message = "O campo e-mail é obrigatório")
    private String email;

    private String observacao;

    @NotEmpty(message = "É obrigatório a seleção de um perfil")
    private List<PerfilDTO> perfis;
}
