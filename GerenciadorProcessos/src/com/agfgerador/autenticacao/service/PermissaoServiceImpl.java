package com.agfgerador.autenticacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.dao.PermissaoDAO;
import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.Metodo;
import com.agfgerador.autenticacao.domain.Modulo;
import com.agfgerador.autenticacao.domain.Perfil;
import com.agfgerador.autenticacao.domain.Permissao;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;



@Service("permissaoService")
public class PermissaoServiceImpl implements PermissaoService{

	private PermissaoDAO permissaoDAO;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	public PermissaoServiceImpl(PermissaoDAO permissaoDAO) {
		this.permissaoDAO = permissaoDAO;
	}
	
	@Transactional
	public void createNew(ObjetoPadrao obj) {
		permissaoDAO.persist(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.ADICIONAR);
	}

	@Transactional(readOnly=true)
	public List<ObjetoPadrao> findAll() {
		return permissaoDAO.findAll();
	}

	@Transactional(readOnly=true)
	public List<Permissao> findByNome(String nome) {
		return permissaoDAO.findByNome(nome);
	}

	@Transactional
	public void update(ObjetoPadrao obj) {
		permissaoDAO.update(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.ATUALIZAR);
	}
	
	@Transactional
	public void remove(ObjetoPadrao obj) {
		permissaoDAO.delete(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.REMOVER);
	}

	@Transactional(readOnly=true)
	public ObjetoPadrao findById(Long id) {
		return permissaoDAO.loadById(id);
	}

	@Transactional(readOnly=true)
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
		return permissaoDAO.filter(obj);
	}

	@Transactional(readOnly=true)
	public List<Permissao> findByPerfil(Perfil p) {
		return permissaoDAO.findByPerfil(p);
	}
	
	@Transactional(readOnly=true)
	public List<Componente> findComponentesByPerfil(Perfil p,TipoSistema sist,Boolean isAtalho) {
		return permissaoDAO.findComponentesByPerfil(p,sist,isAtalho);
	}

	@Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
		return permissaoDAO.findAll(pagesize, page);
	}

	@Override
	public Long getNumberRecords() {
		return permissaoDAO.getNumberRecords();
	}

	@Override
	public boolean temPemissaoComponente(Perfil p, TipoSistema sist,Componente comp,boolean isAtalho) {
		return permissaoDAO.temPemissaoComponente(p, sist, comp,isAtalho);
	}

	@Override
	public List<ObjetoPadrao> findAll(String value, int pagesize, int page) {
		return null;
	}

	@Override
	public Long getNumberRecords(String value) {
		return null;
	}

	@Override
	public void createNew(ObjetoPadraoSemId obj) {

	}
	
    public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page){
    	return permissaoDAO.filter(obj, pagesize, page);
    }
    
    public Long getNumberRecordsFilter(ObjetoPadrao obj) {
    	return permissaoDAO.getNumberRecordsFilter(obj);
    }
}