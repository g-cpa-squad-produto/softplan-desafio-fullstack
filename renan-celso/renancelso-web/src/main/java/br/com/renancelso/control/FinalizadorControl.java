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
import br.com.renancelso.service.FinalizadorServiceLocal;
import br.com.renancelso.service.TriadorServiceLocal;
import br.com.renancelso.service.UsuarioServiceLocal;
import br.com.renancelso.service.model.Parecer;
import br.com.renancelso.service.model.Processo;
import br.com.renancelso.service.model.Usuario;
import br.com.renancelso.service.model.UsuarioProcesso;

/**
 * @author Renan Celso
 */
@ManagedBean(name = "finalizadorControl")
@ViewScoped
public class FinalizadorControl extends BaseControl implements Serializable{
	
	private static final long serialVersionUID = -5124331212710389209L;
	
	@EJB
	private FinalizadorServiceLocal finalizadorService;
		
	private List<Processo> listaProcessos;			
	
	private Processo processoSelecionado;
	
	private Parecer novoParecer;
	
	@PostConstruct
	public void init() {	
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");			
		processoSelecionado = null;
		listaProcessos = new ArrayList<>();
		listaProcessos = finalizadorService.listarProcessosPendentesParecer(usuarioLogado);						
	}
	
	
	public void onRowSelect(SelectEvent event) {			
		processoSelecionado = (Processo) event.getObject();
		novoParecer = new Parecer();
		novoParecer.setProcesso(processoSelecionado);
		novoParecer = new Parecer();
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");	
		novoParecer.setUsuarioResponsavel(usuarioLogado);
	}
	
	public String btCadastrarNovoParecer(){
		
		if(novoParecer.getDescricaoParecer() == null
				|| "".equalsIgnoreCase(novoParecer.getDescricaoParecer())){
			addErrorMessage("Informe um parecer.");
			return null;
		}		
		
		novoParecer.setProcesso(processoSelecionado);		
		novoParecer = (Parecer) finalizadorService.salvar(novoParecer);		
		processoSelecionado.setParecerValido("S");	
		processoSelecionado = (Processo) finalizadorService.atualizar(processoSelecionado);
		
		addInfoMessage("Parecer realizado com sucesso.");
		
		init();
		
		return null;
		
	}
	
	public String btVisualizarProcessoVoltar() {		
		processoSelecionado = null;
		return null;
	}	

	public List<Processo> getListaProcessos() {
		return listaProcessos;
	}

	public void setListaProcessos(List<Processo> listaProcessos) {
		this.listaProcessos = listaProcessos;
	}

	public Processo getProcessoSelecionado() {
		return processoSelecionado;
	}

	public void setProcessoSelecionado(Processo processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}


	public Parecer getNovoParecer() {
		return novoParecer;
	}


	public void setNovoParecer(Parecer novoParecer) {
		this.novoParecer = novoParecer;
	}	

}
