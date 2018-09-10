package br.com.softplan.feature.usuario.dto;

import br.com.softplan.feature.usuario.model.PerfilUsuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioResumidoDTO {

    private Long id;
    private String nome;
    private String cpf;
    private LocalDate aniversario;
    private boolean ativo;
    private PerfilUsuario perfil;

}
