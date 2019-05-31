package br.com.softplan.marcusvoltolim.utils;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * {@link ControllerAdvice} que intercepta as Exceptions de CRUD (Create, Update, Delete e NotFound) e define um codigo
 * HTTP apropriado para elas.
 *
 * @author Leonardo
 * @author Sartre Brasil
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DefaultControllerAdvice extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultControllerAdvice.class);
	
	@ExceptionHandler(TransactionSystemException.class)
	@ResponseStatus(code = INTERNAL_SERVER_ERROR, reason = "Ocorreu um erro no banco de dados")
	public ResponseEntity<Exception> exception(TransactionSystemException ex) {
		logarExcecaoSemStackTrace(ex);
		return new ResponseEntity<>((Exception) ex.getMostSpecificCause(), INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = INTERNAL_SERVER_ERROR, reason = "Ocorreu um erro inesperado no servidor")
	public ResponseEntity<Exception> exception(Exception ex) {
		logarExcecaoSemStackTrace(ex);
		return new ResponseEntity<>(ex, INTERNAL_SERVER_ERROR);
	}
	
	
	private void esconderStackTrace(Exception ex) {
		ex.setStackTrace(new StackTraceElement[] {});
	}
	
	private void logarExcecaoSemStackTrace(Exception ex, String... msg) {
		String mensagem = msg.length == 1 ? msg[0] : ex.getMessage();
		esconderStackTrace(ex);
		LOGGER.error(mensagem, ex);
	}
	
}
