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

import com.agfgerador.autenticacao.domain.TipoUsuario;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

@Repository
@Transactional("txManagerAutenticacao")
public class UsuarioDAOWithJPA implements UsuarioDAO {

	@PersistenceContext(unitName="autenticacao")
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Usuario> findByNome(String nome) {
		Query query = entityManager.createQuery("Select m from Usuario m where m.nome LIKE :nome");
		query.setParameter("nome", nome + "%");

		return query.getResultList();
	}

	public Usuario findByLogin(String login){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
	    return (Usuario) criteria.uniqueResult();	    
	}
	
	public Usuario findByAutentication(String login,String senha){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("senha", senha));
		criteria.add(Restrictions.eq("habilitado", true));
	    return (Usuario) criteria.uniqueResult();	    
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> filter(Usuario obj, boolean allHabilitado, TipoUsuario tipo){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		if(obj.getId() != 0)
			criteria.add(Restrictions.eq("id", obj.getId()));
		if((obj.getNome()!=null)&&(!obj.getNome().equals("")))
			criteria.add(Restrictions.ilike("nome",obj.getNome() +"%"));
		if((obj.getLogin()!=null)&&(!obj.getLogin().equals("")))
			criteria.add(Restrictions.ilike("login",obj.getLogin() +"%"));   
		if(!allHabilitado)
			criteria.add(Restrictions.eq("habilitado", obj.isHabilitado()));
		if((obj.getEmail()!=null)&&(!obj.getEmail().equals("")))
			criteria.add(Restrictions.ilike("email",obj.getEmail() +"%"));
	
	   if(tipo.equals(TipoUsuario.Analista)){	   
		   criteria.add(Restrictions.or(Restrictions.eq("tipo", TipoUsuario.Comum),
		   Restrictions.eq("tipo", TipoUsuario.Gestor)));  
	   }
	   else if(tipo.equals(TipoUsuario.Gestor))
		   criteria.add(Restrictions.eq("tipo", TipoUsuario.Comum));  
		return criteria.list();	    
	}
	
	public ObjetoPadrao loadById(Long id) {
		return entityManager.find(Usuario.class, id);
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
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("tipo", TipoUsuario.Comum));
		criteria.add(Restrictions.eq("habilitado", true));
		return criteria.list();	    
	
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public  List<Usuario> findGestorControla(){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		//criteria.add(Restrictions.eq("tipo", TipoUsuario.Comum));
		//criteria.add(Restrictions.eq("habilitado", true));
		return criteria.list();	    
	}

	@SuppressWarnings("unchecked")
	public  List<Usuario> findAnalistaControla(){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		 criteria.add(Restrictions.or(Restrictions.eq("tipo", TipoUsuario.Comum),
				 Restrictions.eq("tipo", TipoUsuario.Gestor)));  
		 criteria.add(Restrictions.eq("habilitado", true));
		return criteria.list();	    
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		//criteria.add(Restrictions.eq("tipo", TipoUsuario.Comum));
	    //criteria.add(Restrictions.eq("habilitado", true));
	    criteria.setFirstResult(page);
	    criteria.setMaxResults(pagesize);
	    return criteria.list();
	}

	@Override
	public Long getNumberRecords() {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
	    criteria.add(Restrictions.eq("habilitado", true));
	    criteria.setProjection(Projections.rowCount());
	    return (Long)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObjetoPadrao> findAll(String value, int pagesize, int page) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		if((value!=null)&&(!value.equals("")))
			criteria.add(Restrictions.ilike("nome",value+"%"));
	    criteria.add(Restrictions.eq("habilitado", true));
	    criteria.add(Restrictions.eq("tipo", TipoUsuario.Comum));
		return criteria.list();	 
	}

	@Override
	public Long getNumberRecords(String value) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		if((value!=null)&&(!value.equals("")))
			criteria.add(Restrictions.ilike("nome",value+"%"));
	    criteria.add(Restrictions.eq("habilitado", true));
	    criteria.setProjection(Projections.rowCount());
	    return (Long)criteria.uniqueResult();
	}

	@Override
	public Long avisos(boolean temAvisos) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		if(temAvisos){
			criteria.add(Restrictions.isNotNull("aviso"));
		}
		else{
			criteria.add(Restrictions.isNull("aviso"));
		}
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		if(obj.getId() != 0){
			criteria.add(Restrictions.eq("id", obj.getId()));
		}
		if((((Usuario)obj).getNome()!=null)&&(!((Usuario)obj).getNome().equals(""))){
			criteria.add(Restrictions.ilike("nome",((Usuario)obj).getNome()+"%"));
		}
		if((((Usuario)obj).getLogin()!=null)&&(!((Usuario)obj).getLogin().equals(""))){
			criteria.add(Restrictions.ilike("login",((Usuario)obj).getLogin() +"%"));  
		}
		if((((Usuario)obj).getEmail()!=null)&&(!((Usuario)obj).getEmail().equals(""))){
			criteria.add(Restrictions.ilike("email",((Usuario)obj).getEmail() +"%"));
		}
	   
	      if(((Usuario)obj).isHabilitado() != null)
	          criteria.add(Restrictions.eq("habilitado", ((Usuario)obj).isHabilitado()));
	   
	        criteria.setFirstResult(page);
	 		criteria.setMaxResults(pagesize);
	       
	       return criteria.list();
	}
	
	@Override
	public Long getNumberRecordsFilter(ObjetoPadrao obj) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		if(obj.getId() != 0)
			criteria.add(Restrictions.eq("id", obj.getId()));
		if((((Usuario)obj).getNome()!=null)&&(!((Usuario)obj).getNome().equals("")))
			criteria.add(Restrictions.ilike("nome",((Usuario)obj).getNome() +"%"));
		if((((Usuario)obj).getLogin()!=null)&&(!((Usuario)obj).getLogin().equals("")))
			criteria.add(Restrictions.ilike("login",((Usuario)obj).getLogin() +"%"));   
		if((((Usuario)obj).getEmail()!=null)&&(!((Usuario)obj).getEmail().equals("")))
			criteria.add(Restrictions.ilike("email",((Usuario)obj).getEmail() +"%"));   
	
	      if(((Usuario)obj).isHabilitado() != null)
	          criteria.add(Restrictions.eq("habilitado", ((Usuario)obj).isHabilitado()));
	       
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.list().get(0);
	}
	
    @SuppressWarnings("unchecked")
    @Override
    public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
      Session session = (Session) entityManager.getDelegate();
      Criteria criteria = session.createCriteria(Usuario.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      if((((Usuario)obj).getNome() != null)&&(!((Usuario)obj).getNome().equals(""))) 
        criteria.add(Restrictions.ilike("nome",((Usuario)obj).getNome()+"%")); 

      if((((Usuario)obj).getLogin() != null)&&(!((Usuario)obj).getLogin().equals(""))) 
        criteria.add(Restrictions.ilike("login",((Usuario)obj).getLogin()+"%")); 

      if(((Usuario)obj).isHabilitado() != null)
        criteria.add(Restrictions.eq("habilitado", ((Usuario)obj).isHabilitado()));

      if((((Usuario)obj).getEmail() != null)&&(!((Usuario)obj).getEmail().equals(""))) 
        criteria.add(Restrictions.ilike("email",((Usuario)obj).getEmail()+"%")); 

      return criteria.list();
    }
	
}