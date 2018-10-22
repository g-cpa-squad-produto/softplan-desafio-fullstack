package br.com.softplan.services;

import org.springframework.stereotype.Service;

import br.com.softplan.models.Token;

@Service
public class TokenService {
	
	public Token getToken() {
		return new Token("FTABXBGXTTVLVFDRVGXRKZEWCNAPAZDYHEFSJVFYPYKNNLMVZQKZZCTGZPXNGYQDIEBORAYVJIKFWXOMLZCCCFOMUTIPXSTRJGLZMERUUKZDFJBXFWTHHGQMBDCOSCQV");
	}

}
