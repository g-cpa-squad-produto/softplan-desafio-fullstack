package br.com.softplan.feature.usuario.dto;

import br.com.softplan.feature.usuario.model.PerfilUsuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate aniversario;
    private String endereco;
    private boolean ativo;
    private String login;
    private String senha;
    private PerfilUsuario perfil;

}
