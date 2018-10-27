package br.com.softplan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProcessNotFound extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	
	public ProcessNotFound(String msg) {
		super(msg);
	}

}
