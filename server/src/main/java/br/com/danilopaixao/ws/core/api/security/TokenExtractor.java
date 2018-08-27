package br.com.danilopaixao.ws.core.api.security;

public interface TokenExtractor {

	String extract(final String header);

}
