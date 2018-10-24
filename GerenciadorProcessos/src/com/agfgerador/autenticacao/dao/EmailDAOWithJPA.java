package com.agfgerador.autenticacao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.domain.Email;
import com.agfgerador.autenticacao.domain.Parametros;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
@Repository
@Transactional("txManagerAutenticacao")

public class EmailDAOWithJPA implements EmailDAO {
	@PersistenceContext(unitName="autenticacao")
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<ObjetoPadrao> filter(ObjetoPadrao obj){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Email.class);
		if(obj.getId() != 0)
			criteria.add(Restrictions.eq("id", obj.getId()));	    
	    return criteria.list();	    
	}
	@SuppressWarnings("unchecked")
	public List<Email> loadByParametros(Parametros parametro)
	{
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Email.class);
		criteria.add(Restrictions.eq("parametro.id", parametro.getId()));
		return criteria.list();
		
	}
	public ObjetoPadrao loadById(Long id) {
		return entityManager.find(Email.class, id);
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
	
		return entityManager.createQuery("Select m from Email m").getResultList();	
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
    @SuppressWarnings("unchecked")
    @Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
	       Session session  = (Session) entityManager.getDelegate();
	       Criteria criteria = session.createCriteria(Email.class);
	       criteria.setFirstResult(page);
	       criteria.setMaxResults(pagesize);
	       return criteria.list();
	}
	@Override
	public Long getNumberRecords() {
	     Session session = (Session) entityManager.getDelegate();
	     Criteria criteria = session.createCriteria(Email.class);
	     criteria.setProjection(Projections.rowCount());
	     return (Long) criteria.list().get(0);
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
      Criteria criteria = session.createCriteria(Email.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      if((((Email)obj).getServidor() != null)&&(!((Email)obj).getServidor().equals(""))) 
        criteria.add(Restrictions.ilike("servidor",((Email)obj).getServidor()+"%")); 

      if((((Email)obj).getPorta() != null)) 
        criteria.add(Restrictions.eq("porta",((Email)obj).getPorta())); 

      if((((Email)obj).getLogin() != null)&&(!((Email)obj).getLogin().equals(""))) 
        criteria.add(Restrictions.ilike("login",((Email)obj).getLogin()+"%")); 

      if((((Email)obj).getSenha() != null)&&(!((Email)obj).getSenha().equals(""))) 
        criteria.add(Restrictions.ilike("senha",((Email)obj).getSenha()+"%")); 

      if((((Email)obj).getEmail() != null)&&(!((Email)obj).getEmail().equals(""))) 
        criteria.add(Restrictions.ilike("email",((Email)obj).getEmail()+"%")); 

      int x1=0;
      if((((Email)obj).getParametro().getNome() != null)&&(!((Email)obj).getParametro().getNome().equals(""))){ 
        criteria.createAlias("parametro", "parametro");
        criteria.add(Restrictions.ilike("parametro.nome",((Email)obj).getParametro().getNome()+"%")); 
        x1=1;
      }
      if(((Email)obj).getParametro().getId() != 0) {
         if(x1==0) {
            criteria.createAlias("parametro", "parametro");
         }
         criteria.add(Restrictions.eq("parametro.id", ((Email)obj).getParametro().getId()));
      }
      criteria.setFirstResult(page);
      criteria.setMaxResults(pagesize);
      return criteria.list();
    }

    @Override
    public Long getNumberRecordsFilter(ObjetoPadrao obj) {
      Session session = (Session) entityManager.getDelegate();
      Criteria criteria = session.createCriteria(Email.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      if((((Email)obj).getServidor() != null)&&(!((Email)obj).getServidor().equals(""))) 
        criteria.add(Restrictions.ilike("servidor",((Email)obj).getServidor()+"%")); 

      if((((Email)obj).getPorta() != null)) 
        criteria.add(Restrictions.eq("porta",((Email)obj).getPorta())); 

      if((((Email)obj).getLogin() != null)&&(!((Email)obj).getLogin().equals(""))) 
        criteria.add(Restrictions.ilike("login",((Email)obj).getLogin()+"%")); 

      if((((Email)obj).getSenha() != null)&&(!((Email)obj).getSenha().equals(""))) 
        criteria.add(Restrictions.ilike("senha",((Email)obj).getSenha()+"%")); 

      if((((Email)obj).getEmail() != null)&&(!((Email)obj).getEmail().equals(""))) 
        criteria.add(Restrictions.ilike("email",((Email)obj).getEmail()+"%")); 

      int x2=0;
      if((((Email)obj).getParametro().getNome() != null)&&(!((Email)obj).getParametro().getNome().equals(""))){ 
        criteria.createAlias("parametro", "parametro");
        criteria.add(Restrictions.ilike("parametro.nome",((Email)obj).getParametro().getNome()+"%")); 
        x2=1;
      }
      if(((Email)obj).getParametro().getId() != 0) {
         if(x2==0) {
            criteria.createAlias("parametro", "parametro");
         }
         criteria.add(Restrictions.eq("parametro.id", ((Email)obj).getParametro().getId()));
      }
      criteria.setProjection(Projections.rowCount());
      return (Long) criteria.list().get(0);
    }

}
