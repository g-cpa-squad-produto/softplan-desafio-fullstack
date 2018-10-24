package com.agfgerador.autenticacao.dao;

import com.agfgerador.autenticacao.domain.Compativeis;
import com.agfgerador.compartilhado.dao.DAOPadrao;

public interface CompativeisDAO extends DAOPadrao{
	
	Compativeis LoadBySistema(Integer codsistema);
    	
	
}
