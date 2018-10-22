package org.gradle.service;

import org.gradle.interfacedao.InterfaceProcessoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessoService {
	
	@Autowired
	InterfaceProcessoDAO interfaceProcessoDAO;

}
