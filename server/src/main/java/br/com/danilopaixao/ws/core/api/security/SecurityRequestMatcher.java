package br.com.danilopaixao.ws.core.api.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SecurityRequestMatcher implements RequestMatcher {

	private RequestMatcher matcher;
	private OrRequestMatcher skips;

	public SecurityRequestMatcher(final String pathToMatch, final String... pathToSkip) {
		final List<RequestMatcher> matchers = Arrays.stream(pathToSkip)
				.map(AntPathRequestMatcher::new)
				.collect(Collectors.toList());
		matchers.add(new AntPathRequestMatcher("/**", "OPTIONS"));

		this.skips = new OrRequestMatcher(matchers);
		this.matcher = new AntPathRequestMatcher(pathToMatch);
	}

	@Override
	public boolean matches(final HttpServletRequest request) {
		return skips.matches(request) ? false : matcher.matches(request);
	}
}