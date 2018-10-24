package com.agfgerador.autenticacao.service;
import com.agfgerador.autenticacao.domain.Compativeis;
import com.agfgerador.compartilhado.service.ServicePadrao;


public interface CompativeisService extends ServicePadrao {

	public Compativeis LoadBySistema(Integer codsistema);
}
