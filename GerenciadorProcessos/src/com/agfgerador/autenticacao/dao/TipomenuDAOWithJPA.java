 package com.agfgerador.autenticacao.dao;

 import java.util.List;
 import javax.persistence.EntityManager;
 import javax.persistence.PersistenceContext;
 import org.hibernate.Criteria;
 import org.hibernate.Session;
 import org.hibernate.criterion.Restrictions;
 import org.hibernate.criterion.Projections;
 import org.springframework.stereotype.Repository;
 import org.springframework.transaction.annotation.Transactional;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
 import com.agfgerador.autenticacao.domain.Tipomenu;


   @Repository
   @Transactional("txManagerConexao")
   public class TipomenuDAOWithJPA implements TipomenuDAO {

     @PersistenceContext(unitName="conexao")
     protected EntityManager entityManager;

     @Override
     public ObjetoPadrao loadById(Long id) {
       return entityManager.find(Tipomenu.class, id);
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

     @SuppressWarnings("unchecked")
     @Override
     public List<ObjetoPadrao> findAll() {
     return entityManager.createQuery("Select m from Tipomenu m").getResultList();
     }

     @SuppressWarnings("unchecked")
     @Override
     public List<ObjetoPadrao> findAll(int pagesize, int page) {
       Session session  = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Tipomenu.class);
       criteria.setFirstResult(page);
       criteria.setMaxResults(pagesize);
       return criteria.list();
     }

     @Override
     public Long getNumberRecords() {
     Session session = (Session) entityManager.getDelegate();
     Criteria criteria = session.createCriteria(Tipomenu.class);
     criteria.setProjection(Projections.rowCount());
     return (Long) criteria.list().get(0);
     }

     @SuppressWarnings("unchecked")
     @Override
     public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
       Session session = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Tipomenu.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       if((((Tipomenu)obj).getNome() != null)&&(!((Tipomenu)obj).getNome().equals(""))) 
         criteria.add(Restrictions.ilike("nome",((Tipomenu)obj).getNome()+"%")); 

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
     @Override
     public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page) {
       Session session = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Tipomenu.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       if((((Tipomenu)obj).getNome() != null)&&(!((Tipomenu)obj).getNome().equals(""))) 
         criteria.add(Restrictions.ilike("nome",((Tipomenu)obj).getNome()+"%")); 

       criteria.setFirstResult(page);
       criteria.setMaxResults(pagesize);
       return criteria.list();
     }

     @Override
     public Long getNumberRecordsFilter(ObjetoPadrao obj) {
       Session session = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Tipomenu.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       if((((Tipomenu)obj).getNome() != null)&&(!((Tipomenu)obj).getNome().equals(""))) 
         criteria.add(Restrictions.ilike("nome",((Tipomenu)obj).getNome()+"%")); 

       criteria.setProjection(Projections.rowCount());
       return (Long) criteria.list().get(0);
     }

}