package com.agfgerador.autenticacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.dao.UsuarioPerfilDAO;
import com.agfgerador.autenticacao.domain.Metodo;
import com.agfgerador.autenticacao.domain.Modulo;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;


/**UsuarioPerfilServiceImpl - Classe de comnicação com os DAO e Service.
 * 
 * @author Arthur Freire
 */
@Service("usuarioPerfilService") 
public class UsuarioPerfilServiceImpl implements UsuarioPerfilService{

	private UsuarioPerfilDAO usuarioPerfilDAO;
	 
	@Autowired
	private LogService logService;
	
	@Autowired
	public UsuarioPerfilServiceImpl(UsuarioPerfilDAO usuarioPerfilDAO) {
		this.usuarioPerfilDAO = usuarioPerfilDAO;
	}
    /**Método de inserir e também registra o que foi feito no log.
     * 
     * @author Arthur Freire
     * @param obj ObjetoPadrao - Objeto da classe.
     */
	@Transactional
	public void createNew(ObjetoPadrao obj) {
		usuarioPerfilDAO.persist(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.ADICIONAR);
	}
    /**Método listar todos os objetos.
     * 
     * @author Arthur Freire
     */
	@Transactional(readOnly=true)
	public List<ObjetoPadrao> findAll() {
		return usuarioPerfilDAO.findAll();
	}

	@Transactional(readOnly=true)
	public List<UsuarioPerfil> findByNome(String nome) {
		return usuarioPerfilDAO.findByNome(nome);
	}
    /**Método de atualizar e também registra o que foi feito no log.
     * 
     * @author Arthur Freire
     * @param obj ObjetoPadrao - Objeto da classe.
     */
	@Transactional
	public void update(ObjetoPadrao obj) {
		usuarioPerfilDAO.update(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.ATUALIZAR);
	}
    /**Método de deletar e também registra o que foi feito no log.
     * 
     * @author Arthur Freire
     * @param obj ObjetoPadrao - Objeto da classe.
     */
	@Transactional
	public void remove(ObjetoPadrao obj) {
		usuarioPerfilDAO.delete(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.REMOVER);
	}
    /**Método de busca pelo Id.
     * 
     * @author Arthur Freire
     * @param id Long - Objeto da classe.
     */
	@Transactional(readOnly=true)
	public ObjetoPadrao findById(Long id) {
		return usuarioPerfilDAO.loadById(id);
	}
    /**Método listar com filtros.
     * 
     * @author Arthur Freire
     * @param obj ObjetoPadrao - Objeto da classe.
     * 
     * @return List<ObjetoPadrao> - lista de Objetos.
     */
	@Transactional(readOnly=true)
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
		return usuarioPerfilDAO.filter(obj);
	}
	
	@Transactional(readOnly=true)
	public List<UsuarioPerfil> findByUsuario(Usuario p,TipoSistema s) {
		return usuarioPerfilDAO.findByUsuario(p,s);
	}
	
	@Transactional(readOnly=true)
	public boolean verificaPermissaoSistemaUsuario(Usuario p,TipoSistema sistema){
		return usuarioPerfilDAO.verificaPermissaoSistemaUsuario(p,sistema);
	}
	
	@Transactional(readOnly=true)
	public boolean haPerfilAdm(Long codusuario){
		return usuarioPerfilDAO.haPerfilAdm(codusuario);
	}
	@Transactional(readOnly=true)
	public List<Usuario> loadAllExcptAdmin(TipoSistema sistema)
	{
		return usuarioPerfilDAO.loadAllExcptAdmin(sistema);
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
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
		return usuarioPerfilDAO.findAll(pagesize, page);
	}
    /**Método conta todos os objetos da classe no banco de dados.
     * 
     * @author Arthur Freire
     * @return long - total dos objetos.
     */
	@Override
	public Long getNumberRecords() {
		return usuarioPerfilDAO.getNumberRecords() ;
	}

	@Transactional(readOnly=true)
	public List<UsuarioPerfil> findByUsuario(Usuario p) {
		return usuarioPerfilDAO.findByUsuario(p) ;
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
    @Override
    public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page){
      return usuarioPerfilDAO.filter(obj, pagesize, page);
    }
    /**Método listar com filtros todos os objetos.
     * 
     * @author Arthur Freire
     * @param obj ObjetoPadrao - Objeto da classe.
     * 
     * @return List<ObjetoPadrao> - lista de Objetos.
     */
    @Override
    public Long getNumberRecordsFilter(ObjetoPadrao obj){
      return usuarioPerfilDAO.getNumberRecordsFilter(obj);
    }

	@Override
	public void createNew(ObjetoPadraoSemId obj) {

		
	}
}