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

import com.agfgerador.autenticacao.domain.Aviso;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
@Repository
@Transactional("txManagerAutenticacao")
public class AvisoDAOWithJPA implements AvisoDAO {

	@PersistenceContext(unitName="autenticacao")
	protected EntityManager entityManager;
		
	public ObjetoPadrao loadById(Long id) {
		return entityManager.find(Aviso.class, id);
	}

	@Override
	public void persist(ObjetoPadrao obj) {
		entityManager.persist(obj); 
		
	}

	@Override
	public void update(ObjetoPadrao obj) {
		entityManager.merge(obj); 
		
	}

	@Override
	public void delete(ObjetoPadrao obj) {
		entityManager.remove(entityManager.merge(obj));
		
	}

	@Override
	public List<ObjetoPadrao> findAll() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Aviso.class);
		criteria.setFirstResult(page);
		criteria.setMaxResults(pagesize);
		return criteria.list();
	}

	@Override
	public Long getNumberRecords() {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Aviso.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Aviso.class);
		
		if(obj.getId() != 0)
			criteria.add(Restrictions.eq("id", obj.getId()));
	    if(((Aviso)obj).getSistema()!=null)
	    	criteria.add(Restrictions.eq("sistema", ((Aviso)obj).getSistema()));
	    
	    if((((Aviso)obj).getTitulo()!=null)&&(!((Aviso)obj).getTitulo().equals("")))
	    	criteria.add(Restrictions.ilike("titulo", ((Aviso)obj).getTitulo()+"%"));
	    
	    if((((Aviso)obj).getDescricao()!=null)&&(!((Aviso)obj).getDescricao().equals("")))
	    	criteria.add(Restrictions.ilike("descricao", ((Aviso)obj).getDescricao()+"%"));
	    
	    return criteria.list();
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
	public List<Aviso> findBySistema(TipoSistema sis) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Aviso.class);
		criteria.add(Restrictions.eq("sistema",sis)); 
	    return criteria.list();	    
	}

}
