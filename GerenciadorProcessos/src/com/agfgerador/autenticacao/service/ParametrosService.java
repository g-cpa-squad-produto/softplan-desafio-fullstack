package com.agfgerador.autenticacao.service;

import java.util.List;
import com.agfgerador.autenticacao.domain.Parametros;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.service.ServicePadrao;

public interface ParametrosService extends ServicePadrao {
	
	public Parametros loadMinimo();
	public abstract Long getNumberRecordsFilter(ObjetoPadrao obj);
	public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
}
