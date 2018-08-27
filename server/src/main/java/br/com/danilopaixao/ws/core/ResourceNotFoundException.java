package br.com.danilopaixao.ws.core;

import java.io.Serializable;

import lombok.Getter;

@Getter
public final class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 5390091506149407665L;
	
	private static final String MSG = "Resource name=%s by id=%s not found.";
	
	private final String name;
	private final Serializable id;

	public ResourceNotFoundException(final String name, final Serializable id) {
		super(String.format(MSG, name, id));
		this.name = name;
		this.id = id;
	}

}
