package br.com.softplan.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ErrorDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String error;
    private String descricao;
    private List<DetalheErroDTO> detalhes;
}
