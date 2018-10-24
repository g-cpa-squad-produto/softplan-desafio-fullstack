package com.agfgerador.autenticacao.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.dao.CompativeisDAO;
import com.agfgerador.autenticacao.domain.Compativeis;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;


@Service("compativeisService")
public class CompativeisServiceImpl implements CompativeisService{

	private CompativeisDAO compativeisDAO;
	Compativeis compativeis;
	SecurityContext ctx = null;
	
	@Autowired
	public CompativeisServiceImpl(CompativeisDAO compativeisDAO) {
		this.compativeisDAO = compativeisDAO;
	}
	
	@Transactional
	public void createNew(ObjetoPadrao obj) {
		compativeisDAO.persist(obj);
	}
	
	@Transactional
	public void update(ObjetoPadrao obj) {
		compativeisDAO.update(obj);
	}
	
	@Transactional
	public void remove(ObjetoPadrao obj) {
		compativeisDAO.delete(obj);
	}

	@Transactional(readOnly=true)
	public ObjetoPadrao findById(Long id) {
		return compativeisDAO.loadById(id);
	}

	@Transactional(readOnly=true)
	public Compativeis LoadBySistema(Integer codsistema) {
		return compativeisDAO.LoadBySistema(codsistema);
	}

	@Override
	public List<ObjetoPadrao> findAll() {

		return null;
	}

	@Override
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {

		return null;
	}

	@Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {

		return null;
	}

	@Override
	public Long getNumberRecords() {

		return null;
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


}