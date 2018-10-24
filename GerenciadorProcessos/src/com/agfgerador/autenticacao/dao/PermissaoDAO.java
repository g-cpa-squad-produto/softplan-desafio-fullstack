package com.agfgerador.autenticacao.dao;

import java.util.List;

import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.Perfil;
import com.agfgerador.autenticacao.domain.Permissao;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.compartilhado.dao.DAOPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

public interface PermissaoDAO extends DAOPadrao {
	
	List<Permissao> findByNome(String nome);
	
	List<Permissao> findByPerfil(Perfil p);
	
	List<Componente> findComponentesByPerfil(Perfil p,TipoSistema sist,Boolean isAtalho);
	
    boolean temPemissaoComponente(Perfil p,TipoSistema sist,Componente comp,boolean isAtalho);
    
    public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
    
    public Long getNumberRecordsFilter(ObjetoPadrao obj);
}
