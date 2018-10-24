package com.agfgerador.autenticacao.dao;

import java.util.List;

import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.compartilhado.dao.DAOPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

public interface ComponenteDAO extends DAOPadrao {
	
	List<Componente> findAllBySistema(TipoSistema s);
	
	Componente findByNomeAndSistema(String nome,TipoSistema s);
	
	List<Componente> findMenuBySistema(TipoSistema s);
	
	List<Componente> findMenuItemByMenu(String menu,TipoSistema s);
	
	List<Componente> findMenuItemByMenuBuscaIndex(Componente componente,UsuarioPerfil usuperfil, String campoBusca);
	
	List<String> findMenus(TipoSistema s);
	
    public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);
    
    public Long getNumberRecordsFilter(ObjetoPadrao obj);

	Long getNumberRecordsFiltercombobox(ObjetoPadrao obj);

	List<ObjetoPadrao> filtercombobox(ObjetoPadrao obj, int pagesize, int page);

	ObjetoPadrao filtercombobox(ObjetoPadrao obj);

}
