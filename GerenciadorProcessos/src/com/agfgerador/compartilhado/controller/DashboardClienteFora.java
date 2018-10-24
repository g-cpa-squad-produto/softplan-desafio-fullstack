package com.agfgerador.compartilhado.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Span;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.XulElement;

import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.Globais;
import com.agfgerador.autenticacao.domain.Parametros;
import com.agfgerador.autenticacao.domain.Permissao;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.autenticacao.service.CompativeisService;
import com.agfgerador.autenticacao.service.ComponenteService;
import com.agfgerador.autenticacao.service.ParametrosService;
import com.agfgerador.autenticacao.service.PermissaoService;
import com.agfgerador.autenticacao.service.UsuarioPerfilService;
import com.agfgerador.autenticacao.service.UsuarioService;

public abstract class DashboardClienteFora extends GenericForwardComposer  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Div geral,atalhos;
	protected Tabbox tabbox;
	protected Tabs tabs;
	protected Tabpanels tabpanels;
	protected Menubar menubar;
	protected Label nomeInstituicao;
	protected Label nomeUsuario;
	protected Textbox param1, param2, param3;
	public Usuario usuario;
	protected String codSistema;
	protected Parametros vcParametros;
	protected Globais vcGlobais;
	protected Label globaisAno, globaisMes, globaisGestora;
	protected Parametros pGerais;
	protected List<UsuarioPerfil> usuarioPerfilList;
	protected Map<Long, Window> windows;
	protected Window windowAlterarSenha;
	protected Include includeLiberacao,includeShortcut,priincludeprincipal,segunincludeprincipal,
	terceincludeprincipal;
	private XulElement window;
	protected UsuarioService usuarioService;
	protected UsuarioPerfilService usuarioPerfilService;
	protected PermissaoService permissaoService;
	protected ParametrosService parametrosService;
	protected ComponenteService componenteService;
	protected CompativeisService compativeisService;

	
	public abstract void doAfterCompose(Component win) throws Exception;
	
	public void doAfterCompose(Component win,TipoSistema vsistema,String vcodSistema,String versaoApp,String nomeProjeto) throws Exception {
		super.doAfterCompose(win);
		win.setAttribute("controller",this);
		if(verificaVersao(versaoApp, vcodSistema)){
			session.setAttribute("iscompativelbaseapp", true);
			session.setAttribute("usuarioLogado", usuarioService.findByLogin("cliente"));
			session.setAttribute("sistema", vsistema);
			session.setAttribute("projeto", nomeProjeto);
			window = (Window)win;
			priincludeprincipal.setSrc("/view/saude/view/cidmanage.zul");
			segunincludeprincipal.setSrc("/view/saude/view/cidmanage.zul");
			terceincludeprincipal.setSrc("/view/saude/view/cidmanage.zul");
			if(setUsuario()){	
				windows = new HashMap<Long, Window>();
				tabbox.appendChild(tabs);
				tabbox.appendChild(tabpanels);
				pGerais = (Parametros) (parametrosService.findById((long)1));
				if(pGerais!=null)
					nomeInstituicao.setValue(pGerais.getNome());
				else
					nomeInstituicao.setValue("Ativ! - MB Solutions");
				vcGlobais = new Globais();
				codSistema = vcodSistema;
				setDadosIntegracao();
				montaMenu();
				montaAtalhos();
			}
		}
		else{
			session.setAttribute("iscompativelbaseapp", false);
		}
	}

	@SuppressWarnings("static-access")
	public boolean verificaVersao(String versaoApp,String codSistema){
		boolean ret = true;
		String versaoBase = compativeisService.LoadBySistema(Integer.parseInt(codSistema)).getVersaoliberada();
		if((versaoBase==null)||(!versaoBase.equals(versaoApp))){
			ret = false;
			try {
				Messagebox msg = new Messagebox();
				msg.show("A aplicação e o banco de dados estão em versões distintas. Contac-te o suporte.", "Atenção", msg.OK, msg.EXCLAMATION,
				new org.zkoss.zk.ui.event.EventListener() { 	
					public void onEvent(Event evt) throws InterruptedException {
				        if (evt.getName().equals("onOK")) 
				        	onClickSair();
				    }
				});	
			}
			catch (Exception e) {
				System.out.println("erro verificarVersao "+e);
			}
		}
		return ret;
	}
	
	private void setDadosIntegracao() {
		int randNumber = getRandomValue(usuario.getSenha().length());
		param1.setValue(usuario.getId()+"");
		param2.setValue(randNumber+"");
		param3.setValue(getSenhaId(randNumber));
		nomeUsuario.setValue(usuario.getNome());
	}
	
	public int getRandomValue(int tamanho){
		Random rand = new Random();
		return rand.nextInt((tamanho - 1));
	}
	
	public String getSenhaId(int fim){
		String senhaId="";
		senhaId = usuario.getSenha().substring(0,fim);
		senhaId=senhaId+usuario.getId()+usuario.getSenha().substring(fim, usuario.getSenha().length());
		return senhaId;
	}

	public boolean setUsuario()
	{	boolean ret = true;
		//SecurityContext ctx = SecurityContextHolder.getContext(); 
		if(true)
		{	usuario = usuarioService.findByLogin("cliente");
			if(usuario==null)
			{	ret = false;
				onClickSair();
			}
		} /*else{ 
			ret = false;
			onClickSair();
			}*/
		
		return ret;
	}
	public void  onClick$alterarsenha(){
		try {  
			windowAlterarSenha = (Window) Executions.createComponents("/view/compartilhado/view/alterarsenha.zul",geral, null);
			windowAlterarSenha.doModal();

		} catch (SuspendNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public void onClick$senhaLiberacao(){
		includeLiberacao.setVisible(true);
		try{
			((LiberacaoSistemaController) includeLiberacao.getAttribute("controller")).inicializaVariaveis();
		}catch(Exception e){
			System.out.println("erro na chamada da liberação da senha ");
		}
	
	}

	public void  onClick$sobreOSistema(){
		try {  
			windowAlterarSenha = (Window) Executions.createComponents("/view/compartilhado/view/sobreOSistema.zul",geral, null);
			windowAlterarSenha.doModal();
		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
	}

	public boolean VerifSeTemPermissao(Componente componente){
		boolean ret = false;
		try{
			for(UsuarioPerfil perfilUser:usuarioPerfilList){
				if(perfilUser.getPerfil().getSistema().equals((TipoSistema)session.getAttribute("sistema"))){
					List<Permissao> permissoes = permissaoService.findByPerfil(perfilUser.getPerfil());
					for(Permissao per:permissoes){
						if(per.getComponente().getId().toString().equals(componente.getId().toString())){
							ret=per.isAtivo();
							break;
						}
					}
					break;
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();}
		return ret;
	}

	@SuppressWarnings("unused")
	public void montaMenu()
	{	List<Permissao> permissaoListAux = new ArrayList<Permissao>();
	    List<Componente> menus = new ArrayList<Componente>();
	    List<Componente> menusItens = new ArrayList<Componente>();
	    Menu menu = null;
		Menupopup poupupMenu = null;
		Menuitem menuitem = null;
	    
		usuarioPerfilList = usuarioPerfilService.findByUsuario(usuario,(TipoSistema)session.getAttribute("sistema"));
		
		for(UsuarioPerfil usuarioPerfil : usuarioPerfilList)
		{	if(usuarioPerfil.isAtivo())
			{	menus = componenteService.findMenuBySistema(usuarioPerfil.getPerfil().getSistema());
				for(int x=0;x<menus.size();x++)
				{	if(permissaoService.temPemissaoComponente(usuarioPerfil.getPerfil(), usuarioPerfil.getPerfil().getSistema(), menus.get(x),false))
					{	menu = new Menu(menus.get(x).getDescricao());
						poupupMenu = new Menupopup();
						menusItens = componenteService.findMenuItemByMenu(menus.get(x).getNome(), usuarioPerfil.getPerfil().getSistema());
						for(int y=0;y<menusItens.size();y++)
						{	if(permissaoService.temPemissaoComponente(usuarioPerfil.getPerfil(), usuarioPerfil.getPerfil().getSistema(), menusItens.get(y),false))
							{	menuitem = new Menuitem(menusItens.get(y).getDescricao());
								menuitem.addForward("onClick", self, "onClickEventMenuItem", menusItens.get(y));
								poupupMenu.appendChild(menuitem);	
							}
						}
						menu.appendChild(poupupMenu);
						menubar.appendChild(menu);
					}
				}
			}
		}
		menuitem = new Menuitem("Sair");
		menuitem.setTooltiptext("Sair do sistema");
		menuitem.addForward("onClick", self, "onClickSair");
		menubar.appendChild(menuitem);
	}

	public void montaAtalhos(){
		List<Componente> compAtalhos = null;
		Div atalhosComp = new Div();
		atalhosComp.setSclass("shortcut");
		for(UsuarioPerfil usuarioPerfil : usuarioPerfilList)
		{	if(usuarioPerfil.isAtivo()){	
				compAtalhos = permissaoService.findComponentesByPerfil(usuarioPerfil.getPerfil(), usuarioPerfil.getPerfil().getSistema(), true);
				for(int x=0;x<compAtalhos.size();x++){
					atalhosComp.appendChild(getAtalho(compAtalhos.get(x),"btn btn-primary","glyphicon glyphicon-new-window",compAtalhos.get(x).getDescricao()));

				}
				 atalhos.appendChild(atalhosComp);
				if((compAtalhos!=null)&&(compAtalhos.size()>0))
					atalhos.appendChild(getAtalho(null, "btn btn-default btn-small shortcut-return", "glyphicon glyphicon-chevron-left","Voltar para Área de Trabalho"));
			}
		}
	}

	private A getAtalho(Componente atalho,String classA,String classSpan,String lb) {
		A a;
		Span span;
		Label label;
		a = new A();
		label = new Label(lb);
		span = new Span();
		a.setSclass(classA);
		span.setSclass(classSpan);
		a.appendChild(span);
		a.appendChild(label);
		a.addForward("onClick", self, "onClickAtalho",atalho);
		return a;
	}
	
	public void onClickAtalho(Event evt) {
		Componente atalho = (Componente)evt.getData();
		if(atalho!=null){
			Clients.evalJavaScript("shortcutActive('"+window.getUuid()+"')");
			includeShortcut.setSrc(atalho.getUrl());
		}
		else{
			Clients.evalJavaScript("shortcutDisable('"+window.getUuid()+"')");
			includeShortcut.setSrc("");
		}
	}

	public void onClickEventMenuItem(Event evt) {
		Componente comp = (Componente) evt.getData();
		includeShortcut.setSrc(comp.getUrl());
		priincludeprincipal.setVisible(false);
		segunincludeprincipal.setVisible(false);
		terceincludeprincipal.setVisible(false);
		/*novaWindow(comp,windows.get(comp.getId()),comp.getDescricao(),comp.getUrl());*/
	}
	@SuppressWarnings("deprecation")
	public void initGlobais(){
		Date data = new Date();
		vcGlobais.setAno(data.getYear()+1900);		
		vcGlobais.setMes(data.getMonth()+1);
		globaisAno.setValue(vcGlobais.getAno().toString());
		globaisMes.setValue(vcGlobais.getMes().toString());
		vcGlobais.setUsuario(usuario.getId());
		vcGlobais.setGestora((long)1);
		globaisGestora.setValue(pGerais.getNome());
		session.setAttribute("atGlobais", vcGlobais);	
	}
	
	public Window setWindowCriacao(Tab tab,Tabpanel tabPanel,String urlWindow){
		tab.setClosable(true);	
		Window win = (Window) Executions.createComponents(urlWindow,tabPanel, null);

		win.setAttribute("ConteinerController", this);
		tabs.appendChild(tab);
		tabpanels.appendChild(tabPanel);
		tabbox.setSelectedTab(tab);
		tabbox.setSelectedPanel(tabPanel);
		return win;

	}

	public void setWindow(Tab tab,Tabpanel tabPanel){
		if(tabs.getChildren().contains(tab)){
			tabbox.setSelectedTab(tab);
			tabbox.setSelectedPanel(tabPanel);
		}else{
			tabs.appendChild(tab);			
			tabpanels.appendChild(tabPanel);
			tabbox.setSelectedTab(tab);
			tabbox.setSelectedPanel(tabPanel);
		}		
	} 

	public Window novaWindow(Componente comp,Window win,String labeltab,String url){
		if((comp!=null)&&(!VerifSeTemPermissao(comp)))
			win = null;
		
		if(win == null){
			Tab tab = new Tab(labeltab);	
			Tabpanel tabpanel = new Tabpanel();
			win = setWindowCriacao(tab, tabpanel, url);
			win.setAttribute("tab", tab);
			win.setAttribute("tabpanel", tabpanel);
			if(comp!=null)
			{	win.setAttribute("componente", comp);
				windows.put(comp.getId(), win);
			}
			
		}else{
			IPadrao controller = (IPadrao) win.getAttribute("controller");
			controller.onInicio(); 
			setWindow((Tab)win.getAttribute("tab"),(Tabpanel) win.getAttribute("tabpanel"));	
		}
		
		return win;
	}

	public void novaWindow(Componente comp,IPadrao controller,String combo){
		novaWindow(comp, windows.get(comp.getId()), comp.getDescricao(),comp.getUrl());
		windows.get(comp.getId()).setAttribute("controllerLink", controller);
		windows.get(comp.getId()).setAttribute("comboLink", combo);
		
	}

	public void onClickSair(){
		Executions.sendRedirect("/logout");
	}

	protected Window windowFachadaRE;
	private List<Long> gruposRE;
	/*private Menu grupoRE;
	private Menupopup poupupGrupoRE;
	private Menupopup poupupMenuRE;*/

	public boolean existeGrupo(Long idGrupo)
	{	int x;
		boolean ret = false;
		for(x=0;x<gruposRE.size();x++){	
			if(gruposRE.get(x).toString().equals(idGrupo.toString()))
				break;
		}
		if(x<gruposRE.size())
			ret = true;
		else
			gruposRE.add(idGrupo);
		
		return ret;
			
	}
	
	public void verificaAvisos(){
		Usuario user = (Usuario)session.getAttribute("usuarioLogado");
		TipoSistema sistema = (TipoSistema)session.getAttribute("sistema");
		if((user!=null)&&(user.getAviso()!=null)){
			try {
				if(user.getAviso().getSistema().equals(sistema)){
					Window win=(Window)Executions.createComponents("/view/autenticacao/view/mavisomanage.zul",null, null);
					MAvisosController controller = (MAvisosController) win.getAttribute("controller");
					controller.setObjetos(user);
					win.doModal();
				}
				
			} catch (SuspendNotAllowedException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
