package br.com.danilopaixao.ws.user.api.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRequest {
	private Long id;
	private String name;
	private String login;
}
