package com.agfgerador.autenticacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.dao.AvisoDAO;
import com.agfgerador.autenticacao.domain.Aviso;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
@Service("avisoService")
public class AvisoServiceImpl implements AvisoService {
	
	private AvisoDAO avisoDAO;
	
	@SuppressWarnings("unused")
	@Autowired
	private LogService logService;
	
	
	@Autowired
	public AvisoServiceImpl(AvisoDAO avisoDAO) {
		this.avisoDAO = avisoDAO;
	}
	@Transactional
	@Override
	public void createNew(ObjetoPadrao obj) {
		avisoDAO.persist(obj);
		
	}
	@Transactional
	@Override
	public void update(ObjetoPadrao obj) {
		avisoDAO.update(obj);
	}
	@Transactional
	@Override
	public void remove(ObjetoPadrao obj) {
		avisoDAO.delete(obj);
	}
	@Transactional(readOnly = true)
	@Override
	public ObjetoPadrao findById(Long id) {
		return avisoDAO.loadById(id);
	}
	@Transactional(readOnly = true)
	@Override
	public List<ObjetoPadrao> findAll() {

		return null;
	}
	@Transactional(readOnly = true)
	@Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
		return avisoDAO.findAll(pagesize, page);
	}
	@Transactional(readOnly = true)
	@Override
	public Long getNumberRecords() {
		return avisoDAO.getNumberRecords();
	}
	@Transactional(readOnly = true)
	@Override
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
		return avisoDAO.filter(obj);
	}
	@Transactional(readOnly = true)
	@Override
	public List<ObjetoPadrao> findAll(String value, int pagesize, int page) {

		return null;
	}
	@Transactional(readOnly = true)
	@Override
	public Long getNumberRecords(String value) {

		return null;
	}
	@Transactional(readOnly = true)
	@Override
	public List<Aviso> findBySistema(TipoSistema sis) {

		return null;
	}
	@Override
	public void createNew(ObjetoPadraoSemId obj) {

		
	}

}
