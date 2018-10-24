package com.agfgerador.autenticacao.service;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agfgerador.autenticacao.dao.UsuarioDAO;
import com.agfgerador.autenticacao.domain.Metodo;
import com.agfgerador.autenticacao.domain.Modulo;
import com.agfgerador.autenticacao.domain.TipoUsuario;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;


@Service("usuarioService") 
public class UsuarioServiceImpl implements UsuarioService{

	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private LogService logService;
	@Autowired
	private SessionRegistry sessionReg;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	@Transactional
	public void createNew(ObjetoPadrao obj) {
		usuarioDAO.persist(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.ADICIONAR);
	}

	@Transactional(readOnly=true)
	public List<ObjetoPadrao> findAll() {
		return usuarioDAO.findAll();
	}

	@Transactional(readOnly=true)
	public List<Usuario> findByNome(String nome) {
		return usuarioDAO.findByNome(nome);
	}

	@Transactional
	public void update(ObjetoPadrao obj) {
		usuarioDAO.update(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.ATUALIZAR);
	}
	/**
	 * Usado inicialmente para o reset da senha de acesso ao usuário.
	 * @param obj [Usuario]
	 * 
	 * @author Carlos Pereira
	 */
	@Transactional
	public void updateSemLog(ObjetoPadrao obj) {
		usuarioDAO.update(obj);
	}
	
	@Transactional
	public void remove(ObjetoPadrao obj) {
		usuarioDAO.delete(obj);
		logService.createNew(obj, Modulo.AUTENTICAÇÃO, Metodo.REMOVER);
	}

	@Transactional(readOnly=true)
	public ObjetoPadrao findById(Long id) {
		return usuarioDAO.loadById(id);
	}

	@Transactional(readOnly=true)
	public List<Usuario> filter(Usuario obj, boolean allHabilitado,TipoUsuario tipo) {
		return usuarioDAO.filter(obj, allHabilitado,tipo);
	}
	
	@Transactional(readOnly=true)
	public Usuario findByLogin(String login) {
		return usuarioDAO.findByLogin(login);
	}
	
	public List<String> getUserOnLine(){
		List<Object> principals = sessionReg.getAllPrincipals();
		List<String> result = new ArrayList<String>(); 
		for(int i = 0; i < principals.size(); i++) {
		      result.add(principals.get(i).toString());
		}
		return result;
	}

	public void setSessionReg(SessionRegistry sessionReg) {
		this.sessionReg = sessionReg;
	}
	
	@Transactional(readOnly=true)
	public  List<Usuario> findGestorControla()
	{
		return usuarioDAO.findGestorControla();
	}
	
	@Transactional(readOnly=true)
	public  List<Usuario> findAnalistaControla()
	{
		return usuarioDAO.findAnalistaControla();
	}
	@Transactional(readOnly=true)
	@Override
	public List<ObjetoPadrao> findAll(int pagesize, int page) {
		return usuarioDAO.findAll(pagesize, page);
	}
	@Transactional(readOnly=true)
	@Override
	public Long getNumberRecords() {
		return usuarioDAO.getNumberRecords();
	}

	@Override
	public List<ObjetoPadrao> filter(ObjetoPadrao obj) {
		return  usuarioDAO.filter(obj);
	}
	@Transactional(readOnly=true)
	@Override
	public List<ObjetoPadrao> findAll(String value, int pagesize, int page) {
		return usuarioDAO.findAll(value, pagesize, page);
	}
	@Transactional(readOnly=true)
	@Override
	public Long getNumberRecords(String value) {
		return usuarioDAO.getNumberRecords(value);
	}

	@Transactional(readOnly=true)
	@Override
	public Usuario findByAutentication(String login, String senha) {
		return usuarioDAO.findByAutentication(login,senha);
	}
	@Transactional(readOnly=true)
	@Override
	public Long avisos(boolean temAvisos) {
		return usuarioDAO.avisos(temAvisos);
	}
	
	
	@Override
 	public List<ObjetoPadrao> filter(ObjetoPadrao obj, int pagesize, int page){
 		return usuarioDAO.filter(obj, pagesize, page);
 	}

 	@Override
 	public Long getNumberRecordsFilter(ObjetoPadrao obj){
 		return usuarioDAO.getNumberRecordsFilter(obj);
 	}

	@Override
	public void createNew(ObjetoPadraoSemId obj) {

		
	}
	
}