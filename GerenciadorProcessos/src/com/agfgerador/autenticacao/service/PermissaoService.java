package com.agfgerador.autenticacao.service;

import java.util.List;

import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.Perfil;
import com.agfgerador.autenticacao.domain.Permissao;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.service.ServicePadrao;

public interface PermissaoService extends ServicePadrao {

	public List<Permissao> findByNome(String nome);
	
	public List<Permissao> findByPerfil(Perfil p);

	public List<Componente> findComponentesByPerfil(Perfil p,TipoSistema sist,Boolean isAtalho);
	
	public boolean temPemissaoComponente(Perfil p,TipoSistema sist,Componente comp,boolean isAtalho);
	
	public abstract Long getNumberRecordsFilter(ObjetoPadrao obj);
	   
	public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
}
