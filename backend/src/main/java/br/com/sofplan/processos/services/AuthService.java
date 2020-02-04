package br.com.sofplan.processos.services;

import br.com.sofplan.processos.dto.v1.LoginDTO;
import br.com.sofplan.processos.dto.v1.LoginResponseDTO;

public interface AuthService {

	LoginResponseDTO login(LoginDTO request);
	
}
