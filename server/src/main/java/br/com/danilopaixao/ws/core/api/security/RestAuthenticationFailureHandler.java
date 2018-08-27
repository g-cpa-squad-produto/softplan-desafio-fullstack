package br.com.danilopaixao.ws.core.api.security;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	private final Map<Class<? extends AuthenticationException>, Consumer<HttpServletResponse>> action = action();
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException e) throws IOException, ServletException {
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		Optional.ofNullable(action.get(e.getClass())).orElse(httpServletResponse->{
			try {
				mapper.writeValue(response.getWriter(), new ApiErrorResponse[] { 
						ApiErrorResponse.of(
								"Authentication failed", ApiErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED) });
			} catch (IOException e1) {
				throwsException(e1);
			}
		});
	}

	private Map<Class<? extends AuthenticationException>, Consumer<HttpServletResponse>> action() {
		final Map<Class<? extends AuthenticationException>, Consumer<HttpServletResponse>> action = Collections.synchronizedMap(new HashMap<>());
		action.put(BadCredentialsException.class, response->{
			try {
				mapper.writeValue(response.getWriter(), new ApiErrorResponse[] {
						ApiErrorResponse.of(
								"Invalid username or password", ApiErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED) });
			} catch (IOException e) {
				throwsException(e);
			}
		});
		
		action.put(null, response->{
			try {
				mapper.writeValue(response.getWriter(), new ApiErrorResponse[] {
						ApiErrorResponse.of(
								"Token has expired", ApiErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED) });
			} catch (IOException e) {
				throwsException(e);
			}
		});
		return action;
	}
	
	private void throwsException(final IOException e) {
		throw new RuntimeException(e);
	}
}
