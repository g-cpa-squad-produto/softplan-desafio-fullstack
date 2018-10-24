package com.agfgerador.autenticacao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.dao.LogDAO;
import com.agfgerador.autenticacao.domain.Log;
import com.agfgerador.autenticacao.domain.Metodo;
import com.agfgerador.autenticacao.domain.Modulo;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;


@Service("logService")
public class LogServiceImpl implements LogService{

	private LogDAO logDAO;
	Log log;
	SecurityContext ctx = null;
	
	@Autowired
	public LogServiceImpl(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
	
	@Transactional
	public void createNew(Object obj, Modulo modulo, Metodo metodo) {
		ctx = SecurityContextHolder.getContext();
		log = new Log();
		log.setData(new Date());
		log.setModulo(modulo);
		log.setDomainName(obj.getClass().getSimpleName());
		log.setIdObject(((ObjetoPadrao)obj).getId());
		log.setMetodo(metodo);	
		log.setValorAtributos(log.getValorAtributos(obj));
		log.setUsuarioLogin(ctx.getAuthentication().getName());
		logDAO.persist(log);
	}
	
	@Transactional
	public void createNewSemId(Object obj, Modulo modulo, Metodo metodo) {
		ctx = SecurityContextHolder.getContext();
		log = new Log();
		log.setData(new Date());
		log.setModulo(modulo);
		log.setDomainName(obj.getClass().getSimpleName());
		log.setIdObject(((ObjetoPadraoSemId)obj).getId());
		log.setMetodo(metodo);	
		log.setValorAtributos(log.getValorAtributosSemId(obj));
		log.setUsuarioLogin(ctx.getAuthentication().getName());
		logDAO.persist(log);
	}

	@Transactional(readOnly=true)
	public List<ObjetoPadrao> findAll() {
		return logDAO.findAll();
	}

	@Transactional(readOnly=true)
	public List<Log> findByNome(String nome) {
		return logDAO.findByNome(nome);
	}

	@Transactional
	public void update(ObjetoPadrao obj) {
		logDAO.update(obj);
	}
	
	@Transactional
	public void remove(ObjetoPadrao obj) {
		logDAO.delete(obj);
	}

	@Transactional(readOnly=true)
	public ObjetoPadrao findById(Long id) {
		return logDAO.loadById(id);
	}

	@Transactional(readOnly=true)
	public List<Log> filter(Log obj, boolean allHabilitado) {
		return logDAO.filter(obj, allHabilitado);
	}

	@Override
	public void createNew(ObjetoPadrao obj) {

		
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
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {

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