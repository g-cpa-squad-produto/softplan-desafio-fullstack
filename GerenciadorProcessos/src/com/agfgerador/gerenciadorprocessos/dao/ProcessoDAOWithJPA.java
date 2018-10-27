package com.agfgerador.gerenciadorprocessos.dao;

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
import com.agfgerador.gerenciadorprocessos.domain.Processo;

/**ProcessoDAOWithJPA - Classe de manipulação do banco de dados.
 * 
 * @author Arthur Freire
 */
@Repository
@Transactional("txManagerConexao")
public class ProcessoDAOWithJPA implements ProcessoDAO {

	@PersistenceContext(unitName="conexao")
	protected EntityManager entityManager;

	/**Método de busca pelo Id.
	 * 
	 * @author Arthur Freire
	 * @param id Long - Objeto da classe.
	 * 
	 * @return ObjetoPadrao
	 */
	@Override
	public ObjetoPadrao loadById(Long id) {
		return entityManager.find(Processo.class, id);
	}
	/**Método de inserir.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Objeto da classe.
	 */
	@Override
	public void persist(ObjetoPadrao obj) {
		entityManager.persist(obj);
	}
	/**Método de atualizar.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Objeto da classe.
	 */
	@Override
	public void update(ObjetoPadrao obj) {
		entityManager.merge(obj);
	}
	/**Método de deletar.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Objeto da classe.
	 */
	@Override
	public void delete(ObjetoPadrao obj) {
		entityManager.remove(entityManager.merge(obj));
	}
	/**Método listar todos os objetos.
	 * 
	 * @author Arthur Freire
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ObjetoPadrao> findAll() {
		return entityManager.createQuery("Select m from Processo m").getResultList();
	}
	/**Método listar todos os objetos da paginação.
	 * 
	 * @author Arthur Freire
	 * @param pagesize int - quantidade que será listado.
	 * @param page int - De qual posição irá começar a busca.
	 * 
	 * @return List<ObjetoPadrao> - lista de Objetos.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
		Session session  = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Processo.class);
		criteria.setFirstResult(page);
		criteria.setMaxResults(pagesize);
		return criteria.list();
	}
	/**Método conta todos os objetos da classe no banco de dados.
	 * 
	 * @author Arthur Freire
	 * @return Long - total dos objetos.
	 */
	@Override
	public Long getNumberRecords() {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Processo.class);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.list().get(0);
	}
	/**Método listar com filtros.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Objeto da classe.
	 * 
	 * @return List<ObjetoPadrao> - lista de Objetos.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Processo.class);
		if(obj.getId() != 0)
			criteria.add(Restrictions.eq("id", obj.getId()));

		int x0=0;
		if((((Processo)obj).getPessoa().getNome() != null)&&(!((Processo)obj).getPessoa().getNome().equals(""))){ 
			criteria.createAlias("pessoa", "pessoa");
			criteria.add(Restrictions.ilike("pessoa.nome",((Processo)obj).getPessoa().getNome()+"%")); 
			x0=1;
		}
		if(((Processo)obj).getPessoa().getId() != 0) {
			if(x0==0) {
				criteria.createAlias("pessoa", "pessoa");
			}
			criteria.add(Restrictions.eq("pessoa.id", ((Processo)obj).getPessoa().getId()));
		}
		if((((Processo)obj).getDtabertura()!=null)&&(!((Processo)obj).getDtabertura().equals("")))
			criteria.add(Restrictions.eq("dtabertura", ((Processo)obj).getDtabertura()));

		if((((Processo)obj).getNumprocesso() != null)) 
			criteria.add(Restrictions.eq("numprocesso",((Processo)obj).getNumprocesso())); 

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
	/**Método listar com filtros todos os objetos da paginação.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Objeto da classe.
	 * @param pagesize int - quantidade que será listado.
	 * @param page int - De qual posição irá começar a busca.
	 * 
	 * @return List<ObjetoPadrao> - lista de Objetos.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Processo.class);
		if(obj.getId() != 0)
			criteria.add(Restrictions.eq("id", obj.getId()));

		int x1=0;
		if((((Processo)obj).getPessoa().getNome() != null)&&(!((Processo)obj).getPessoa().getNome().equals(""))){ 
			criteria.createAlias("pessoa", "pessoa");
			criteria.add(Restrictions.ilike("pessoa.nome",((Processo)obj).getPessoa().getNome()+"%")); 
			x1=1;
		}
		if(((Processo)obj).getPessoa().getId() != 0) {
			if(x1==0) {
				criteria.createAlias("pessoa", "pessoa");
			}
			criteria.add(Restrictions.eq("pessoa.id", ((Processo)obj).getPessoa().getId()));
		}
		if((((Processo)obj).getDtabertura()!=null)&&(!((Processo)obj).getDtabertura().equals("")))
			criteria.add(Restrictions.eq("dtabertura", ((Processo)obj).getDtabertura()));

		if((((Processo)obj).getNumprocesso() != null)) 
			criteria.add(Restrictions.eq("numprocesso",((Processo)obj).getNumprocesso())); 

		criteria.setFirstResult(page);
		criteria.setMaxResults(pagesize);
		return criteria.list();
	}
    /**Método listar com filtros todos os objetos.
     * 
     * @author Arthur Freire
     * @param obj ObjetoPadrao - Objeto da classe.
     * 
     * @return Long - Quantidade da lista.
     */
	@Override
	public Long getNumberRecordsFilter(ObjetoPadrao obj) {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Processo.class);
		if(obj.getId() != 0)
			criteria.add(Restrictions.eq("id", obj.getId()));

		int x2=0;
		if((((Processo)obj).getPessoa().getNome() != null)&&(!((Processo)obj).getPessoa().getNome().equals(""))){ 
			criteria.createAlias("pessoa", "pessoa");
			criteria.add(Restrictions.ilike("pessoa.nome",((Processo)obj).getPessoa().getNome()+"%")); 
			x2=1;
		}
		if(((Processo)obj).getPessoa().getId() != 0) {
			if(x2==0) {
				criteria.createAlias("pessoa", "pessoa");
			}
			criteria.add(Restrictions.eq("pessoa.id", ((Processo)obj).getPessoa().getId()));
		}
		if((((Processo)obj).getDtabertura()!=null)&&(!((Processo)obj).getDtabertura().equals("")))
			criteria.add(Restrictions.eq("dtabertura", ((Processo)obj).getDtabertura()));

		if((((Processo)obj).getNumprocesso() != null)) 
			criteria.add(Restrictions.eq("numprocesso",((Processo)obj).getNumprocesso())); 

		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.list().get(0);
	}

}