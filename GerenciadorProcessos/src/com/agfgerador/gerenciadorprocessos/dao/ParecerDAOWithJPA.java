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
import org.hibernate.sql.JoinType;

import java.sql.Connection;
 import java.sql.PreparedStatement;
 import com.agfgerador.gerenciadorprocessos.domain.Parecer;
import com.agfgerador.gerenciadorprocessos.domain.Processo;


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
       String sql = "UPDATE parecer SET usuario_id = "+((Parecer)obj).getUsuario().getId()+" , processo_id = "+((Parecer)obj).getProcesso().getId()+" , descricao = \""+((Parecer)obj).getDescricao()+"\" WHERE id = "+((Parecer)objofi).getId();
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
       if(((Parecer)obj).getProcesso().getId() != 0) {
          if(x1==0) {
             criteria.createAlias("processo", "processo");
          }
          criteria.add(Restrictions.eq("processo.id", ((Parecer)obj).getProcesso().getId()));
          x1=1;
       }
       
       ////////////////////////////////////////////////////////
       if(((Parecer)obj).getProcesso().getNumprocesso() != null) {
           if(x1==0) {
              criteria.createAlias("processo", "processo");
           }
           criteria.add(Restrictions.eq("processo.numprocesso", ((Parecer)obj).getProcesso().getNumprocesso()));
           x1=1;
        }
       
       if((((Parecer)obj).getProcesso().getDtabertura()!= null)&&(!((Parecer)obj).getProcesso().getDtabertura().equals(""))) {
           if(x1==0) {
              criteria.createAlias("processo", "processo");
           }
           criteria.add(Restrictions.eq("processo.dtabertura", ((Parecer)obj).getProcesso().getDtabertura()));
           x1=1;
        }
       
       if((((Parecer)obj).getProcesso().getPessoa().getNome()!= null)&&(!((Parecer)obj).getProcesso().getPessoa().getNome().equals(""))) {
           if(x1==0) {
              criteria.createAlias("processo", "processo");
           }
           criteria.createCriteria("processo.pessoa","p",JoinType.LEFT_OUTER_JOIN);
           criteria.add(Restrictions.ilike("p.nome", ((Parecer)obj).getProcesso().getPessoa().getNome()+"%"));
           x1=1;
        }
       ///////////////////////////////////////////////////////////////////
       
       if((((Parecer)obj).getDescricao() != null)&&(!((Parecer)obj).getDescricao().replaceAll(" ","").equals(""))) 
           criteria.add(Restrictions.ilike("descricao",((Parecer)obj).getDescricao()+"%")); 
         
       if((((Parecer)obj).getDescricao() != null)&&(((Parecer)obj).getDescricao().equals(" "))) 
       criteria.add(Restrictions.isNull("descricao"));

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
       if(((Parecer)obj).getProcesso().getId() != 0) {
          if(x3==0) {
             criteria.createAlias("processo", "processo");
          }
          criteria.add(Restrictions.eq("processo.id", ((Parecer)obj).getProcesso().getId()));
          x3=1;
       }
       ////////////////////////////////////////////////////////
       if(((Parecer)obj).getProcesso().getNumprocesso() != null) {
           if(x3==0) {
              criteria.createAlias("processo", "processo");
           }
           criteria.add(Restrictions.eq("processo.numprocesso", ((Parecer)obj).getProcesso().getNumprocesso()));
           x3=1;
        }
       
       if((((Parecer)obj).getProcesso().getDtabertura()!= null)&&(!((Parecer)obj).getProcesso().getDtabertura().equals(""))) {
           if(x3==0) {
              criteria.createAlias("processo", "processo");
           }
           criteria.add(Restrictions.eq("processo.dtabertura", ((Parecer)obj).getProcesso().getDtabertura()));
           x3=1;
        }
       
       if((((Parecer)obj).getProcesso().getPessoa().getNome()!= null)&&(!((Parecer)obj).getProcesso().getPessoa().getNome().equals(""))) {
           if(x3==0) {
              criteria.createAlias("processo", "processo");
           }
           criteria.createCriteria("processo.pessoa","p",JoinType.LEFT_OUTER_JOIN);
           criteria.add(Restrictions.ilike("p.nome", ((Parecer)obj).getProcesso().getPessoa().getNome()+"%"));
           x3=1;
        }
       ///////////////////////////////////////////////////////////////////
       
       if((((Parecer)obj).getDescricao() != null)&&(!((Parecer)obj).getDescricao().replaceAll(" ","").equals(""))) 
           criteria.add(Restrictions.ilike("descricao",((Parecer)obj).getDescricao()+"%")); 
         
       if((((Parecer)obj).getDescricao() != null)&&(((Parecer)obj).getDescricao().equals(" "))) 
       criteria.add(Restrictions.isNull("descricao"));

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
       if(((Parecer)obj).getProcesso().getId() != 0) {
          if(x5==0) {
             criteria.createAlias("processo", "processo");
          }
          criteria.add(Restrictions.eq("processo.id", ((Parecer)obj).getProcesso().getId()));
          x5=1;
       }
       ////////////////////////////////////////////////////////
       if(((Parecer)obj).getProcesso().getNumprocesso() != null) {
           if(x5==0) {
              criteria.createAlias("processo", "processo");
           }
           criteria.add(Restrictions.eq("processo.numprocesso", ((Parecer)obj).getProcesso().getNumprocesso()));
           x5=1;
        }
       
       if((((Parecer)obj).getProcesso().getDtabertura()!= null)&&(!((Parecer)obj).getProcesso().getDtabertura().equals(""))) {
           if(x5==0) {
              criteria.createAlias("processo", "processo");
           }
           criteria.add(Restrictions.eq("processo.dtabertura", ((Parecer)obj).getProcesso().getDtabertura()));
           x5=1;
        }
       
       if((((Parecer)obj).getProcesso().getPessoa().getNome()!= null)&&(!((Parecer)obj).getProcesso().getPessoa().getNome().equals(""))) {
           if(x5==0) {
              criteria.createAlias("processo", "processo");
           }
           criteria.createCriteria("processo.pessoa","p",JoinType.LEFT_OUTER_JOIN);
           criteria.add(Restrictions.ilike("p.nome", ((Parecer)obj).getProcesso().getPessoa().getNome()+"%"));
           x5=1;
        }
       ///////////////////////////////////////////////////////////////////
       if((((Parecer)obj).getDescricao() != null)&&(!((Parecer)obj).getDescricao().replaceAll(" ","").equals(""))) 
           criteria.add(Restrictions.ilike("descricao",((Parecer)obj).getDescricao()+"%")); 
         
       if((((Parecer)obj).getDescricao() != null)&&(((Parecer)obj).getDescricao().equals(" "))) 
       criteria.add(Restrictions.isNull("descricao"));

       criteria.setProjection(Projections.rowCount());
       return (Long) criteria.list().get(0);
     }

}