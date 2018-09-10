package br.com.softplan.feature.processo.dto;

import br.com.softplan.feature.processo.model.StatusProcesso;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProcessoResumidoDTO {

    private Long id;
    private Long numero;
    private LocalDate data;
    private String descricao;
    private StatusProcesso status;

}
