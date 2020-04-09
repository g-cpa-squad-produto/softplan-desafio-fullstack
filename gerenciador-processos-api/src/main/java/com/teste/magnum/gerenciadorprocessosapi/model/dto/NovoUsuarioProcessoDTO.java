package com.teste.magnum.gerenciadorprocessosapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NovoUsuarioProcessoDTO implements Serializable {

    private Long idProcesso;
    private List<UsuarioProcessoDTO> usuariosVinculados;

}
