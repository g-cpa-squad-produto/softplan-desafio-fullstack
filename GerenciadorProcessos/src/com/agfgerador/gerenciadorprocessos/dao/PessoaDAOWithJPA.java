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
 import com.agfgerador.gerenciadorprocessos.domain.Pessoa;


   @Repository
   @Transactional("txManagerConexao")
   public class PessoaDAOWithJPA implements PessoaDAO {

     @PersistenceContext(unitName="conexao")
     protected EntityManager entityManager;

     @Override
     public ObjetoPadrao loadById(Long id) {
       return entityManager.find(Pessoa.class, id);
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
     return entityManager.createQuery("Select m from Pessoa m").getResultList();
     }

     @SuppressWarnings("unchecked")
     @Override
     public List<ObjetoPadrao> findAll(int pagesize, int page) {
       Session session  = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Pessoa.class);
       criteria.setFirstResult(page);
       criteria.setMaxResults(pagesize);
       return criteria.list();
     }

     @Override
     public Long getNumberRecords() {
     Session session = (Session) entityManager.getDelegate();
     Criteria criteria = session.createCriteria(Pessoa.class);
     criteria.setProjection(Projections.rowCount());
     return (Long) criteria.list().get(0);
     }

     @SuppressWarnings("unchecked")
     @Override
     public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
       Session session = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Pessoa.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       int x0=0;
       if((((Pessoa)obj).getTipopessoa().getDescricao() != null)&&(!((Pessoa)obj).getTipopessoa().getDescricao().equals(""))){ 
         criteria.createAlias("tipopessoa", "tipopessoa");
         criteria.add(Restrictions.ilike("tipopessoa.descricao",((Pessoa)obj).getTipopessoa().getDescricao()+"%")); 
         x0=1;
       }
       if(((Pessoa)obj).getTipopessoa().getId() != 0) {
          if(x0==0) {
             criteria.createAlias("tipopessoa", "tipopessoa");
          }
          criteria.add(Restrictions.eq("tipopessoa.id", ((Pessoa)obj).getTipopessoa().getId()));
       }
       if((((Pessoa)obj).getNome() != null)&&(!((Pessoa)obj).getNome().equals(""))) 
           criteria.add(Restrictions.ilike("nome",((Pessoa)obj).getNome()+"%")); 

         if((((Pessoa)obj).getData()!=null)&&(!((Pessoa)obj).getData().equals("")))
           criteria.add(Restrictions.eq("data", ((Pessoa)obj).getData()));

         /*if((((Pessoa)obj).getCpf() != null)&&(!((Pessoa)obj).getCpf().equals(""))) 
           criteria.add(Restrictions.ilike("cpf",((Pessoa)obj).getCpf()+"%"));*/ 
         if(((((Pessoa)obj).getCpf() != null)&&(!((Pessoa)obj).getCpf().equals("")))||((((Pessoa)obj).getCep() != null)&&(!((Pessoa)obj).getCep().equals("")))) {
      	   criteria.add(Restrictions.or(Restrictions.ilike("cpf",((Pessoa)obj).getCpf()+"%"), Restrictions.ilike("cnpj",((Pessoa)obj).getCnpj()+"%")));
         }

         /*if((((Pessoa)obj).getCnpj() != null)&&(!((Pessoa)obj).getCnpj().equals(""))) 
           criteria.add(Restrictions.ilike("cnpj",((Pessoa)obj).getCnpj()+"%"));*/ 

         if((((Pessoa)obj).getCep() != null)&&(!((Pessoa)obj).getCep().equals(""))) 
           criteria.add(Restrictions.ilike("cep",((Pessoa)obj).getCep()+"%")); 

         if((((Pessoa)obj).getUf() != null)&&(!((Pessoa)obj).getUf().equals(""))) 
           criteria.add(Restrictions.ilike("uf",((Pessoa)obj).getUf()+"%")); 

         if((((Pessoa)obj).getCidade() != null)&&(!((Pessoa)obj).getCidade().equals(""))) 
           criteria.add(Restrictions.ilike("cidade",((Pessoa)obj).getCidade()+"%")); 

         if((((Pessoa)obj).getLogradouro() != null)&&(!((Pessoa)obj).getLogradouro().equals(""))) 
           criteria.add(Restrictions.ilike("logradouro",((Pessoa)obj).getLogradouro()+"%")); 

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
       Criteria criteria = session.createCriteria(Pessoa.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       int x1=0;
       if((((Pessoa)obj).getTipopessoa().getDescricao() != null)&&(!((Pessoa)obj).getTipopessoa().getDescricao().equals(""))){ 
         criteria.createAlias("tipopessoa", "tipopessoa");
         criteria.add(Restrictions.ilike("tipopessoa.descricao",((Pessoa)obj).getTipopessoa().getDescricao()+"%")); 
         x1=1;
       }
       if(((Pessoa)obj).getTipopessoa().getId() != 0) {
          if(x1==0) {
             criteria.createAlias("tipopessoa", "tipopessoa");
          }
          criteria.add(Restrictions.eq("tipopessoa.id", ((Pessoa)obj).getTipopessoa().getId()));
       }
       if((((Pessoa)obj).getNome() != null)&&(!((Pessoa)obj).getNome().equals(""))) 
           criteria.add(Restrictions.ilike("nome",((Pessoa)obj).getNome()+"%")); 

         if((((Pessoa)obj).getData()!=null)&&(!((Pessoa)obj).getData().equals("")))
           criteria.add(Restrictions.eq("data", ((Pessoa)obj).getData()));

         /*if((((Pessoa)obj).getCpf() != null)&&(!((Pessoa)obj).getCpf().equals(""))) 
           criteria.add(Restrictions.ilike("cpf",((Pessoa)obj).getCpf()+"%"));*/ 
         if(((((Pessoa)obj).getCpf() != null)&&(!((Pessoa)obj).getCpf().equals("")))||((((Pessoa)obj).getCep() != null)&&(!((Pessoa)obj).getCep().equals("")))) {
      	   criteria.add(Restrictions.or(Restrictions.ilike("cpf",((Pessoa)obj).getCpf()+"%"), Restrictions.ilike("cnpj",((Pessoa)obj).getCnpj()+"%")));
         }

         /*if((((Pessoa)obj).getCnpj() != null)&&(!((Pessoa)obj).getCnpj().equals(""))) 
           criteria.add(Restrictions.ilike("cnpj",((Pessoa)obj).getCnpj()+"%"));*/ 

         if((((Pessoa)obj).getCep() != null)&&(!((Pessoa)obj).getCep().equals(""))) 
           criteria.add(Restrictions.ilike("cep",((Pessoa)obj).getCep()+"%")); 

         if((((Pessoa)obj).getUf() != null)&&(!((Pessoa)obj).getUf().equals(""))) 
           criteria.add(Restrictions.ilike("uf",((Pessoa)obj).getUf()+"%")); 

         if((((Pessoa)obj).getCidade() != null)&&(!((Pessoa)obj).getCidade().equals(""))) 
           criteria.add(Restrictions.ilike("cidade",((Pessoa)obj).getCidade()+"%")); 

         if((((Pessoa)obj).getLogradouro() != null)&&(!((Pessoa)obj).getLogradouro().equals(""))) 
           criteria.add(Restrictions.ilike("logradouro",((Pessoa)obj).getLogradouro()+"%")); 

       criteria.setFirstResult(page);
       criteria.setMaxResults(pagesize);
       return criteria.list();
     }

     @Override
     public Long getNumberRecordsFilter(ObjetoPadrao obj) {
       Session session = (Session) entityManager.getDelegate();
       Criteria criteria = session.createCriteria(Pessoa.class);
       if(obj.getId() != 0)
         criteria.add(Restrictions.eq("id", obj.getId()));

       int x2=0;
       if((((Pessoa)obj).getTipopessoa().getDescricao() != null)&&(!((Pessoa)obj).getTipopessoa().getDescricao().equals(""))){ 
         criteria.createAlias("tipopessoa", "tipopessoa");
         criteria.add(Restrictions.ilike("tipopessoa.descricao",((Pessoa)obj).getTipopessoa().getDescricao()+"%")); 
         x2=1;
       }
       if(((Pessoa)obj).getTipopessoa().getId() != 0) {
          if(x2==0) {
             criteria.createAlias("tipopessoa", "tipopessoa");
          }
          criteria.add(Restrictions.eq("tipopessoa.id", ((Pessoa)obj).getTipopessoa().getId()));
       }
       if((((Pessoa)obj).getNome() != null)&&(!((Pessoa)obj).getNome().equals(""))) 
         criteria.add(Restrictions.ilike("nome",((Pessoa)obj).getNome()+"%")); 

       if((((Pessoa)obj).getData()!=null)&&(!((Pessoa)obj).getData().equals("")))
         criteria.add(Restrictions.eq("data", ((Pessoa)obj).getData()));

       /*if((((Pessoa)obj).getCpf() != null)&&(!((Pessoa)obj).getCpf().equals(""))) 
         criteria.add(Restrictions.ilike("cpf",((Pessoa)obj).getCpf()+"%"));*/ 
       if(((((Pessoa)obj).getCpf() != null)&&(!((Pessoa)obj).getCpf().equals("")))||((((Pessoa)obj).getCep() != null)&&(!((Pessoa)obj).getCep().equals("")))) {
    	   criteria.add(Restrictions.or(Restrictions.ilike("cpf",((Pessoa)obj).getCpf()+"%"), Restrictions.ilike("cnpj",((Pessoa)obj).getCnpj()+"%")));
       }

       /*if((((Pessoa)obj).getCnpj() != null)&&(!((Pessoa)obj).getCnpj().equals(""))) 
         criteria.add(Restrictions.ilike("cnpj",((Pessoa)obj).getCnpj()+"%"));*/ 

       if((((Pessoa)obj).getCep() != null)&&(!((Pessoa)obj).getCep().equals(""))) 
         criteria.add(Restrictions.ilike("cep",((Pessoa)obj).getCep()+"%")); 

       if((((Pessoa)obj).getUf() != null)&&(!((Pessoa)obj).getUf().equals(""))) 
         criteria.add(Restrictions.ilike("uf",((Pessoa)obj).getUf()+"%")); 

       if((((Pessoa)obj).getCidade() != null)&&(!((Pessoa)obj).getCidade().equals(""))) 
         criteria.add(Restrictions.ilike("cidade",((Pessoa)obj).getCidade()+"%")); 

       if((((Pessoa)obj).getLogradouro() != null)&&(!((Pessoa)obj).getLogradouro().equals(""))) 
         criteria.add(Restrictions.ilike("logradouro",((Pessoa)obj).getLogradouro()+"%")); 

       criteria.setProjection(Projections.rowCount());
       return (Long) criteria.list().get(0);
     }

}