package br.com.sofplan.processos.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

	private static final long serialVersionUID = 4721769191398499972L;

	public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "Not Found");
    }

}
