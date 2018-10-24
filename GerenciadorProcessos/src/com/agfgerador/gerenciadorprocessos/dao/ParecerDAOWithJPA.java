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
 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
 import org.hibernate.internal.SessionImpl;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import com.agfgerador.gerenciadorprocessos.domain.Parecer;


   @Repository
   @Transactional("txManagerConexao")
   public class ParecerDAOWithJPA implements ParecerDAO {

     @PersistenceContext(unitName="conexao")
     protected EntityManager entityManager;

     @Override
     public ObjetoPadraoSemId loadById(Long id) {
       Session session = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Parecer.class);
       if(id != 0){
          criteria.add(Restrictions.eq("id", id));
       }
       return (ObjetoPadraoSemId) criteria.uniqueResult();
     }

     @Override
     public void persist(ObjetoPadraoSemId obj) {
       entityManager.persist(obj);
     }
     @Override

     public void update(ObjetoPadraoSemId obj) {
       ObjetoPadraoSemId objofi = loadById(obj.getId());
       String sql = "UPDATE parecer SET usuario_id = "+((Parecer)obj).getUsuario().getId()+" , processo_id = "+((Parecer)obj).getProcesso().getId()+" , descricao = \""+((Parecer)obj).getDescricao()+"\" , dtparecer = '"+((Parecer)obj).getDtparecer()+"' WHERE id = "+((Parecer)objofi).getId();
        SessionImpl session = (SessionImpl) entityManager.getDelegate();
       Connection con = session.connection();
       try {
          PreparedStatement stmt= con.prepareStatement(sql);
          int i=stmt.executeUpdate();
       } catch (Exception e) {
          e.printStackTrace();
          throw null;
       }
     }
     @Override
     public void delete(ObjetoPadraoSemId obj) {
       entityManager.remove(entityManager.merge(obj));
     }

     @SuppressWarnings("unchecked")
     @Override
     public List<ObjetoPadraoSemId> findAll() {
     return entityManager.createQuery("Select m from Parecer m").getResultList();
     }

     @SuppressWarnings("unchecked")
     @Override
     public List<ObjetoPadraoSemId> findAll(int pagesize, int page) {
       Session session  = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Parecer.class);
       criteria.setFirstResult(page);
       criteria.setMaxResults(pagesize);
       return criteria.list();
     }

     @Override
     public Long getNumberRecords() {
     Session session = (Session) entityManager.getDelegate();
     Criteria criteria = session.createCriteria(Parecer.class);
     criteria.setProjection(Projections.rowCount());
     return (Long) criteria.list().get(0);
     }

     @SuppressWarnings("unchecked")
     @Override
     public List<ObjetoPadraoSemId> filter(ObjetoPadraoSemId obj) {
       Session session = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Parecer.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       int x0=0;
       if((((Parecer)obj).getUsuario().getNome() != null)&&(!((Parecer)obj).getUsuario().getNome().equals(""))){ 
         criteria.createAlias("usuario", "usuario");
         criteria.add(Restrictions.ilike("usuario.nome",((Parecer)obj).getUsuario().getNome()+"%")); 
         x0=1;
       }
       if(((Parecer)obj).getUsuario().getId() != 0) {
          if(x0==0) {
             criteria.createAlias("usuario", "usuario");
          }
          criteria.add(Restrictions.eq("usuario.id", ((Parecer)obj).getUsuario().getId()));
       }
       int x1=0;
       if((((Parecer)obj).getProcesso().getObs() != null)&&(!((Parecer)obj).getProcesso().getObs().equals(""))){ 
         criteria.createAlias("processo", "processo");
         criteria.add(Restrictions.ilike("processo.obs",((Parecer)obj).getProcesso().getObs()+"%")); 
         x1=1;
       }
       if(((Parecer)obj).getProcesso().getId() != 0) {
          if(x1==0) {
             criteria.createAlias("processo", "processo");
          }
          criteria.add(Restrictions.eq("processo.id", ((Parecer)obj).getProcesso().getId()));
       }
       if((((Parecer)obj).getDescricao() != null)&&(!((Parecer)obj).getDescricao().equals(""))) 
         criteria.add(Restrictions.ilike("descricao",((Parecer)obj).getDescricao()+"%")); 

       if((((Parecer)obj).getDtparecer()!=null)&&(!((Parecer)obj).getDtparecer().equals("")))
         criteria.add(Restrictions.eq("dtparecer", ((Parecer)obj).getDtparecer()));

       return criteria.list();
     }

     @Override
     public List<ObjetoPadraoSemId> findAll(String value, int pagesize, int page) {
       return null;
     }

     @Override
     public Long getNumberRecords(String value) {
       return null;
     }

     @SuppressWarnings("unchecked")
     @Override
     public List<ObjetoPadraoSemId> filter(ObjetoPadraoSemId obj, int pagesize, int page) {
       Session session = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Parecer.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       int x2=0;
       if((((Parecer)obj).getUsuario().getNome() != null)&&(!((Parecer)obj).getUsuario().getNome().equals(""))){ 
         criteria.createAlias("usuario", "usuario");
         criteria.add(Restrictions.ilike("usuario.nome",((Parecer)obj).getUsuario().getNome()+"%")); 
         x2=1;
       }
       if(((Parecer)obj).getUsuario().getId() != 0) {
          if(x2==0) {
             criteria.createAlias("usuario", "usuario");
          }
          criteria.add(Restrictions.eq("usuario.id", ((Parecer)obj).getUsuario().getId()));
       }
       int x3=0;
       if((((Parecer)obj).getProcesso().getObs() != null)&&(!((Parecer)obj).getProcesso().getObs().equals(""))){ 
         criteria.createAlias("processo", "processo");
         criteria.add(Restrictions.ilike("processo.obs",((Parecer)obj).getProcesso().getObs()+"%")); 
         x3=1;
       }
       if(((Parecer)obj).getProcesso().getId() != 0) {
          if(x3==0) {
             criteria.createAlias("processo", "processo");
          }
          criteria.add(Restrictions.eq("processo.id", ((Parecer)obj).getProcesso().getId()));
       }
       if((((Parecer)obj).getDescricao() != null)&&(!((Parecer)obj).getDescricao().equals(""))) 
         criteria.add(Restrictions.ilike("descricao",((Parecer)obj).getDescricao()+"%")); 

       if((((Parecer)obj).getDtparecer()!=null)&&(!((Parecer)obj).getDtparecer().equals("")))
         criteria.add(Restrictions.eq("dtparecer", ((Parecer)obj).getDtparecer()));

       criteria.setFirstResult(page);
       criteria.setMaxResults(pagesize);
       return criteria.list();
     }

     @Override
     public Long getNumberRecordsFilter(ObjetoPadraoSemId obj) {
       Session session = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Parecer.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       int x4=0;
       if((((Parecer)obj).getUsuario().getNome() != null)&&(!((Parecer)obj).getUsuario().getNome().equals(""))){ 
         criteria.createAlias("usuario", "usuario");
         criteria.add(Restrictions.ilike("usuario.nome",((Parecer)obj).getUsuario().getNome()+"%")); 
         x4=1;
       }
       if(((Parecer)obj).getUsuario().getId() != 0) {
          if(x4==0) {
             criteria.createAlias("usuario", "usuario");
          }
          criteria.add(Restrictions.eq("usuario.id", ((Parecer)obj).getUsuario().getId()));
       }
       int x5=0;
       if((((Parecer)obj).getProcesso().getObs() != null)&&(!((Parecer)obj).getProcesso().getObs().equals(""))){ 
         criteria.createAlias("processo", "processo");
         criteria.add(Restrictions.ilike("processo.obs",((Parecer)obj).getProcesso().getObs()+"%")); 
         x5=1;
       }
       if(((Parecer)obj).getProcesso().getId() != 0) {
          if(x5==0) {
             criteria.createAlias("processo", "processo");
          }
          criteria.add(Restrictions.eq("processo.id", ((Parecer)obj).getProcesso().getId()));
       }
       if((((Parecer)obj).getDescricao() != null)&&(!((Parecer)obj).getDescricao().equals(""))) 
         criteria.add(Restrictions.ilike("descricao",((Parecer)obj).getDescricao()+"%")); 

       if((((Parecer)obj).getDtparecer()!=null)&&(!((Parecer)obj).getDtparecer().equals("")))
         criteria.add(Restrictions.eq("dtparecer", ((Parecer)obj).getDtparecer()));

       criteria.setProjection(Projections.rowCount());
       return (Long) criteria.list().get(0);
     }

}