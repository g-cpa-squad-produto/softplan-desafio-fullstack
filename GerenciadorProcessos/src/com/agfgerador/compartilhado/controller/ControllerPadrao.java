package com.agfgerador.compartilhado.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkforge.json.simple.JSONArray;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.A;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;
import org.zkoss.zul.impl.XulElement;

import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.Permissao;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.service.ComponenteService;
import com.agfgerador.autenticacao.service.UsuarioService;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
import com.agfgerador.compartilhado.service.ServicePadrao;
import com.agfgerador.compartilhado.service.ServicePadraoSemId;
import com.agfgerador.compartilhado.service.SqlJdbcsService;
import com.agfgerador.compartilhado.util.AGFComponente;
import com.agfgerador.compartilhado.util.AGFDAO;
import com.agfgerador.compartilhado.util.AGFJs;


public abstract class ControllerPadrao extends GenericForwardComposer implements IPadrao  {


	private static final long serialVersionUID = 1L;
	
	protected AnnotateDataBinder binder;
	protected Div list,form;
	protected Listbox listbox;
	protected Auxhead auxhead;
	protected String nomeTela;
	protected Usuario usuarioAux;
	protected Paging paginacao;
	protected int paginaAnterior=0;
	protected int pageIncludSize=5;
	protected int pageSize=8; 
	protected ServicePadrao service;
	protected ServicePadraoSemId serviceSemId;
	protected ObjetoPadrao objp;
	protected ObjetoPadraoSemId objpsemid;
	protected boolean removeAll;
	protected String tipoTela;
	protected Map<String,InputElement> elementos;
	protected Include barraFerramentas;
	protected Menuitem reportContexto;
	protected List<Object> objetos;
	protected boolean  possuiBarraFerramentas;

	protected XulElement window;
	protected JSONArray barraFerramentasButtons;
	protected A btNovo, btSalvar, btRemover, btCancelar, btAnterior, btProximo, 
				btImprimir,btExportar, btInformacoes, btLista;
	
	protected ComponenteService componenteService;
	protected UsuarioService usuarioService;
	protected SqlJdbcsService sqlJdbcService;
	protected IFilhas controllerFilhas;
	protected AGFDAO mbdao;
	protected ComponenteZKController controllerComponente;
	protected Map<Long, Window> windows;

	public void doAfterCompose(Component win,boolean possuiBarraferramenta,String tpTela) throws Exception {
		super.doAfterCompose(win);
		tipoTela = tpTela;
		possuiBarraFerramentas = possuiBarraferramenta;
		usuarioAux = (Usuario)session.getAttribute("usuarioLogado");
		window = (Window) win;
		nomeTela = (String) win.getAttribute("nomeTela");
		if((window.getAttribute("istemfilha")!=null)&&((boolean)window.getAttribute("istemfilha"))){
			controllerFilhas = (IFilhas) window.getAttribute("controller");
			controllerFilhas.setControllerFilha();
		}
		else{
			controllerFilhas = null;
		}
		mbdao = new AGFDAO();
		controllerComponente = new ComponenteZKController();
		registrarPermissaoPrincipal((Componente) win.getAttribute("componente"));
		renderizarListaPrincipal();
		
	} 

	public void onInicializacaoTela(String tpTela) {
		if((tpTela!=null)&&(execution.getArg().size()==0)||(execution.getArg().size()==1)){
			if(possuiBarraFerramentas) 
				barraFerramentas();
			
			if((tpTela.equals("all"))){	
				changeView(form.getUuid(), list.getUuid());
				onClickbtLista();
			}
			else if(tpTela.equals("form")){	
				IForm controller = (IForm) window.getAttribute("controller");
				controller.setObjetoTelaForm();
			}
			else if(tpTela.equals("inline")) {	
				onClickbtLista();
				onClickbtNovo();
			}	
		}
		else if((execution.getArg().size()>0)&&((boolean)execution.getArg().get("ismodal")==true)){
			window.setAttribute("controllerPaiModal", execution.getArg().get("controller"));
			window.setAttribute("controllerAvoModal", execution.getArg().get("controllerPai"));
			IModal controller = (IModal)window.getAttribute("controller");
			controller.onModal();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void registrarPermissaoPrincipal(Componente componente){
		controllerComponente.registrarPermissaoPrincipal(componente, barraFerramentas, (List<Permissao>) this.session.getAttribute("permissao"));	
	}

	public ObjetoPadrao btSalvar(ObjetoPadrao obj,ServicePadrao service) throws InterruptedException
	{	if(obj!=null){
			getRelacoesNpara1(); 
			if(isValidForm())
			{	if(obj.getId() == null )	
					salvarObjeto(obj, service);
				else
					obj = updateObjeto(obj, service);
				binder.loadAll();
			}
			else
				obj = null;
			}
		return obj;
	}
	
	public ObjetoPadraoSemId btSalvar(ObjetoPadraoSemId obj,ServicePadraoSemId service) throws InterruptedException
	{	if(obj!=null){
			getRelacoesNpara1(); 
			if(isValidForm())
			{	
				if(obj.getId() == null )	
					salvarObjetoSemId(obj, service);
				else
					obj = updateObjeto(obj, service);
				binder.loadAll();
			}
			else
				obj = null;
			}
		return obj;
	}

	public void salvarObjeto(ObjetoPadrao obj,ServicePadrao service)
	{	try 
		{	if(mbdao.createnew(obj, service)){	
			    AGFComponente.showMessage("sucesso","O Registro foi adicionado com sucesso.");
				atualizaComboLink();
			}
		} 
		catch (Exception e) {
			AGFComponente.showMessage("erro","Erro ao adicionar o registro.");
			e.printStackTrace();
		}
	}
	
	public void salvarObjetoSemId(ObjetoPadraoSemId obj,ServicePadraoSemId service)
	{	try 
		{	if(mbdao.createnewSemId(obj, service)){	
			    AGFComponente.showMessage("sucesso","O Registro foi adicionado com sucesso.");
				atualizaComboLink();
			}
		} 
		catch (Exception e) {
			AGFComponente.showMessage("erro","Erro ao adicionar o registro.");
			e.printStackTrace();
		}
	}

	public ObjetoPadrao updateObjeto(ObjetoPadrao obj,ServicePadrao service)
	{	try
		{	try
			{	obj = mbdao.merge(obj, service);
				if(obj!=null){	
					AGFComponente.showMessage("sucesso","O Registro foi atualizado com sucesso.");
					atualizaComboLink();
				}
			} 
			catch (Exception e) {
				obj = null;
				AGFComponente.showMessage("erro","Erro ao atualizar o registro.");
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			obj = null;
			AGFComponente.showMessage("erro","Erro ao atualizar o registro.");
			e.printStackTrace();
		}
	
		return obj;
	}
	
	
	public ObjetoPadraoSemId updateObjeto(ObjetoPadraoSemId obj,ServicePadraoSemId service)
	{	try
		{	try
			{	obj = mbdao.merge(obj, service);
				if(obj!=null){	
					AGFComponente.showMessage("sucesso","O Registro foi atualizado com sucesso.");
					atualizaComboLink();
				}
			} 
			catch (Exception e) {
				obj = null;
				AGFComponente.showMessage("erro","Erro ao atualizar o registro.");
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			obj = null;
			AGFComponente.showMessage("erro","Erro ao atualizar o registro.");
			e.printStackTrace();
		}
	
		return obj;
	}

	public ObjetoPadrao saveOrUpdateObjeto(ObjetoPadrao obj,ServicePadrao service){
		if(obj.getId()==null)
			salvarObjeto(obj, service);
		else
			obj = updateObjeto(obj, service);
		
		return obj;
	}

	public void onClick$listbox()
	{	ObjetoPadrao obj  = controllerComponente.onDoubleClick$listbox(listbox);
		if(obj!=null){
			carregarObjeto(obj);  
			menuView("form");
		}
		
	}

	public  void onClickbtProximo()
	{	ObjetoPadrao obj  = controllerComponente.onClickbtProximo(listbox);
		if(obj!=null){
			carregarObjeto(obj);  
		}
	}

	public void onClickbtAnterior()
	{	ObjetoPadrao obj  = controllerComponente.onClickbtAnterior(listbox);
		if(obj!=null){
			carregarObjeto(obj);  
		}
	}
	
	public  void btNovo()
	{	limparRelacoesNpara1();
		inicializarRelacoesNpara1();
		if(controllerFilhas!=null)
			controllerFilhas.setObjetosFilha();
		// garante que os dois componentes existem na window, pois há solicitações em que o tipo da tela faça com que um ou os dois sejam nulos - Arthur Freire
		if((tipoTela!=null)&&(tipoTela.equals("all")))
			changeView(list.getUuid(), form.getUuid());

		if(binder!=null)
			binder.loadAll();	
		menuView("form");
		
	}

	public ObjetoPadrao btCancelar(ObjetoPadrao obj,ServicePadrao service) {
		if(obj.getId()!= null){
			obj = service.findById(obj.getId());
			setRelacoesNpara1();
			binder.loadAll();
		}
		else 
			AGFComponente.showMessage("alerta","O registro ainda não foi salvo.");	 
		return obj;
	}
	
	public ObjetoPadraoSemId btCancelar(ObjetoPadraoSemId obj,ServicePadraoSemId service) {
		if(obj.getId()!= null){
			obj = service.findById(obj.getId());
			setRelacoesNpara1();
			binder.loadAll();
		}
		else 
			AGFComponente.showMessage("alerta","O registro ainda não foi salvo.");	 
		return obj;
	}

	public void btRemover(ObjetoPadrao obj, ServicePadrao s) throws InterruptedException{
		service =s;
		objp =obj;
		if(removeAll){ 
			Messagebox.show("Remover este(s) registro(s)?", "Remove", Messagebox.YES|Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {

				public void onEvent(Event evt) {
					switch (((Integer)evt.getData()).intValue()) {
					case Messagebox.YES:
						try{
							boolean remover = false;
							boolean removeOK = false;
							Boolean removeDependenciasOK;
							for (Object item : listbox.getItems()) {
								for (Object lbCell : ((Listitem) item).getChildren()) {
									if(remover == true){
										remover = false;
										Long idLista = Long.valueOf(((Listcell) lbCell).getLabel());
										ObjetoPadrao b = service.findById(idLista);
										removeDependenciasOK=removeDependencias(b);
										if((removeDependenciasOK==null)||(removeDependenciasOK))
											removeOK = mbdao.remove(b, service);
										else if(!removeDependenciasOK)
											AGFComponente.showMessage("erro","Erro ao excluir as dependências do objeto.");
									}
									if(((Listcell) lbCell).getChildren().size() == 1){
										Checkbox check = (Checkbox) ((Listcell) lbCell).getChildren().get(0);	
										if(check.isChecked()){		
											remover = true;
										}
									}
								}
							}
							if(removeOK)
							{	AGFComponente.showMessage("sucesso", "O Registro removido com sucesso.");
								onClickbtLista();
								atualizaComboLink();
							}
							else
								AGFComponente.showMessage("erro","Erro ao excluir o registro.");
						} catch (Exception e) {
							AGFComponente.showMessage("erro","Erro ao excluir o registro.");
						}
						break; 
					case Messagebox.NO:
						break; 
					}
				}
			}); 
		}else{
			Messagebox.show("Remover este registro?", "Remove", Messagebox.YES|Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {
				public void onEvent(Event evt) {
					switch (((Integer)evt.getData()).intValue()) {
					case Messagebox.YES:
						try{
							Boolean removeDependenciasOK = removeDependencias(objp);
							
							if((removeDependenciasOK==null)||(removeDependenciasOK)){
								if(mbdao.remove(objp,service))
									AGFComponente.showMessage("sucesso", "O Registro removido com sucesso.");
								else
									AGFComponente.showMessage("erro","Erro ao excluir as dependências do objeto.");
							}
							else
								AGFComponente.showMessage("erro","Erro ao excluir as dependências do objeto.");
							onClickbtLista();
							atualizaComboLink();
							
						} catch (Exception e) {
							AGFComponente.showMessage("erro","Erro ao excluir o registro.");
						}
						break; 
					case Messagebox.NO:
						break; 
					}
				}
			});
		}
	}
	
	public void btRemover(ObjetoPadraoSemId obj, ServicePadraoSemId s) throws InterruptedException{
		serviceSemId =s;
		objpsemid =obj;
		if(removeAll){ 
			Messagebox.show("Remover este(s) registro(s)?", "Remove", Messagebox.YES|Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {

				public void onEvent(Event evt) {
					switch (((Integer)evt.getData()).intValue()) {
					case Messagebox.YES:
						try{
							boolean remover = false;
							boolean removeOK = false;
							Boolean removeDependenciasOK;
							for (Object item : listbox.getItems()) {
								for (Object lbCell : ((Listitem) item).getChildren()) {
									if(remover == true){
										remover = false;
										Long idLista = Long.valueOf(((Listcell) lbCell).getLabel());
										ObjetoPadraoSemId b = serviceSemId.findById(idLista);
										removeDependenciasOK=removeDependencias(b);
										if((removeDependenciasOK==null)||(removeDependenciasOK))
											removeOK = mbdao.remove(b, serviceSemId);
										else if(!removeDependenciasOK)
											AGFComponente.showMessage("erro","Erro ao excluir as dependências do objeto.");
									}
									if(((Listcell) lbCell).getChildren().size() == 1){
										Checkbox check = (Checkbox) ((Listcell) lbCell).getChildren().get(0);	
										if(check.isChecked()){		
											remover = true;
										}
									}
								}
							}
							if(removeOK)
							{	AGFComponente.showMessage("sucesso", "O Registro removido com sucesso.");
								onClickbtLista();
								atualizaComboLink();
							}
							else
								AGFComponente.showMessage("erro","Erro ao excluir o registro.");
						} catch (Exception e) {
							AGFComponente.showMessage("erro","Erro ao excluir o registro.");
							e.printStackTrace();
						}
						break;
					case Messagebox.NO:
						break; 
					}
				}
			}); 
		}else{ 
			Messagebox.show("Remover este registro?", "Remove", Messagebox.YES|Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {
				public void onEvent(Event evt) {
					switch (((Integer)evt.getData()).intValue()) {
					case Messagebox.YES:
						try{
							Boolean removeDependenciasOK = removeDependencias(objp);
							System.out.println("estou aqui 1");
							if((removeDependenciasOK==null)||(removeDependenciasOK)){
								System.out.println("estou aqui 2");
								if(mbdao.remove(objp,service))
									AGFComponente.showMessage("sucesso", "O Registro removido com sucesso.");
								else
									AGFComponente.showMessage("erro","Erro ao excluir as dependências do objeto.");
							}
							else
								AGFComponente.showMessage("erro","Erro ao excluir as dependências do objeto.");
							onClickbtLista();
							atualizaComboLink();
							
						} catch (Exception e) {
							AGFComponente.showMessage("erro","Erro ao excluir o registro.");
							e.printStackTrace();
						}
						break; 
					case Messagebox.NO:
						break; 
					}
				}
			});
		}
	}

	public void paginacaoBtRemover(boolean exibeMsg)
	{
		if(listbox.getModel().getSize() == 1){
			onClickbtLista();
		}else{
			if(listbox.getSelectedIndex() == 0){
				onClickbtProximo();
			}else{
				if(listbox.getSelectedIndex() < (listbox.getModel().getSize() -1) ){
					onClickbtProximo();
				}else{
					if(listbox.getSelectedIndex() == (listbox.getModel().getSize() -1) ){
						onClickbtAnterior();
					}
				}
			}
		}
		if(exibeMsg)
			AGFComponente.showMessage("sucesso", "O Registro removido com sucesso.");
		onClickbtLista();
	}
	public void btLista()
	{	changeView(form.getUuid(), list.getUuid());
		menuView("list");
	}

	public void carregarObj(Object obj)
	{	inicializarRelacoesNpara1(); 
		setRelacoesNpara1();
		if(controllerFilhas!=null)
			controllerFilhas.setObjetosFilha();
		
		if((tipoTela!=null)&&(tipoTela.equals("all")))// garante que os dois componentes existem na window, pois há solicitações em que o tipo da tela faça com que um ou os dois sejam nulos - Arthur Freire
			changeView(list.getUuid(), form.getUuid()); 
	
		if(binder!=null)
			binder.loadAll();
		
	}
	
	public void atualizaComboLink()
	{	IComboLink controller = (IComboLink) window.getAttribute("controllerLink");
		String combo = (String) window.getAttribute("comboLink");
		if(controller!=null)
			(controller).atualizaComboLink(combo);
		
	}

	public void labelLink(String ncomp,String combo)
	{	Componente comp = componenteService.findByNomeAndSistema(ncomp, (TipoSistema)session.getAttribute("sistema"));
		if(comp!=null){
			windows = new HashMap<Long, Window>();
			((DashboardPadrao)window.getRoot().getAttribute("controller")).novaWindow(comp, windows.get(comp.getId()), comp.getDescricao(),comp.getUrl());
		}else {
			AGFComponente.showMessage("erro","Link de acesso está incorreto, por favor entre em contato com o Suporte técnico.");
		}
	}
	
	/*public void labelLink(String ncomp,String combo)
	{	Componente comp = componenteService.findByNomeAndSistema(ncomp, (TipoSistema)session.getAttribute("sistema"));
		if(comp!=null){
			//caso a window seja originada de uma modal. Deve-se pegar o controller da dashboard de forma diferente. - Arthur Freire
			if(window.getAttribute("controllerAvoModal")!=null) {
				System.out.println("controller: "+(IPadrao)window.getAttribute("controller")+", combo: "+comp+", aquia auqi aquia auiqa 22222222222");
				((DashboardPadrao)window.getAttribute("controllerAvoModal")).novaWindow(comp,(IPadrao)window.getAttribute("controller"),combo);
			}else {
				((DashboardPadrao)window.getRoot().getAttribute("controller")).novaWindow(comp,(IPadrao)window.getAttribute("controller"),combo);
				System.out.println("controller: "+(IPadrao)window.getAttribute("controller")+", combo: "+comp+", aquia auqi aquia auiqa 333333333333");
			}
		}
	}*/

	public void onClickbtExportar() throws IOException{
		AGFComponente.exportar(nomeTela, listbox);
	}

	public void barraFerramentas(){
		barraFerramentasButtons = new JSONArray();
		
		btNovo = (A) barraFerramentas.getFellow("btNovo");
		btNovo.addForward("onClick", self, "onClickbtNovo");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btNovo, "all");
		
		btSalvar = (A) barraFerramentas.getFellow("btSalvar");
		btSalvar.addForward("onClick", self, "onClickbtSalvar");
		barraFerramentasButtons =AGFJs.addItemBF(barraFerramentasButtons, btSalvar, "form");
		
		btRemover = (A) barraFerramentas.getFellow("btRemover");
		btRemover.addForward("onClick", self, "onClickbtRemover");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btRemover, "all");


		btCancelar = (A) barraFerramentas.getFellow("btCancelar");
		btCancelar.addForward("onClick", self, "onClickbtCancelar");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btCancelar, "form");

		btAnterior = (A) barraFerramentas.getFellow("btAnterior");
		btAnterior.addForward("onClick", self, "onClickbtAnterior");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btAnterior, "form");

		btProximo = (A) barraFerramentas.getFellow("btProximo");
		btProximo.addForward("onClick", self, "onClickbtProximo");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btProximo, "form");
	

		btImprimir = (A) barraFerramentas.getFellow("btImprimir");
		btImprimir.addForward("onClick", self, "onClickbtImprimir");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btImprimir, "all");

		btExportar = (A) barraFerramentas.getFellow("btExportar");
		btExportar.addForward("onClick", self, "onClickbtExportar");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btExportar, "all");
		

		btInformacoes = (A) barraFerramentas.getFellow("btInformacoes");
		btInformacoes.addForward("onClick", self, "onClickbtInformacoes");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btInformacoes, "all");
		

		btLista = (A) barraFerramentas.getFellow("btLista"); 
		btLista.addForward("onClick", self, "onClickbtLista");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btLista, "all");
		
		reportContexto = (Menuitem) barraFerramentas.getFellow("reportContexto");
		reportContexto.addForward("onClick", self, "onClick$reportContexto");
		
	}

	public void menuView(String tipo){
		if(tipo.equals("form"))
			removeAll = false;
		else
			removeAll = true;
		Clients.evalJavaScript("menuView('"+tipo+"', '"+barraFerramentasButtons+"')");
	}
	
	public void changeView(String div1, String div2){ 
		Clients.evalJavaScript("changeView('"+div1+"','"+div2+"')"); 
	}

	public void inicializaBarraFerramentas() {
		barraFerramentasButtons = new JSONArray();
	}	
}
