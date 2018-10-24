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

import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.Perfil;
import com.agfgerador.autenticacao.domain.Permissao;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

@Repository
@Transactional("txManagerAutenticacao")
public class PermissaoDAOWithJPA implements PermissaoDAO {

	@PersistenceContext(unitName="autenticacao")
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Permissao> findByNome(String nome) {
		Query query = entityManager.createQuery("Select m from Permissao m where m.nome LIKE :nome");
		query.setParameter("nome", nome + "%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Permissao> findByPerfil(Perfil p) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Permissao.class);
		criteria.add(Restrictions.eq("perfil.id", p.getId()));
	    return criteria.list();	    
	}
	
	@SuppressWarnings("unchecked")
	public List<ObjetoPadrao> filter(ObjetoPadrao obj){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Permissao.class);
		if(obj.getId() != 0)
		criteria.add(Restrictions.eq("id", obj.getId()));
	    return criteria.list();	    
	}
	
	public ObjetoPadrao loadById(Long id) {
		return entityManager.find(Permissao.class, id);
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
		return entityManager.createQuery("Select m from Permissao m").getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Componente> findComponentesByPerfil(Perfil p,TipoSistema sist,Boolean isAtalho) {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(Permissao.class);
			criteria.createAlias("perfil", "p");
			criteria.add(Restrictions.eq("perfil.id", p.getId()));
			criteria.add(Restrictions.eq("p.sistema", sist));
			if(isAtalho!=null)
				criteria.add(Restrictions.eq("atalho", isAtalho));
			criteria.setProjection(Projections.groupProperty("componente"));
			return criteria.list();	    
		}
	
	
	@Override
	public boolean temPemissaoComponente(Perfil p, TipoSistema sist,Componente comp,boolean isAtalho) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Permissao.class);
		criteria.createAlias("perfil", "p");
		criteria.add(Restrictions.eq("perfil.id", p.getId()));
		criteria.add(Restrictions.eq("p.sistema", sist));
		criteria.add(Restrictions.eq("componente.id", comp.getId()));
		criteria.add(Restrictions.eq("ativo", true));
		criteria.add(Restrictions.eq("atalho", isAtalho));
		Permissao pp = (Permissao) criteria.uniqueResult();
		boolean ret = false;
		if(pp!=null)
			ret = true;
		return ret;
			
	}
	
    @SuppressWarnings("unchecked")
    @Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
	       Session session  = (Session) entityManager.getDelegate();
	       Criteria criteria = session.createCriteria(Permissao.class);
	       criteria.setFirstResult(page);
	       criteria.setMaxResults(pagesize);
	       return criteria.list();
	}

	@Override
	public Long getNumberRecords() {
	     Session session = (Session) entityManager.getDelegate();
	     Criteria criteria = session.createCriteria(Permissao.class);
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
      Criteria criteria = session.createCriteria(Permissao.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      int x2=0;
      if((((Permissao)obj).getPerfil().getNome() != null)&&(!((Permissao)obj).getPerfil().getNome().equals(""))){ 
        criteria.createAlias("perfil", "perfil");
        criteria.add(Restrictions.ilike("perfil.nome",((Permissao)obj).getPerfil().getNome()+"%")); 
        x2=1;
      }
      if(((Permissao)obj).getPerfil().getId() != 0) {
         if(x2==0) {
            criteria.createAlias("perfil", "perfil");
         }
         criteria.add(Restrictions.eq("perfil.id", ((Permissao)obj).getPerfil().getId()));
      }
      int x3=0;
      if((((Permissao)obj).getComponente().getDescricao() != null)&&(!((Permissao)obj).getComponente().getDescricao().equals(""))){ 
        criteria.createAlias("componente", "componente");
        criteria.add(Restrictions.ilike("componente.descricao",((Permissao)obj).getComponente().getDescricao()+"%")); 
        x3=1;
      }
      if(((Permissao)obj).getComponente().getId() != 0) {
         if(x3==0) {
            criteria.createAlias("componente", "componente");
         }
         criteria.add(Restrictions.eq("componente.id", ((Permissao)obj).getComponente().getId()));
      }
      if(((Permissao)obj).isAtivo() != null)
        criteria.add(Restrictions.eq("ativo", ((Permissao)obj).isAtivo()));

      if(((Permissao)obj).isAtalho() != null)
        criteria.add(Restrictions.eq("atalho", ((Permissao)obj).isAtalho()));

      if(((Permissao)obj).isSomenteLeitura() != null)
        criteria.add(Restrictions.eq("somenteleitura", ((Permissao)obj).isSomenteLeitura()));

      criteria.setFirstResult(page);
      criteria.setMaxResults(pagesize);
      return criteria.list();
    }
    
    @Override
    public Long getNumberRecordsFilter(ObjetoPadrao obj) {
      Session session = (Session) entityManager.getDelegate();
      Criteria criteria = session.createCriteria(Permissao.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      int x4=0;
      if((((Permissao)obj).getPerfil().getNome() != null)&&(!((Permissao)obj).getPerfil().getNome().equals(""))){ 
        criteria.createAlias("perfil", "perfil");
        criteria.add(Restrictions.ilike("perfil.nome",((Permissao)obj).getPerfil().getNome()+"%")); 
        x4=1;
      }
      if(((Permissao)obj).getPerfil().getId() != 0) {
         if(x4==0) {
            criteria.createAlias("perfil", "perfil");
         }
         criteria.add(Restrictions.eq("perfil.id", ((Permissao)obj).getPerfil().getId()));
      }
      int x5=0;
      if((((Permissao)obj).getComponente().getDescricao() != null)&&(!((Permissao)obj).getComponente().getDescricao().equals(""))){ 
        criteria.createAlias("componente", "componente");
        criteria.add(Restrictions.ilike("componente.descricao",((Permissao)obj).getComponente().getDescricao()+"%")); 
        x5=1;
      }
      if(((Permissao)obj).getComponente().getId() != 0) {
         if(x5==0) {
            criteria.createAlias("componente", "componente");
         }
         criteria.add(Restrictions.eq("componente.id", ((Permissao)obj).getComponente().getId()));
      }
      if(((Permissao)obj).isAtivo() != null)
        criteria.add(Restrictions.eq("ativo", ((Permissao)obj).isAtivo()));

      if(((Permissao)obj).isAtalho() != null)
        criteria.add(Restrictions.eq("atalho", ((Permissao)obj).isAtalho()));

      if(((Permissao)obj).isSomenteLeitura() != null)
        criteria.add(Restrictions.eq("somenteleitura", ((Permissao)obj).isSomenteLeitura()));

      criteria.setProjection(Projections.rowCount());
      return (Long) criteria.list().get(0);
    }


	

	
}