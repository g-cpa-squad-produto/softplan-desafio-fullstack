package com.agfgerador.gerenciadorprocessos.service;

/**ParecerServiceImpl - Classe de comnicação com os DAO e Service.
 * 
 * @author Arthur Freire
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.agfgerador.autenticacao.domain.Metodo;
import com.agfgerador.autenticacao.domain.Modulo;
import com.agfgerador.autenticacao.service.LogService;
import com.agfgerador.gerenciadorprocessos.dao.ParecerDAO;


import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
@Service("parecerService")
public class ParecerServiceImpl implements ParecerService {

	private ParecerDAO parecerDAO;

	@Autowired
	private LogService logService;

	@Autowired
	public ParecerServiceImpl(ParecerDAO parecerDAO) {
		this.parecerDAO = parecerDAO;
	}
	/**Método de inserir e também registra o que foi feito no log.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Objeto da classe.
	 */
	@Transactional
	public void createNew(ObjetoPadraoSemId obj) {
		parecerDAO.persist(obj);
		logService.createNewSemId(obj, Modulo.PROJETOBASE, Metodo.ADICIONAR);
	}
	/**Método de atualizar e também registra o que foi feito no log.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Objeto da classe.
	 */
	@Transactional
	public void update(ObjetoPadraoSemId obj) {
		parecerDAO.update(obj);
		logService.createNewSemId(obj, Modulo.PROJETOBASE, Metodo.ATUALIZAR);
	}
	/**Método de deletar e também registra o que foi feito no log.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Objeto da classe.
	 */
	@Transactional
	public void remove(ObjetoPadraoSemId obj) {
		parecerDAO.delete(obj);
		logService.createNewSemId(obj, Modulo.PROJETOBASE, Metodo.REMOVER);
	}
	/**Método de busca pelo Id.
	 * 
	 * @author Arthur Freire
	 * @param id Long - Objeto da classe.
	 */
	@Transactional(readOnly=true)
	public ObjetoPadraoSemId findById(Long id) {
		return parecerDAO.loadById(id);
	}
	/**Método listar todos os objetos.
	 * 
	 * @author Arthur Freire
	 */
	@Override
	public List<ObjetoPadraoSemId> findAll() {
		return parecerDAO.findAll();
	}
	/**Método listar todos os objetos da paginação.
	 * 
	 * @author Arthur Freire
	 * @param pagesize int - quantidade que será listado.
	 * @param page int - De qual posição irá começar a busca.
	 * 
	 * @return List<ObjetoPadrao> - lista de Objetos.
	 */
	@Override
	public List<ObjetoPadraoSemId> findAll(int pagesize, int page) {
		return parecerDAO.findAll( pagesize,  page);
	}
	/**Método conta todos os objetos da classe no banco de dados.
	 * 
	 * @author Arthur Freire
	 * @return long - total dos objetos.
	 */
	@Override
	public Long getNumberRecords() {
		return parecerDAO.getNumberRecords();     
	}
	/**Método listar com filtros.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Objeto da classe.
	 * 
	 * @return List<ObjetoPadrao> - lista de Objetos.
	 */
	@Transactional(readOnly=true)
	public List<ObjetoPadraoSemId> filter(ObjetoPadraoSemId obj) {
		return parecerDAO.filter(obj);
	}

	@Override
	public List<ObjetoPadraoSemId> findAll(String value, int pagesize, int page) {
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
	@Override
	public List<ObjetoPadraoSemId> filter(ObjetoPadraoSemId obj, int pagesize, int page){
		return parecerDAO.filter(obj, pagesize, page);
	}
	/**Método listar com filtros todos os objetos.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Objeto da classe.
	 * 
	 * @return List<ObjetoPadrao> - lista de Objetos.
	 */
	@Override
	public Long getNumberRecordsFilter(ObjetoPadraoSemId obj){
		return parecerDAO.getNumberRecordsFilter(obj);
	}
	/**Método de inserir e também registra o que foi feito no log - Exclusivo para objetos que não tem o id padrão.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Objeto da classe.
	 */
	@Override
	public void createNewSemId(ObjetoPadraoSemId obj) {
		parecerDAO.persist(obj);
		logService.createNewSemId(obj, Modulo.PROJETOBASE, Metodo.ADICIONAR);
	}

}
