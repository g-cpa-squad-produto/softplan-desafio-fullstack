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

import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

@Repository
@Transactional("txManagerAutenticacao")
public class UsuarioPerfilDAOWithJPA implements UsuarioPerfilDAO {

	@PersistenceContext(unitName="autenticacao")
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<UsuarioPerfil> findByNome(String nome) {
		Query query = entityManager.createQuery("Select m from UsuarioPerfil m where m.nome LIKE :nome");
		query.setParameter("nome", nome + "%");
		return query.getResultList();
	}

	/*@SuppressWarnings("unchecked")
	public List<ObjetoPadrao> filter(ObjetoPadrao obj){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(UsuarioPerfil.class);
		if(obj.getId() != 0)
		criteria.add(Restrictions.eq("id", obj.getId()));
	    return criteria.list();	    
	}*/
	
	@SuppressWarnings("unchecked")
	public List<UsuarioPerfil> findByUsuario(Usuario p,TipoSistema s) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(UsuarioPerfil.class);
		criteria.createAlias("perfil", "p");
		criteria.add(Restrictions.eq("usuario.id", p.getId()));
		criteria.add(Restrictions.eq("p.sistema", s));
	    return criteria.list();	    
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioPerfil> findByUsuario(Usuario p) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(UsuarioPerfil.class);
		criteria.add(Restrictions.eq("usuario.id", p.getId()));
	    return criteria.list();	    
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> loadAllExcptAdmin(TipoSistema sistema){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(UsuarioPerfil.class);
		criteria.createAlias("perfil", "p");
		criteria.add(Restrictions.eq("administrador", false));
		criteria.add(Restrictions.eq("p.sistema", sistema));
		criteria.setProjection(Projections.groupProperty("usuario"));
	    return criteria.list();	    
	}

	public boolean verificaPermissaoSistemaUsuario(Usuario p,TipoSistema sistema) {
		boolean retorno = false;
		
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(UsuarioPerfil.class);
		criteria.createAlias("perfil", "pf");
	
		criteria.add(Restrictions.eq("usuario.id", p.getId()));
		criteria.add(Restrictions.eq("pf.sistema",sistema));
		if(criteria.list().size()>0)
			retorno = true;
	    
		return retorno;
	}
	
	public ObjetoPadrao loadById(Long id) {
		return entityManager.find(UsuarioPerfil.class, id);
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
		return entityManager.createQuery("Select m from UsuarioPerfil m").getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public boolean haPerfilAdm(Long codusuario){
		String tot = "0";
		int total = 0;
		Query query = entityManager.createQuery("Select count(*) from UsuarioPerfil m where m.usuario.id=:idusuario and m.administrador=true and m.ativo=true ");
		query.setParameter("idusuario", codusuario );
		Object valores = query.getSingleResult(); 
		if (valores != null){
		 tot = (String) valores.toString();
		}		

		total = Integer.parseInt(tot);
		
		if(total==0){return false;}
		else {return true;}			
	}

    @SuppressWarnings("unchecked")
    @Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
	       Session session  = (Session) entityManager.getDelegate();
	       Criteria criteria = session.createCriteria(UsuarioPerfil.class);
	       criteria.setFirstResult(page);
	       criteria.setMaxResults(pagesize);
	       return criteria.list();
	}

	@Override
	public Long getNumberRecords() {
	     Session session = (Session) entityManager.getDelegate();
	     Criteria criteria = session.createCriteria(UsuarioPerfil.class);
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
      Criteria criteria = session.createCriteria(UsuarioPerfil.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      int x2=0;
      if((((UsuarioPerfil)obj).getPerfil().getNome() != null)&&(!((UsuarioPerfil)obj).getPerfil().getNome().equals(""))){ 
        criteria.createAlias("perfil", "perfil");
        criteria.add(Restrictions.ilike("perfil.nome",((UsuarioPerfil)obj).getPerfil().getNome()+"%")); 
        x2=1;
      }
      if(((UsuarioPerfil)obj).getPerfil().getId() != 0) {
         if(x2==0) {
            criteria.createAlias("perfil", "perfil");
         }
         criteria.add(Restrictions.eq("perfil.id", ((UsuarioPerfil)obj).getPerfil().getId()));
      }
      int x3=0;
      if((((UsuarioPerfil)obj).getUsuario().getNome() != null)&&(!((UsuarioPerfil)obj).getUsuario().getNome().equals(""))){ 
        criteria.createAlias("usuario", "usuario");
        criteria.add(Restrictions.ilike("usuario.nome",((UsuarioPerfil)obj).getUsuario().getNome()+"%")); 
        x3=1;
      }
      if(((UsuarioPerfil)obj).getUsuario().getId() != 0) {
         if(x3==0) {
            criteria.createAlias("usuario", "usuario");
         }
         criteria.add(Restrictions.eq("usuario.id", ((UsuarioPerfil)obj).getUsuario().getId()));
      }
      if(((UsuarioPerfil)obj).isAtivo() != null)
        criteria.add(Restrictions.eq("ativo", ((UsuarioPerfil)obj).isAtivo()));

      if(((UsuarioPerfil)obj).isAdministrador() != null)
        criteria.add(Restrictions.eq("administrador", ((UsuarioPerfil)obj).isAdministrador())); 

      criteria.setFirstResult(page);
      criteria.setMaxResults(pagesize);
      return criteria.list();
    }

    @Override
    public Long getNumberRecordsFilter(ObjetoPadrao obj) {
      Session session = (Session) entityManager.getDelegate();
      Criteria criteria = session.createCriteria(UsuarioPerfil.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      int x4=0;
      if((((UsuarioPerfil)obj).getPerfil().getNome() != null)&&(!((UsuarioPerfil)obj).getPerfil().getNome().equals(""))){ 
        criteria.createAlias("perfil", "perfil");
        criteria.add(Restrictions.ilike("perfil.nome",((UsuarioPerfil)obj).getPerfil().getNome()+"%")); 
        x4=1;
      }
      if(((UsuarioPerfil)obj).getPerfil().getId() != 0) {
         if(x4==0) {
            criteria.createAlias("perfil", "perfil");
         }
         criteria.add(Restrictions.eq("perfil.id", ((UsuarioPerfil)obj).getPerfil().getId()));
      }
      int x5=0;
      if((((UsuarioPerfil)obj).getUsuario().getNome() != null)&&(!((UsuarioPerfil)obj).getUsuario().getNome().equals(""))){ 
        criteria.createAlias("usuario", "usuario");
        criteria.add(Restrictions.ilike("usuario.nome",((UsuarioPerfil)obj).getUsuario().getNome()+"%")); 
        x5=1;
      }
      if(((UsuarioPerfil)obj).getUsuario().getId() != 0) {
         if(x5==0) {
            criteria.createAlias("usuario", "usuario");
         }
         criteria.add(Restrictions.eq("usuario.id", ((UsuarioPerfil)obj).getUsuario().getId()));
      }
      if(((UsuarioPerfil)obj).isAtivo() != null)
        criteria.add(Restrictions.eq("ativo", ((UsuarioPerfil)obj).isAtivo()));

      if(((UsuarioPerfil)obj).isAdministrador() != null)
        criteria.add(Restrictions.eq("administrador", ((UsuarioPerfil)obj).isAdministrador()));

      criteria.setProjection(Projections.rowCount());
      return (Long) criteria.list().get(0);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
      Session session = (Session) entityManager.getDelegate();
      Criteria criteria = session.createCriteria(UsuarioPerfil.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      int x0=0;
      if((((UsuarioPerfil)obj).getPerfil().getNome() != null)&&(!((UsuarioPerfil)obj).getPerfil().getNome().equals(""))){ 
        criteria.createAlias("perfil", "perfil");
        criteria.add(Restrictions.ilike("perfil.nome",((UsuarioPerfil)obj).getPerfil().getNome()+"%")); 
        x0=1;
      }
      if(((UsuarioPerfil)obj).getPerfil().getId() != 0) {
         if(x0==0) {
            criteria.createAlias("perfil", "perfil");
         }
         criteria.add(Restrictions.eq("perfil.id", ((UsuarioPerfil)obj).getPerfil().getId()));
      }
      int x1=0;
      if((((UsuarioPerfil)obj).getUsuario().getNome() != null)&&(!((UsuarioPerfil)obj).getUsuario().getNome().equals(""))){ 
        criteria.createAlias("usuario", "usuario");
        criteria.add(Restrictions.ilike("usuario.nome",((UsuarioPerfil)obj).getUsuario().getNome()+"%")); 
        x1=1;
      }
      if(((UsuarioPerfil)obj).getUsuario().getId() != 0) {
         if(x1==0) {
            criteria.createAlias("usuario", "usuario");
         }
         criteria.add(Restrictions.eq("usuario.id", ((UsuarioPerfil)obj).getUsuario().getId()));
      }
      if(((UsuarioPerfil)obj).isAtivo() != null)
        criteria.add(Restrictions.eq("ativo", ((UsuarioPerfil)obj).isAtivo()));

      if(((UsuarioPerfil)obj).isAdministrador() != null)
        criteria.add(Restrictions.eq("administrador", ((UsuarioPerfil)obj).isAdministrador()));

      return criteria.list();
    }



}