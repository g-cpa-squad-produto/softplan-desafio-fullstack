package org.gradle.dao;

import org.gradle.entidade.UsuarioProcesso;
import org.gradle.interfacedao.InterfaceUsuarioProcessoDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioProcessoDAO {
	
	@Autowired
	InterfaceUsuarioProcessoDAO interfaceUsuarioProcessoDAO;
	
	public void salvaAnalise(UsuarioProcesso usuarioProcesso) {
		interfaceUsuarioProcessoDAO.save(usuarioProcesso);
	}

}
