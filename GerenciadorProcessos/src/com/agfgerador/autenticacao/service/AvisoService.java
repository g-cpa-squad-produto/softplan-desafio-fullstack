package com.agfgerador.autenticacao.service;

import java.util.List;
import com.agfgerador.autenticacao.domain.Aviso;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.compartilhado.service.ServicePadrao;

public interface AvisoService extends ServicePadrao{
	public List<Aviso> findBySistema(TipoSistema sis);
}
