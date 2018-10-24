 package com.agfgerador.gerenciadorprocessos.dao;

 import java.util.List;
 import javax.persistence.EntityManager;
 import javax.persistence.PersistenceContext;
 import org.hibernate.Criteria;
 import org.hibernate.Session;
 import org.hibernate.criterion.Restrictions;
 import org.hibernate.criterion.Projections;
 import javax.persistence.TypedQuery;
 import org.springframework.stereotype.Repository;
 import org.springframework.transaction.annotation.Transactional;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
 import com.agfgerador.gerenciadorprocessos.domain.Tipopessoa;


   @Repository
   @Transactional("txManagerConexao")
   public class TipopessoaDAOWithJPA implements TipopessoaDAO {

     @PersistenceContext(unitName="conexao")
     protected EntityManager entityManager;

     @Override
     public ObjetoPadrao loadById(Long id) {
       return entityManager.find(Tipopessoa.class, id);
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
     return entityManager.createQuery("Select m from Tipopessoa m").getResultList();
     }

     @SuppressWarnings("unchecked")
     @Override
     public List<ObjetoPadrao> findAll(int pagesize, int page) {
       Session session  = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Tipopessoa.class);
       criteria.setFirstResult(page);
       criteria.setMaxResults(pagesize);
       return criteria.list();
     }

     @Override
     public Long getNumberRecords() {
     Session session = (Session) entityManager.getDelegate();
     Criteria criteria = session.createCriteria(Tipopessoa.class);
     criteria.setProjection(Projections.rowCount());
     return (Long) criteria.list().get(0);
     }

     @SuppressWarnings("unchecked")
     @Override
     public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
       Session session = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Tipopessoa.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       if((((Tipopessoa)obj).getDescricao() != null)&&(!((Tipopessoa)obj).getDescricao().equals(""))) 
         criteria.add(Restrictions.ilike("descricao",((Tipopessoa)obj).getDescricao()+"%")); 

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
       Criteria criteria = session.createCriteria(Tipopessoa.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       if((((Tipopessoa)obj).getDescricao() != null)&&(!((Tipopessoa)obj).getDescricao().equals(""))) 
         criteria.add(Restrictions.ilike("descricao",((Tipopessoa)obj).getDescricao()+"%")); 

       criteria.setFirstResult(page);
       criteria.setMaxResults(pagesize);
       return criteria.list();
     }

     @Override
     public Long getNumberRecordsFilter(ObjetoPadrao obj) {
       Session session = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Tipopessoa.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       if((((Tipopessoa)obj).getDescricao() != null)&&(!((Tipopessoa)obj).getDescricao().equals(""))) 
         criteria.add(Restrictions.ilike("descricao",((Tipopessoa)obj).getDescricao()+"%")); 

       criteria.setProjection(Projections.rowCount());
       return (Long) criteria.list().get(0);
     }

}