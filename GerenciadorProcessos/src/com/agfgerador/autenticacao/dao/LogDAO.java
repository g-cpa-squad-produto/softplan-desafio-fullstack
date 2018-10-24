package com.agfgerador.autenticacao.dao;

import java.util.List;

import com.agfgerador.autenticacao.domain.Log;
import com.agfgerador.compartilhado.dao.DAOPadrao;

public interface LogDAO extends DAOPadrao {
	
	List<Log> findByNome(String nome);
	
	List<Log> filter(Log obj, boolean allHabilitado);
}
