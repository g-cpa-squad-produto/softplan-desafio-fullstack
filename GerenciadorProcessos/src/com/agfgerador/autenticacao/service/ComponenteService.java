package com.agfgerador.autenticacao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.service.ServicePadrao;

public interface ComponenteService extends ServicePadrao {
	
	public List<Componente> findAllBySistema(TipoSistema s);
	
	public Componente findByNomeAndSistema(String nome,TipoSistema s);
	
	public List<Componente> findMenuBySistema(TipoSistema s);
	
	public List<Componente> findMenuItemByMenu(String menu,TipoSistema s);
	
	public List<Componente> findMenuItemByMenuBuscaIndex(Componente componente,UsuarioPerfil usuperfil, String campoBusca);
	
	public List<String> findMenus(TipoSistema s);
	
	public abstract Long getNumberRecordsFilter(ObjetoPadrao obj);
	
	public abstract List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page);

	Long getNumberRecordsFiltercombobox(ObjetoPadrao obj);

	List<ObjetoPadrao> filtercombobox(ObjetoPadrao obj, int pagesize, int page);

	ObjetoPadrao filtercombobox(ObjetoPadrao obj);

}
