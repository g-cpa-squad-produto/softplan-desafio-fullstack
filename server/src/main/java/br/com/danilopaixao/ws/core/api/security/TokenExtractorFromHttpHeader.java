package br.com.danilopaixao.ws.core.api.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
final class TokenExtractorFromHttpHeader implements TokenExtractor {  

    public static String HEADER_PREFIX = "Bearer ";

    @Override
    public String extract(final String header) {
        validHeader(header);
        return header.substring(HEADER_PREFIX.length());
    }

	private void validHeader(final String header) {
		if (StringUtils.isBlank(header)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }

        if (header.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }
        
        if (!header.startsWith(HEADER_PREFIX)) {
            throw new AuthenticationServiceException("Invalid authorization header prefix.");
        }
	}
}