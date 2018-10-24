package com.agfgerador.autenticacao.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.domain.Compativeis;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;


@Repository
@Transactional("txManagerAutenticacao")
public class CompativeisDAOWithJPA implements CompativeisDAO {

	@PersistenceContext(unitName="autenticacao")
	protected EntityManager entityManager;
		
	public ObjetoPadrao loadById(Long id) {
		return entityManager.find(Compativeis.class, id);
	}

	public Compativeis LoadBySistema(Integer codsistema) {
		Session session  = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Compativeis.class);
		criteria.add(Restrictions.eq("codsistema", codsistema));
		return (Compativeis) criteria.uniqueResult();	
	}

	public void persist(ObjetoPadrao obj) {
		entityManager.persist(obj);
	}

	public void update(ObjetoPadrao obj) {
		entityManager.merge(obj);
	}

	public void delete(ObjetoPadrao obj) {
		entityManager.remove(entityManager.merge(obj));
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<ObjetoPadrao> findAll() {

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

}