package br.com.softplan.services;

import org.springframework.stereotype.Service;

import br.com.softplan.models.Token;
import br.com.softplan.models.User;

@Service
public class TokenService {
	
	public Token getToken(User user) {
		return new Token("UKZDFJBXFWTHHGQMBDCOSCQV", user.getLogin(), user.getProfile());
	}

}
