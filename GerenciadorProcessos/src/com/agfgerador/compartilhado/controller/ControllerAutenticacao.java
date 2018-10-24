package com.agfgerador.compartilhado.controller;
import org.zkoss.zk.ui.Component;

public abstract class ControllerAutenticacao extends ControllerPadrao  {
	
	private static final long serialVersionUID = 1L;
	
	public void doAfterCompose(Component win,boolean possuiBarraferramenta,String tpTela) throws Exception {
		super.doAfterCompose(win,possuiBarraferramenta,tpTela);
		pageSize=8; 
		onInicio();
	} 
	public void onInicio()
	{	onInicializacaoTela(tipoTela);
	}
	
	
	
}
