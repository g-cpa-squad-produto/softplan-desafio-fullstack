package br.com.danilopaixao.ws.core.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class ApiResource {

	private final String createdAt;

	public ApiResource() {
		super();
		this.createdAt = LocalDateTime.now()
				.format(DateTimeFormatter.ISO_DATE_TIME);
	}

	public String getCreatedAt() {
		return createdAt;
	}

}
