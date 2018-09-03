package br.com.renancelso.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import br.com.renancelso.padrao.BaseControl;
import br.com.renancelso.service.TriadorServiceLocal;
import br.com.renancelso.service.UsuarioServiceLocal;
import br.com.renancelso.service.model.Processo;
import br.com.renancelso.service.model.Usuario;
import br.com.renancelso.service.model.UsuarioProcesso;

/**
 * @author Renan Celso
 */
@ManagedBean(name = "triadorControl")
@ViewScoped
public class TriadorControl extends BaseControl implements Serializable{
	
	private static final long serialVersionUID = 1271163985557010346L;

	@EJB
	private UsuarioServiceLocal usuarioService;
	
	@EJB
	private TriadorServiceLocal triadorService;
	
	private List<Processo> listaProcessos;	
	
	private List<Usuario> listaUsuarios;	
	
	private UsuarioProcesso usuarioAdicionar;
	
	private Usuario usuarioAux;
	
	private Processo processoSelecionado;
	
	private Processo novoProcesso;
	
	@PostConstruct
	public void init() {	
		usuarioAux = new Usuario();
		novoProcesso = null;
		processoSelecionado= null;
		listaProcessos = new ArrayList<>();
		listaProcessos = triadorService.listarProcessos();			
		listaUsuarios = new ArrayList<>();
		listaUsuarios = usuarioService.listarUsuariosFinalizadores();			
	}
	
	public String btNovoProcesso(){
		novoProcesso = new Processo();			
		return null;
	}
	
	public String btNovoProcessoVoltar() {
		usuarioAux = new Usuario();
		novoProcesso = null;
		return null;
	}	
		
	
	public String btVisualizarProcessoVoltar() {
		novoProcesso = null;
		processoSelecionado = null;
		return null;
	}	
	
	public String btCadastrarNovoProcesso() {
		
		try {
			
			if(novoProcesso.getDescricaoProcesso() == null || "".equalsIgnoreCase(novoProcesso.getDescricaoProcesso())) {
				addErrorMessage("É nessário informar uma descrição.");
				return null;
			}	
			
			if(novoProcesso.getUsuariosAdicionadosParecer() == null 
					|| novoProcesso.getUsuariosAdicionadosParecer().isEmpty()){
				addErrorMessage("É nessário adicionar usuários que poderão dar parecer.");
				return null;
			}
			
			HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);		
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");	
			
			novoProcesso.setUsuarioCriador(usuarioLogado);
			
			novoProcesso.setParecerValido("N");			
			
			novoProcesso = (Processo) triadorService.salvar(novoProcesso);
			
			for (UsuarioProcesso usuarioProcesso : novoProcesso.getUsuariosParecer()) {
				UsuarioProcesso up = new UsuarioProcesso();
				up.setUsuario(usuarioProcesso.getUsuario());
				up.setProcesso(novoProcesso);	
				triadorService.salvar(up);
			}
						
			addInfoMessage("Processo cadastrado com sucesso");	
			
			init();
			
			return null;
			
		} catch (Exception e) {
			log.error(e);
			addErrorMessage("Erro ao cadastrar novo processo. " + e.getMessage());
			return null;
		}
		
	}
	
	public String btAdicionarUsuarioParecerProcesso(){
		
		if(novoProcesso.getUsuariosParecer() == null){
			novoProcesso.setUsuariosParecer(new ArrayList<UsuarioProcesso>());
		}
		
		usuarioAdicionar = new UsuarioProcesso();
		
		usuarioAdicionar.setUsuario(usuarioAux);
		usuarioAdicionar.setProcesso(novoProcesso);		
		
		if(!novoProcesso.getUsuariosParecer().contains(usuarioAdicionar)){
			novoProcesso.getUsuariosParecer().add(usuarioAdicionar);	
		}
		
		return null;
	}
	
	public String btLimparUsuarioAdicionados(){
		novoProcesso.setUsuariosParecer(new ArrayList<UsuarioProcesso>());		
		return null;
	}
	
	public void onRowSelect(SelectEvent event) {
		novoProcesso = null;
		processoSelecionado = (Processo) event.getObject();  
		processoSelecionado.setUsuariosParecer(triadorService.buscarUsuariosVinculados(processoSelecionado));		
    }

	public List<Processo> getListaProcessos() {
		return listaProcessos;
	}

	public void setListaProcessos(List<Processo> listaProcessos) {
		this.listaProcessos = listaProcessos;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Processo getProcessoSelecionado() {
		return processoSelecionado;
	}

	public void setProcessoSelecionado(Processo processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}

	public Processo getNovoProcesso() {
		return novoProcesso;
	}

	public void setNovoProcesso(Processo novoProcesso) {
		this.novoProcesso = novoProcesso;
	}

	public UsuarioProcesso getUsuarioAdicionar() {
		return usuarioAdicionar;
	}

	public void setUsuarioAdicionar(UsuarioProcesso usuarioAdicionar) {
		this.usuarioAdicionar = usuarioAdicionar;
	}

	public Usuario getUsuarioAux() {
		return usuarioAux;
	}

	public void setUsuarioAux(Usuario usuarioAux) {
		this.usuarioAux = usuarioAux;
	}

		

}
