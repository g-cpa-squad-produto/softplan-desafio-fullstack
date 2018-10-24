package com.agfgerador.autenticacao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.Permissao;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;

@Repository
@Transactional("txManagerAutenticacao")
public class ComponenteDAOWithJPA implements ComponenteDAO {

	@PersistenceContext(unitName="autenticacao")
	protected EntityManager entityManager;
		
	public ObjetoPadrao loadById(Long id) {
		return entityManager.find(Componente.class, id);
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
	public List<Componente> findAllBySistema(TipoSistema s)
	{
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Componente.class);
		criteria.add(Restrictions.eq("sistema", s));
		return criteria.list();
		
	}
	

	public Componente findByNomeAndSistema(String nome,TipoSistema s)
	{	Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Componente.class);
		criteria.add(Restrictions.eq("sistema", s));
		criteria.add(Restrictions.sqlRestriction("UPPER(this_.nome) = '"+nome.toUpperCase()+"'"));
		return (Componente) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Componente> findMenuBySistema(TipoSistema s)
	{
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Componente.class);
		criteria.add(Restrictions.eq("sistema", s));
		criteria.add(Restrictions.isNull("menu"));
		//////////novo ini
		//criteria.createAlias("componente", "componente");
		//criteria.add(Restrictions.eq("componente", null));
        //criteria.add(Restrictions.ilike("usuario.nome",((Conexao)obj).getUsuario().getNome()+"%"));
		//criteria.add(Restrictions.isNull("url"));
		//criteria.add(Restrictions.isNull("componente"));
		/////////novo fim
		return criteria.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findMenus(TipoSistema s)
	{
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Componente.class);
		criteria.add(Restrictions.eq("sistema", s));
		criteria.add(Restrictions.isNotNull("menu"));
		criteria.setProjection(Projections.groupProperty("menu"));
	
		return criteria.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Componente> findMenuItemByMenu(String menu,TipoSistema s)
	{
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Componente.class);
		criteria.add(Restrictions.eq("sistema", s));
		criteria.add(Restrictions.isNotNull("menu"));
		criteria.add(Restrictions.eq("menu", menu));
		return criteria.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Componente> findMenuItemByMenuBuscaIndex(Componente componente,UsuarioPerfil usuperfil, String campoBusca)	{		
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Componente.class);
		criteria.add(Restrictions.eq("sistema", usuperfil.getPerfil().getSistema()));
		criteria.add(Restrictions.isNotNull("menu"));
		criteria.add(Restrictions.eq("menu", componente.getNome()));
		criteria.add(Restrictions.or(Restrictions.ilike("descricao","%"+campoBusca+"%" ),Restrictions.ilike("informacao","%"+campoBusca+"%")));
		return criteria.list();
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

	@SuppressWarnings("unchecked")
	public List<ObjetoPadrao> findAll(int pagesize, int page){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Componente.class);
		criteria.setFirstResult(page);
		criteria.setMaxResults(pagesize);
		return criteria.list();
	}
	
	public Long getNumberRecords(){
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Componente.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Componente.class);
		if(obj.getId() != 0)
			criteria.add(Restrictions.eq("id", obj.getId()));
	    criteria.add(Restrictions.ilike("nome",((Componente)obj).getNome()+"%"));
	    criteria.add(Restrictions.ilike("descricao",((Componente)obj).getDescricao()+"%"));
	    if((((Componente)obj).getMenu()!=null)&&(!((Componente)obj).getMenu().equals("")))
	    	criteria.add(Restrictions.ilike("menu",((Componente)obj).getMenu()+"%"));
	    if(((Componente)obj).getSistema()!=null)
	    	criteria.add(Restrictions.eq("sistema", ((Componente)obj).getSistema()));
	    
	    return criteria.list();	    
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ObjetoPadrao filtercombobox(ObjetoPadrao obj) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Componente.class);
		if(obj.getId() != 0)
			criteria.add(Restrictions.eq("id", obj.getId()));
	   if((((Componente)obj).getNome() != null)&&(!((Componente)obj).getNome().equals(""))) 
	    criteria.add(Restrictions.ilike("nome",((Componente)obj).getNome()+"%"));
	   if((((Componente)obj).getDescricao() != null)&&(!((Componente)obj).getDescricao().equals(""))) 
	    criteria.add(Restrictions.ilike("descricao",((Componente)obj).getDescricao()+"%"));
	      if((((Componente)obj).getMenu() == null)||(((Componente)obj).getMenu().equals(""))) { 
	          criteria.add(Restrictions.isNull("menu"));
	    	}else {
	    	  criteria.add(Restrictions.ilike("menu",((Componente)obj).getMenu()));
	    	}
	    if(((Componente)obj).getSistema()!=null)
	    	criteria.add(Restrictions.eq("sistema", ((Componente)obj).getSistema()));
	    
	    return (ObjetoPadrao)criteria.uniqueResult();	    
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
      Criteria criteria = session.createCriteria(Componente.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      if((((Componente)obj).getDescricao() != null)&&(!((Componente)obj).getDescricao().equals(""))) 
        criteria.add(Restrictions.ilike("descricao",((Componente)obj).getDescricao()+"%")); 

      if((((Componente)obj).getInformacao() != null)&&(!((Componente)obj).getInformacao().equals(""))) 
        criteria.add(Restrictions.ilike("informacao",((Componente)obj).getInformacao()+"%")); 

      int x3=0;
      if((((Componente)obj).getTipomenu().getNome() != null)&&(!((Componente)obj).getTipomenu().getNome().equals(""))){ 
        criteria.createAlias("tipomenu", "tipomenu");
        criteria.add(Restrictions.ilike("tipomenu.nome",((Componente)obj).getTipomenu().getNome()+"%")); 
        x3=1;
      }
      if(((Componente)obj).getTipomenu().getId() != 0) {
         if(x3==0) {
            criteria.createAlias("tipomenu", "tipomenu");
         }
         criteria.add(Restrictions.eq("tipomenu.id", ((Componente)obj).getTipomenu().getId()));
      }
      criteria.setFirstResult(page);
      criteria.setMaxResults(pagesize);
      return criteria.list();
    }
    
    
    @Override
    public Long getNumberRecordsFilter(ObjetoPadrao obj) {
      Session session = (Session) entityManager.getDelegate();
      Criteria criteria = session.createCriteria(Componente.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      if((((Componente)obj).getDescricao() != null)&&(!((Componente)obj).getDescricao().equals(""))) 
        criteria.add(Restrictions.ilike("descricao",((Componente)obj).getDescricao()+"%")); 

      if((((Componente)obj).getInformacao() != null)&&(!((Componente)obj).getInformacao().equals(""))) 
        criteria.add(Restrictions.ilike("informacao",((Componente)obj).getInformacao()+"%")); 

      int x5=0;
      if((((Componente)obj).getTipomenu().getNome() != null)&&(!((Componente)obj).getTipomenu().getNome().equals(""))){ 
        criteria.createAlias("tipomenu", "tipomenu");
        criteria.add(Restrictions.ilike("tipomenu.nome",((Componente)obj).getTipomenu().getNome()+"%")); 
        x5=1;
      }
      if(((Componente)obj).getTipomenu().getId() != 0) {
         if(x5==0) {
            criteria.createAlias("tipomenu", "tipomenu");
         }
         criteria.add(Restrictions.eq("tipomenu.id", ((Componente)obj).getTipomenu().getId()));
      }
      criteria.setProjection(Projections.rowCount());
      return (Long) criteria.list().get(0);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<ObjetoPadrao> filtercombobox(ObjetoPadrao obj, int pagesize, int page) {
      Session session = (Session) entityManager.getDelegate();
      Criteria criteria = session.createCriteria(Componente.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      if((((Componente)obj).getDescricao() != null)&&(!((Componente)obj).getDescricao().equals(""))) 
        criteria.add(Restrictions.ilike("descricao",((Componente)obj).getDescricao()+"%")); 

      if((((Componente)obj).getInformacao() != null)&&(!((Componente)obj).getInformacao().equals(""))) 
        criteria.add(Restrictions.ilike("informacao",((Componente)obj).getInformacao()+"%")); 
      
      if((((Componente)obj).getMenu() == null)||(((Componente)obj).getMenu().equals(""))) { 
          criteria.add(Restrictions.isNull("menu"));
    	}else {
    	  criteria.add(Restrictions.ilike("menu",((Componente)obj).getMenu()));
    	}
      
      if((((Componente)obj).getNome() != null)&&(!((Componente)obj).getNome().equals(""))) 
          criteria.add(Restrictions.ilike("nome",((Componente)obj).getNome()));

      int x3=0;
      if((((Componente)obj).getTipomenu().getNome() != null)&&(!((Componente)obj).getTipomenu().getNome().equals(""))){ 
        criteria.createAlias("tipomenu", "tipomenu");
        criteria.add(Restrictions.ilike("tipomenu.nome",((Componente)obj).getTipomenu().getNome()+"%")); 
        x3=1;
      }
      if(((Componente)obj).getTipomenu().getId() != 0) {
         if(x3==0) {
            criteria.createAlias("tipomenu", "tipomenu");
         }
         criteria.add(Restrictions.eq("tipomenu.id", ((Componente)obj).getTipomenu().getId()));
      }
      criteria.setFirstResult(page);
      criteria.setMaxResults(pagesize);
      return criteria.list();
    }
    
    
    @Override
    public Long getNumberRecordsFiltercombobox(ObjetoPadrao obj) {
      Session session = (Session) entityManager.getDelegate();
      Criteria criteria = session.createCriteria(Componente.class);
      if(obj.getId() != 0)
        criteria.add(Restrictions.eq("id", obj.getId()));

      if((((Componente)obj).getDescricao() != null)&&(!((Componente)obj).getDescricao().equals(""))) 
        criteria.add(Restrictions.ilike("descricao",((Componente)obj).getDescricao()+"%")); 

      if((((Componente)obj).getInformacao() != null)&&(!((Componente)obj).getInformacao().equals(""))) 
        criteria.add(Restrictions.ilike("informacao",((Componente)obj).getInformacao()+"%")); 
      
      if((((Componente)obj).getNome() != null)&&(!((Componente)obj).getNome().equals(""))) 
          criteria.add(Restrictions.ilike("nome",((Componente)obj).getNome()));
      
      if((((Componente)obj).getMenu() == null)||(((Componente)obj).getMenu().equals(""))) { 
          criteria.add(Restrictions.isNull("menu"));
    	}else {
    	  criteria.add(Restrictions.ilike("menu",((Componente)obj).getMenu()));
    	}

      int x5=0;
      if((((Componente)obj).getTipomenu().getNome() != null)&&(!((Componente)obj).getTipomenu().getNome().equals(""))){ 
        criteria.createAlias("tipomenu", "tipomenu");
        criteria.add(Restrictions.ilike("tipomenu.nome",((Componente)obj).getTipomenu().getNome()+"%")); 
        x5=1;
      }
      if(((Componente)obj).getTipomenu().getId() != 0) {
         if(x5==0) {
            criteria.createAlias("tipomenu", "tipomenu");
         }
         criteria.add(Restrictions.eq("tipomenu.id", ((Componente)obj).getTipomenu().getId()));
      }
      criteria.setProjection(Projections.rowCount());
      return (Long) criteria.list().get(0);
    }


}
