package com.agfgerador.autenticacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.dao.EmailDAO;
import com.agfgerador.autenticacao.domain.Email;
import com.agfgerador.autenticacao.domain.Metodo;
import com.agfgerador.autenticacao.domain.Modulo;
import com.agfgerador.autenticacao.domain.Parametros;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	private EmailDAO EmailDAO;

	@Autowired
	private LogService logService;

	@Autowired
	public EmailServiceImpl(EmailDAO EmailDAO) {
		this.EmailDAO = EmailDAO;
	}

	@Transactional
	public void createNew(ObjetoPadrao obj) {
		EmailDAO.persist(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.ADICIONAR);
	}

	@Transactional(readOnly = true)
	public List<ObjetoPadrao> findAll() {
		return EmailDAO.findAll();
	}

	@Transactional
	public void update(ObjetoPadrao obj) {
		EmailDAO.update(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.ATUALIZAR);
	}

	@Transactional
	public void remove(ObjetoPadrao obj) {
		EmailDAO.delete(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.REMOVER);
	}

	@Transactional(readOnly = true)
	public ObjetoPadrao findById(Long id) {
		return EmailDAO.loadById(id);
	}

	@Transactional(readOnly = true)
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
		return EmailDAO.filter(obj);
	}

	@Transactional(readOnly = true)
	public List<Email> loadByParametros(Parametros parametro) {
		return EmailDAO.loadByParametros(parametro);
	}

	@Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
		return EmailDAO.findAll(pagesize, page);
	}

	@Override
	public Long getNumberRecords() {
		return EmailDAO.getNumberRecords();
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
    public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page){
      return EmailDAO.filter(obj, pagesize, page);
    }

    @Override
    public Long getNumberRecordsFilter(ObjetoPadrao obj){
      return EmailDAO.getNumberRecordsFilter(obj);
    }

	@Override
	public void createNew(ObjetoPadraoSemId obj) {

	}
}
