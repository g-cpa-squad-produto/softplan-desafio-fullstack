package com.agfgerador.compartilhado.controller;

import java.util.List;


import org.zkforge.json.simple.JSONArray;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.A;
import org.zkoss.zul.Include;

import com.agfgerador.compartilhado.util.AGFDAO;
import com.agfgerador.compartilhado.util.AGFJs;

public abstract class IncludeBaseSemId extends ControllerPadraoSemId {
	
	private static final long serialVersionUID = 1L;
	protected Include barraFerramentasInclude;
	protected  A btCadastrarTodos, btDeletarTodos;
	
	public void doAfterCompose(Component win,String tpTela) throws Exception {
		
		tipoTela = tpTela;
		win.setAttribute("controller",this); 
		super.doAfterCompose(win);
		renderizarListaPrincipal();
		window = (Include) win;
		mbdao = new AGFDAO();
		controllerComponenteSemId = new ComponenteZKControllerSemId();
	}  

	public abstract void onInicio(List<Object>objetos);

	public void onInicio()
	{  	barraFerramentas();
	}
	
	public void barraFerramentas(){
		barraFerramentasButtons = new JSONArray();

		btNovo = (A) barraFerramentasInclude.getFellow("btNovo");
		btNovo.addForward("onClick", self, "onClickbtNovo");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btNovo, "all");
		
		btSalvar = (A) barraFerramentasInclude.getFellow("btSalvar");
		btSalvar.addForward("onClick", self, "onClickbtSalvar");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btSalvar, "form");
		
		btRemover = (A) barraFerramentasInclude.getFellow("btRemover");
		btRemover.addForward("onClick", self, "onClickbtRemover");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btRemover, "all");

		btLista = (A) barraFerramentasInclude.getFellow("btLista"); 
		btLista.addForward("onClick", self, "onClickbtLista");
		barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btLista, "form");
	

	}
	
	public void menuView(String tipo){ 
		if(tipo.equals("form"))
			removeAll = false;
		else
			removeAll = true;
		Clients.evalJavaScript("menuViewInclude('"+tipo+"', '"+barraFerramentasButtons+"')");
	}

}
