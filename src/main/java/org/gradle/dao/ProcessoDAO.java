package org.gradle.dao;

import org.gradle.entidade.Processo;
import org.gradle.interfacedao.InterfaceProcessoDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessoDAO {
	
	@Autowired
	InterfaceProcessoDAO interfaceProcessoDAO;
	
	public void salvaAnalise(Processo processo) {
		interfaceProcessoDAO.save(processo);
	}

}
