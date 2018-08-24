package br.com.danilopaixao.ws.core.http;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danilopaixao.ws.core.api.ApiErrorCode;
import br.com.danilopaixao.ws.core.api.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpRestClient {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpRestClient.class);

	private static final String APPLICATION_NAME = "HttpRestClient";
	
	private static final String EMPTY_BODY = "Empty Body";
	
	@Value("${http.rest.client.connection.timeout}")
	private int connectionTimeout;

	@Value("${http.rest.client.read.timeout}")
	private int readTimeout;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public HttpRestClient() {
		super();
		Objects.requireNonNull(this.restTemplate, "RestTemplate is required non null.");
		Objects.requireNonNull(this.objectMapper, "ObjectMapper is required non null.");
		
		final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(connectionTimeout);
		clientHttpRequestFactory.setReadTimeout(readTimeout);

		this.restTemplate.setRequestFactory(
				new BufferingClientHttpRequestFactory(clientHttpRequestFactory));
		
		this.restTemplate.getInterceptors().add(
				new HttpRestClientLoggerInterceptor());
	}
	
	public HttpRestClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
		super();
		Objects.requireNonNull(restTemplate, "RestTemplate is required non null.");
		Objects.requireNonNull(objectMapper, "ObjectMapper is required non null.");

		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;

		this.restTemplate.setRequestFactory(
				new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		
		this.restTemplate.getInterceptors().add(
				new HttpRestClientLoggerInterceptor());
	}

	public <T> ResponseEntity<Void> postWithoutResponse(String url, Object request) throws HttpRestClientException {
		try {
			return restTemplate.postForEntity(url, request, Void.class);
		} catch (HttpClientErrorException e) {
			throw new HttpRestClientException(toResponseEntity(logAndReturn(e)));

		} catch (HttpServerErrorException e) {
			throw new HttpRestClientException(toResponseEntity(logAndReturn(e)));
		} catch (NullPointerException e) {
			log.debug("RestTemplate post without response NullPointerException expected error=" + e.getMessage(), e);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.error("RestTemplate error=" + e.getMessage(), e);
			throw e;
		}
	}

	public <T> ResponseEntity<T> post(String url, Object request, Class<T> responseType) throws HttpRestClientException {
		try {
			return restTemplate.postForEntity(url, request, responseType);
		} catch (HttpClientErrorException e) {
			throw new HttpRestClientException(toResponseEntity(logAndReturn(e)));

		} catch (HttpServerErrorException e) {
			throw new HttpRestClientException(toResponseEntity(logAndReturn(e)));
		} catch (Exception e) {
			log.error("RestTemplate error=" + e.getMessage(), e);
			throw e;
		}
	}

	public String postSimpleBody(String url, Object body) throws HttpRestClientException {
		try {
			return restTemplate.postForObject(url, body, String.class);
		} catch (HttpClientErrorException e) {
			throw new HttpRestClientException(toResponseEntity(logAndReturn(e)));

		} catch (HttpServerErrorException e) {
			throw new HttpRestClientException(toResponseEntity(logAndReturn(e)));
		}
	}
	
	public String getForObject(String url) throws HttpRestClientException {
		return restTemplate.getForObject(url, String.class);
	}
	
	public <T> ResponseEntity<T> get(String url, Class<T> responseType) throws HttpRestClientException {
		try {
			return restTemplate.getForEntity(url, responseType);
		} catch (HttpClientErrorException e) {
			throw new HttpRestClientException(toResponseEntity(logAndReturn(e)));

		} catch (HttpServerErrorException e) {
			throw new HttpRestClientException(toResponseEntity(logAndReturn(e)));
		}
	}

	private HttpStatusCodeException logAndReturn(HttpStatusCodeException e) {
		final HttpStatus status = e.getStatusCode();
		final HttpHeaders headers = e.getResponseHeaders();
		final String body = e.getResponseBodyAsString();
		final Throwable cause = e.getRootCause();
		final Throwable[] throwables = e.getSuppressed();
		
		logger.error("ApplicationName={} ERROR={} status={} headers={} body={} cause={} throwables={}", 
				APPLICATION_NAME,
				e.getMessage(),
				status, 
				headers,
				body,
				cause,
				Arrays.toString(throwables));

		return e;
	}

	private ApiErrorResponse toResponseEntity(HttpStatusCodeException e) {
		try {
			final HttpStatus status = e.getStatusCode();
			final HttpHeaders headers = e.getResponseHeaders();
			final byte[] body = e.getResponseBodyAsByteArray();

			if (body.length > 0) {
				return error(e, status, headers, body);
			}
			
			return error(e, status, headers, null);
		} catch (IOException e1) {
			throw new HttpRestClientMapperException(e1);
		}
	}

	private ApiErrorResponse error(HttpStatusCodeException e, final HttpStatus status, 
			final HttpHeaders headers, final byte[] body) throws IOException {

		final String message = body == null ? EMPTY_BODY: new String(body, StandardCharsets.UTF_8.name());
		return ApiErrorResponse.of(message, ApiErrorCode.GLOBAL, status);
	}

}
