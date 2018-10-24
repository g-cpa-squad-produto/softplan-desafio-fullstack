package com.agfgerador.autenticacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.dao.ComponenteDAO;
import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.Metodo;
import com.agfgerador.autenticacao.domain.Modulo;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;

@Service("componenteService")
public class ComponenteServiceImpl implements ComponenteService {
	
	private ComponenteDAO componenteDAO;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	public ComponenteServiceImpl(ComponenteDAO componenteDAO) {
		this.componenteDAO = componenteDAO;
	}
	
	@Transactional
	public void createNew(ObjetoPadrao obj) {
		componenteDAO.persist(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.ADICIONAR);
	}

	@Transactional(readOnly=true)
	public List<ObjetoPadrao> findAll() {
		return componenteDAO.findAll();
	}

	@Transactional
	public void update(ObjetoPadrao obj) {
		componenteDAO.update(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.ATUALIZAR);
	}
	
	@Transactional
	public void remove(ObjetoPadrao obj) {
		componenteDAO.delete(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.REMOVER);
	}

	@Transactional(readOnly=true)
	public ObjetoPadrao findById(Long id) {
		return componenteDAO.loadById(id);
	}

	@Transactional(readOnly=true)
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
		return componenteDAO.filter(obj);
	}
	
	@Override
	@Transactional(readOnly=true)
	public ObjetoPadrao filtercombobox(ObjetoPadrao obj) {
		return componenteDAO.filtercombobox(obj);
	}
	

	@Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
		return componenteDAO.findAll(pagesize, page);
	}

	@Override
	public Long getNumberRecords() {
		return componenteDAO.getNumberRecords();
	}

	@Transactional(readOnly=true)
	public List<Componente> findAllBySistema(TipoSistema s) {
		return componenteDAO.findAllBySistema(s);
	}

	@Transactional(readOnly=true)
	public Componente findByNomeAndSistema(String nome, TipoSistema s) {
		return componenteDAO.findByNomeAndSistema(nome, s);
	}

	@Transactional(readOnly=true)
	public List<Componente> findMenuBySistema(TipoSistema s) {
		return componenteDAO.findMenuBySistema(s);
	}

	@Transactional(readOnly=true)
	public List<Componente> findMenuItemByMenu(String menu, TipoSistema s) {
		return componenteDAO.findMenuItemByMenu(menu, s);
	}
	
	@Transactional(readOnly=true)
	public List<Componente> findMenuItemByMenuBuscaIndex(Componente componente,UsuarioPerfil usuperfil, String campoBusca) {
		return componenteDAO.findMenuItemByMenuBuscaIndex(componente, usuperfil, campoBusca);
	}

	@Override
	public List<ObjetoPadrao> findAll(String value, int pagesize, int page) {

		return null;
	}

	@Override
	public Long getNumberRecords(String value) {

		return null;
	}

	@Transactional(readOnly=true)
	public List<String> findMenus(TipoSistema s) {
		return componenteDAO.findMenus(s);
	}

	@Override
	public void createNew(ObjetoPadraoSemId obj) {

	}
	
    @Override
    public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page){
      return componenteDAO.filter(obj, pagesize, page);
    }
    
    @Override
    public Long getNumberRecordsFilter(ObjetoPadrao obj){
      return componenteDAO.getNumberRecordsFilter(obj);
    }
    
    @Override
    public List<ObjetoPadrao> filtercombobox(ObjetoPadrao obj, int pagesize, int page){
      return componenteDAO.filtercombobox(obj, pagesize, page);
    }
    
    @Override
    public Long getNumberRecordsFiltercombobox(ObjetoPadrao obj){
      return componenteDAO.getNumberRecordsFiltercombobox(obj);
    }
}


