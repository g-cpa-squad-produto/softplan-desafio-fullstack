package com.agfgerador.autenticacao.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.agfgerador.autenticacao.domain.Parametros;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

@Repository
@Transactional("txManagerAutenticacao")
public class ParametrosDAOWithJPA implements ParametrosDAO {

	@PersistenceContext(unitName="autenticacao")
	protected EntityManager entityManager;
		
	public ObjetoPadrao loadById(Long id) {
		return entityManager.find(Parametros.class, id);
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

	@SuppressWarnings("unchecked")
	public List<ObjetoPadrao> findAll() {	
		Query query = entityManager.createQuery("Select m from Parametros m");		 
		return query.getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Parametros loadMinimo() {	
		Query query = entityManager.createQuery("Select min(m.id) from Parametros m");
		Object valores = query.getSingleResult(); 
		String id = (String) valores.toString();
		return entityManager.find(Parametros.class,Long.parseLong(id));		
	}

    @SuppressWarnings("unchecked")
    @Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
	       Session session  = (Session) entityManager.getDelegate();
	       Criteria criteria = session.createCriteria(Parametros.class);
	       criteria.setFirstResult(page);
	       criteria.setMaxResults(pagesize);
	       return criteria.list();
	}

	@Override
	public Long getNumberRecords() {
	     Session session = (Session) entityManager.getDelegate();
	     Criteria criteria = session.createCriteria(Parametros.class);
	     criteria.setProjection(Projections.rowCount());
	     return (Long) criteria.list().get(0);
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
	
    @SuppressWarnings("unchecked")
    @Override
    public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page) {
      Session session = (Session) entityManager.getDelegate();
      Criteria criteria = session.createCriteria(Parametros.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      if((((Parametros)obj).getNome() != null)&&(!((Parametros)obj).getNome().equals(""))) 
        criteria.add(Restrictions.ilike("nome",((Parametros)obj).getNome()+"%")); 

      if((((Parametros)obj).getNomereduzido() != null)&&(!((Parametros)obj).getNomereduzido().equals(""))) 
        criteria.add(Restrictions.ilike("nomereduzido",((Parametros)obj).getNomereduzido()+"%")); 

      if((((Parametros)obj).getCnpj() != null)&&(!((Parametros)obj).getCnpj().equals(""))) 
        criteria.add(Restrictions.ilike("cnpj",((Parametros)obj).getCnpj()+"%")); 

      criteria.setFirstResult(page);
      criteria.setMaxResults(pagesize);
      return criteria.list();
    }

    @Override
    public Long getNumberRecordsFilter(ObjetoPadrao obj) {
      Session session = (Session) entityManager.getDelegate();
      Criteria criteria = session.createCriteria(Parametros.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      if((((Parametros)obj).getNome() != null)&&(!((Parametros)obj).getNome().equals(""))) 
        criteria.add(Restrictions.ilike("nome",((Parametros)obj).getNome()+"%")); 

      if((((Parametros)obj).getNomereduzido() != null)&&(!((Parametros)obj).getNomereduzido().equals(""))) 
        criteria.add(Restrictions.ilike("nomereduzido",((Parametros)obj).getNomereduzido()+"%")); 

      if((((Parametros)obj).getCnpj() != null)&&(!((Parametros)obj).getCnpj().equals(""))) 
        criteria.add(Restrictions.ilike("cnpj",((Parametros)obj).getCnpj()+"%")); 

      criteria.setProjection(Projections.rowCount());
      return (Long) criteria.list().get(0);
    }


}