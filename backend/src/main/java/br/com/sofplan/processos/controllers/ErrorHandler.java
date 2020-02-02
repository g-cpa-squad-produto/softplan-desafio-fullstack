package br.com.sofplan.processos.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.sofplan.processos.exceptions.CustomException;

import java.util.ArrayList;
import java.util.List;

/**
 * para resetar as mensagens de outros erros
 * deve herdar de: ResponseEntityExceptionHandler
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ErrorMessage> handleRestException(Exception ex) {
        CustomException restException = (CustomException) ex;
        return new ResponseEntity<>(new ErrorMessage(restException), restException.getStatus());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorMessage> handleIntegrityViolation(Exception ex) {
        ErrorMessage message = new ErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Unprocessable Entity");
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                          WebRequest request) {
        ValidationMessage validationMessage = new ValidationMessage(ex);
        return new ResponseEntity<>(validationMessage, HttpStatus.BAD_REQUEST);
    }

    private static class ErrorMessage {
        private int status;
        private String message;

        
        public ErrorMessage() {
        	super();
        }
        
        public ErrorMessage(int status, String message) {
            this.status = status;
            this.message = message;
        }

        ErrorMessage(CustomException ex) {
            status = ex.getStatus().value();
            message = ex.getMessage();
        }

		@SuppressWarnings("unused")
		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		@SuppressWarnings("unused")
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
    }

    private static class ValidationMessage extends ErrorMessage {
        private List<FieldMessage> fields;

        ValidationMessage(MethodArgumentNotValidException ex) {
            this.setStatus(HttpStatus.BAD_REQUEST.value());
            this.setMessage("VALIDATION_ERROR");

            this.fields = new ArrayList<>();
            for (FieldError error : ex.getBindingResult().getFieldErrors()) {
                addField(error.getField(), error.getDefaultMessage(), error.getObjectName());
            }
        }

        private void addField(String field, String message, String objectName) {
            FieldMessage fieldMessage = new FieldMessage(field, message, objectName);
            this.fields.add(fieldMessage);
        }

    }

    private static class FieldMessage {
        private String field;
        private String message;
        private String objectName;
        
        public FieldMessage(String field, String message, String objectName) {
			super();
			this.field = field;
			this.message = message;
			this.objectName = objectName;
		}
        
		@SuppressWarnings("unused")
		public String getField() {
			return field;
		}
		
		@SuppressWarnings("unused")
		public void setField(String field) {
			this.field = field;
		}
		
		@SuppressWarnings("unused")
		public String getMessage() {
			return message;
		}
		
		@SuppressWarnings("unused")
		public void setMessage(String message) {
			this.message = message;
		}
		
		@SuppressWarnings("unused")
		public String getObjectName() {
			return objectName;
		}
		
		@SuppressWarnings("unused")
		public void setObjectName(String objectName) {
			this.objectName = objectName;
		}
    }

}
