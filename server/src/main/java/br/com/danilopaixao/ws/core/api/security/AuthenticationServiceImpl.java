package br.com.danilopaixao.ws.core.api.security;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class AuthenticationServiceImpl implements  AuthenticationService {

	public AuthResponseDto authorize(final String username, final String password) {

		return AuthResponseDto.builder()
				.id("43553534")
				.firstName("Danilo")
				.lastName("Paixao")
				.email("contato.danilo.paixao@gmail.com")
				.username("admin")
				.token("nf2rj298fh3-9fhj2394u8rofhwofh29fe")
				.build();
	}

}
