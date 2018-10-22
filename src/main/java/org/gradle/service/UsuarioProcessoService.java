package org.gradle.service;

import org.gradle.interfacedao.InterfaceUsuarioProcessoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioProcessoService {
	
	@Autowired
	InterfaceUsuarioProcessoDAO interfaceUsuarioProcessoDAO;

}
