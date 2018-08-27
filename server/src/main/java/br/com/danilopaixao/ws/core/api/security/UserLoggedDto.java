package br.com.danilopaixao.ws.core.api.security;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public final class UserLoggedDto {

	private final String username;
	private List<String> authorities = Lists.newArrayList("ADMIN");

	public UserLoggedDto(final String username) {
		Preconditions.checkArgument(StringUtils.isNotBlank(username), "Username is empty.");
		this.username = username;
	}

}