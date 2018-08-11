package br.com.softplan.controllers;

import br.com.softplan.dto.DetalheErroDTO;
import br.com.softplan.dto.ErrorDTO;
import br.com.softplan.exception.ErroInternoException;
import br.com.softplan.mapper.DetalheErroMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlerController {

    private static final String MENSAGEM_ERRO_CAMPOS = "Foi informado parâmetros inválidos";

    public ResponseEntity<ErrorDTO> handleException(ErroInternoException exception) {
        ErrorDTO error = ErrorDTO.builder()
                .error(exception.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleException(MethodArgumentNotValidException exception) {
        List<DetalheErroDTO> detalhesError = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> DetalheErroMapper.mapFrom(fieldError)).collect(Collectors.toList());

        ErrorDTO error = ErrorDTO.builder()
                .error(exception.getMessage())
                .detalhes(detalhesError)
                .descricao(MENSAGEM_ERRO_CAMPOS)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
