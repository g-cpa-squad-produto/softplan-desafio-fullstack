package org.gradle.exception;

public class AuthenticationException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1865059864186698329L;

	public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
