package br.com.softplan.process.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FieldErrorInfo {

    private String field;
    private String message;
    private Object rejectedValue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String index;
}
