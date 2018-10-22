package br.com.softplan.models;

import lombok.Data;

public @Data class Token {
	
	public Token(String value) {
		this.value = value;
	}
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
