package br.com.softplan.processos.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.softplan.processos.model.Erro;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<?> handleGenericException(GenericException ex) {
	// Cria a lista de erros
	List<Erro> erros = Arrays.asList(new Erro(ex.getMessage()));

	Map<String, Object> map = criarErros(erros);

	if (ex instanceof RegraNegocioException) {
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	} else {
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> errorsInesperados(Exception ex) {
	// Cria a lista de erros
	List<Erro> erros = Arrays.asList(new Erro(ex.getMessage()));

	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(criarErros(erros));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
	// Recupera a lista de erros
	List<Erro> erros = getBeanValidationErros(ex.getBindingResult());

	return handleExceptionInternal(ex, criarErros(erros), headers, status, request);
    }

    // Cria o modelo padrão de retorno para erros
    private Map<String, Object> criarErros(List<Erro> erros) {
	Map<String, Object> map = new HashMap<>();
	map.put("erros", erros);
	return map;
    }

    private List<Erro> getBeanValidationErros(BindingResult bindingResult) {
	List<Erro> erros = new ArrayList<>();

	bindingResult.getFieldErrors().forEach(fieldError -> {
	    erros.add(new Erro(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())));
	});

	return erros;
    }
}
