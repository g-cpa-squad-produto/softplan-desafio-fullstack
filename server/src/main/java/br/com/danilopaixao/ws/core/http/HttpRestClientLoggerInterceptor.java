package br.com.danilopaixao.ws.core.http;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class HttpRestClientLoggerInterceptor implements ClientHttpRequestInterceptor {

	private static final Logger logger =  LoggerFactory.getLogger(HttpRestClientLoggerInterceptor.class);

	private static final String APPLICATION_NAME = "RestTemplate";

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		try {
			this.logRequest(request, body);
			final ClientHttpResponse response = execution.execute(request, body);
			this.logResponse(request, response);
			return response;
		} catch (Throwable cause) {
			throw cause;
		}
	}
	
	private void logRequest(HttpRequest request, byte[] body) throws UnsupportedEncodingException {
		logger.info("ApplicationName={} REQUEST HttpMethod={} RequestURI={} Headers={} Body={}",
				APPLICATION_NAME, request.getMethod(), request.getURI(), request.getHeaders(),
				getRequestBody(body));
	}

	private void logResponse(HttpRequest request, ClientHttpResponse response) throws IOException {
		logger.info("ApplicationName={} RESPONSE HttpStatus={} HttpMethod={} RequestURI={} Headers={} Body={}",
				APPLICATION_NAME, response.getStatusCode(), request.getMethod(), request.getURI(), 
				request.getHeaders(), getResponseBody(response));
	}

	private String getRequestBody(byte[] body) throws UnsupportedEncodingException {
		return Optional.of(
				new String(body, StandardCharsets.UTF_8.name()))
				.filter(StringUtils::isNotBlank)
				.orElse("Empty Body");
	}

	private Object getResponseBody(ClientHttpResponse response) throws IOException {
		final Predicate<String> isJson = s -> s.toLowerCase().contains("json");
		final Predicate<String> isText = s -> s.toLowerCase().contains("text");

		final boolean isReadable = response.getHeaders()
				.get(CONTENT_TYPE)
				.stream()
				.anyMatch(isJson.or(isText));
		
		return isReadable 
				? IOUtils.toString(response.getBody(), StandardCharsets.UTF_8.name())
				: "Body Not Readable As Json Or Text";
	}

}