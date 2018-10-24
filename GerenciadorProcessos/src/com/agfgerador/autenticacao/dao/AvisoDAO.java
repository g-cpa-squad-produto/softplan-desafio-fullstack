package com.agfgerador.autenticacao.dao;

import java.util.List;

import com.agfgerador.autenticacao.domain.Aviso;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.compartilhado.dao.DAOPadrao;

public interface AvisoDAO extends DAOPadrao{
	List<Aviso> findBySistema(TipoSistema sis);
}
