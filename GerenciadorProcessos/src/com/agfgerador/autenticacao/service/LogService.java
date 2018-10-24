package com.agfgerador.autenticacao.service;

import java.util.List;

import com.agfgerador.autenticacao.domain.Log;
import com.agfgerador.autenticacao.domain.Metodo;
import com.agfgerador.autenticacao.domain.Modulo;
import com.agfgerador.compartilhado.service.ServicePadrao;


public interface LogService extends ServicePadrao {

	public void createNew(Object obj, Modulo modulo, Metodo metodo);
	public void createNewSemId(Object obj, Modulo modulo, Metodo metodo);
	
	public List<Log> findByNome(String nome);
	
	public List<Log> filter(Log obj, boolean allHabilitado);
}
