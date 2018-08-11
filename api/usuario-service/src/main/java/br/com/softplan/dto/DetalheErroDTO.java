package br.com.softplan.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalheErroDTO {

    private String campo;
    private String mensagem;
    private Object valorInformado;
}
