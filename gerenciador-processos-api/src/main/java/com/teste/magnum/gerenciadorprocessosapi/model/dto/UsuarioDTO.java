package com.teste.magnum.gerenciadorprocessosapi.model.dto;

import com.teste.magnum.gerenciadorprocessosapi.model.TipoUsuarioEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UsuarioDTO implements Serializable {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private TipoUsuarioEnum tipoUsuario;

}
