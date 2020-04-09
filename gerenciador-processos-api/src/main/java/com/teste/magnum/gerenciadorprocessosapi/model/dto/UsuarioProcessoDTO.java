package com.teste.magnum.gerenciadorprocessosapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioProcessoDTO implements Serializable {

    private String nome;
    private String cpf;

}
