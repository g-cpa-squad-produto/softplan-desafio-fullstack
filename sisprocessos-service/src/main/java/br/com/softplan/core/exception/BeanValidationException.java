package br.com.softplan.core.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Exceção lançada quando ocorre erro de validação em algum Bean
 *
 * @author Samuel Correia Guimarães
 */
public class BeanValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private List<ItemErro> erros;

    public BeanValidationException(String campo, String erro) {
        super("Erro de validação da entidade informada");
        erros = Collections.singletonList(new ItemErro(campo, erro, null));
    }

    public BeanValidationException(List<FieldError> erros) {
        super("Erro de validação da entidade informada");
        this.erros = erros.stream().map(ItemErro::new).collect(Collectors.toList());
    }

    public List<ItemErro> getErros() {
        return erros;
    }


    @Data
    @AllArgsConstructor
    public class ItemErro {

        private String campo;
        private String erro;
        private Object valor;

        ItemErro(FieldError fieldError) {
            this.campo = fieldError.getField();
            this.erro = fieldError.getDefaultMessage();
            this.valor = fieldError.getRejectedValue();
        }

    }

}