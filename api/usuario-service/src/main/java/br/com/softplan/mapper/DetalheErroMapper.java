package br.com.softplan.mapper;

import br.com.softplan.dto.DetalheErroDTO;
import org.springframework.validation.FieldError;

public class DetalheErroMapper {

    public static DetalheErroDTO mapFrom(FieldError fieldError) {
        return DetalheErroDTO.builder()
                .campo(fieldError.getField())
                .mensagem(fieldError.getDefaultMessage())
                .valorInformado(fieldError.getRejectedValue())
                .build();
    }
}
