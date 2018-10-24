package com.agfgerador.autenticacao.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.dao.ParametrosDAO;
import com.agfgerador.autenticacao.domain.Parametros;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;


@Service("parametrosService")
public class ParametrosServiceImpl implements ParametrosService{

	private ParametrosDAO parametrosDAO;
	Parametros parametros;
	SecurityContext ctx = null;
	
	@Autowired
	public ParametrosServiceImpl(ParametrosDAO parametrosDAO) {
		this.parametrosDAO = parametrosDAO;
	}
	
	@Transactional
	public void createNew(ObjetoPadrao obj) {
		parametrosDAO.persist(obj);
	}

	@Transactional(readOnly=true)
	public List<ObjetoPadrao> findAll() {
		return parametrosDAO.findAll();
	}

	@Transactional
	public void update(ObjetoPadrao obj) {
		parametrosDAO.update(obj);
	}
	
	@Transactional
	public void remove(ObjetoPadrao obj) {
		parametrosDAO.delete(obj);
	}

	@Transactional(readOnly=true)
	public ObjetoPadrao findById(Long id) {
		return parametrosDAO.loadById(id);
	}
	@Transactional(readOnly=true)
	public Parametros loadMinimo()
	{
		return parametrosDAO.loadMinimo();
	}

	@Override
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
		return null;
	}

	@Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
		return parametrosDAO.findAll(pagesize, page);
	}

	@Override
	public Long getNumberRecords() {
		return parametrosDAO.getNumberRecords();
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
	
    @Override
    public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page){
      return parametrosDAO.filter(obj, pagesize, page);
    }

    @Override
    public Long getNumberRecordsFilter(ObjetoPadrao obj){
      return parametrosDAO.getNumberRecordsFilter(obj);
    }
}