package com.teste.magnum.gerenciadorprocessosapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class ParecerDTO implements Serializable {

    private Long id;

    private String descricao;

    @JsonIgnore
    private ProcessoDTO processo;

    private LocalDateTime dataRegistro;

}
