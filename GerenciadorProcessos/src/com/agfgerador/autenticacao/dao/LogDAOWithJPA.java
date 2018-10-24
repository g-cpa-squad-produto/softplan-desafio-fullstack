package com.agfgerador.autenticacao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.domain.Log;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;


@Repository
@Transactional("txManagerAutenticacao")
public class LogDAOWithJPA implements LogDAO {

	@PersistenceContext(unitName="autenticacao")
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Log> findByNome(String nome) {
		Query query = entityManager.createQuery("Select m from Log m where m.nome LIKE :nome");
		query.setParameter("nome", nome + "%");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Log> filter(Log obj, boolean allHabilitado){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Log.class);
	    return criteria.list();	    
	}
	
	public ObjetoPadrao loadById(Long id) {
		return entityManager.find(Log.class, id);
	}

	public void persist(ObjetoPadrao obj) {
		entityManager.persist(obj);
	}

	public void update(ObjetoPadrao obj) {
		entityManager.merge(obj);
	}

	public void delete(ObjetoPadrao obj) {
		entityManager.createQuery("DELETE FROM Log m WHERE m.id=:id").setParameter("id", obj.getId()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<ObjetoPadrao> findAll() {
	
		return entityManager.createQuery("Select m from Log m").getResultList();
	
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
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