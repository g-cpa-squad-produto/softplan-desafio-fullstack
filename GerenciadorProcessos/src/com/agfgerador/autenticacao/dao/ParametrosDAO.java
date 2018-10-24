package com.agfgerador.autenticacao.dao;
import java.util.List;

import com.agfgerador.autenticacao.domain.Parametros;
import com.agfgerador.compartilhado.dao.DAOPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

public interface ParametrosDAO extends DAOPadrao{
	Parametros loadMinimo(); 
    public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
    public Long getNumberRecordsFilter(ObjetoPadrao obj);
}
