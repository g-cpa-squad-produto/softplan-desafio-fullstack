package com.teste.magnum.gerenciadorprocessosapi.model.dto;

import com.teste.magnum.gerenciadorprocessosapi.model.StatusProcessoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessoDTO implements Serializable {

    private Long id;
    private String descricao;
    private List<UsuarioProcessoDTO> usuariosVinculados;
    private LocalDateTime dataRegistro;
    private ParecerDTO parecer;
    private StatusProcessoEnum statusProcesso;
}
