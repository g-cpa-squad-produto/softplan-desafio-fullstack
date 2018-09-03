package br.com.renancelso.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import br.com.renancelso.padrao.BaseControl;
import br.com.renancelso.service.LoginServiceLocal;
import br.com.renancelso.service.UsuarioServiceLocal;
import br.com.renancelso.service.model.Usuario;

/**
 * @author Renan Celso
 */
@ManagedBean(name = "adminControl")
@ViewScoped
public class AdminControl extends BaseControl implements Serializable{

	
	private static final long serialVersionUID = 6347745939393960746L;
				
	@EJB
	private UsuarioServiceLocal usuarioService;
	
	@EJB
	private LoginServiceLocal loginService;
	
	private List<Usuario> listaUsuarios;	
	
	private Usuario usuarioSelecionado;
	
	private Usuario novoUsuario;
	
	@PostConstruct
	public void init() {		
		novoUsuario = null;
		usuarioSelecionado = null;
		listaUsuarios = new ArrayList<>();
		listaUsuarios = usuarioService.listarUsuarios();		
	}
	
	public String btNovoUsuario(){
		novoUsuario = new Usuario();			
		return null;
	}
	
	public String btNovoUsuarioVoltar() {
		novoUsuario = null;
		return null;
	}	
	
	public String btAlterarUsuarioVoltar() {
		novoUsuario = null;
		usuarioSelecionado = null;
		return null;
	}	
	
	public String btAtualizarUsuario() {
		try {
			
			if (usuarioSelecionado.getTipoUsuario() == null || "".equalsIgnoreCase(usuarioSelecionado.getTipoUsuario())) {
				addErrorMessage("É necessário informar o tipo de usuário.");
				novoUsuario.setTipoUsuario("");		
				return null;
			}

			if ("".equalsIgnoreCase(usuarioSelecionado.getLogin()) || "".equalsIgnoreCase(usuarioSelecionado.getSenha())) {
				addErrorMessage("Os campos login e senha são obrigatórios. Favor preenchê-los.");
				novoUsuario.setSenha("");				
				return null;
			}			
						
			usuarioSelecionado.setDhAtu(new Date());
			usuarioSelecionado = (Usuario) usuarioService.atualizar(usuarioSelecionado);

			addInfoMessage("Usuário " + usuarioSelecionado.getLogin() + " atualizado com sucesso");
			
			init();

			return null;
			
		}catch (Exception e) {
			log.error(e);
			addErrorMessage("Erro ao atualizar usuário. " + e.getMessage());
			return null;
		}
	}
	
	public String btExcluirNovoUsuario(){
		try {
			
			usuarioSelecionado = (Usuario) usuarioService.consultarPorChavePrimaria(usuarioSelecionado, usuarioSelecionado.getId());
				
			String login = usuarioSelecionado.getLogin();
			
			usuarioService.remover(usuarioSelecionado);
			
			addInfoMessage("Usuário " + login + " excluído com sucesso");
			
			usuarioSelecionado = null;
			
			init();
			
			return null;
		}catch (Exception e) {
			log.error(e);
			addErrorMessage("Erro ao excluir usuário. " + e.getMessage());
			return null;
		}
	}
	
	public String btCadastrarNovoUsuario() {
		try {
			
			if (novoUsuario.getTipoUsuario() == null || "".equalsIgnoreCase(novoUsuario.getTipoUsuario())) {
				addErrorMessage("É necessário informar o tipo de usuário.");
				novoUsuario.setTipoUsuario("");		
				return null;
			}

			if ("".equalsIgnoreCase(novoUsuario.getLogin()) || "".equalsIgnoreCase(novoUsuario.getSenha())) {
				addErrorMessage("Os campos login e senha são obrigatórios. Favor preenchê-los.");
				novoUsuario.setSenha("");				
				return null;
			}			

			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			listaUsuarios = loginService.buscarUsuarioPorLogin(novoUsuario.getLogin());

			if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
				addErrorMessage("e-mail " + novoUsuario.getLogin() + " já é cadastrado.");			
				novoUsuario.setLogin("");				
				return null;
			}
			
			novoUsuario.setDhAtu(new Date());
			novoUsuario = (Usuario) loginService.atualizar(novoUsuario);

			addInfoMessage("Usuário " + novoUsuario.getLogin() + " cadastrado com sucesso");
			
			init();

			return null;

		} catch (Exception e) {
			log.error(e);
			addErrorMessage("Erro ao cadastrar novo usuário. " + e.getMessage());
			return null;
		}
		
	}
	
	public void onRowSelect(SelectEvent event) {
		novoUsuario = null;
		usuarioSelecionado = (Usuario) event.getObject();        
    }		

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public Usuario getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(Usuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}	
}
