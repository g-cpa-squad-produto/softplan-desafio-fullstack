package com.agfgerador.compartilhado.controller;

import org.zkoss.zk.ui.Component;

public abstract class ControllerAGFSemId extends ControllerPadraoSemId {

	private static final long serialVersionUID = 1L;
	
	public void doAfterCompose(Component win,boolean possuiBarraferramenta,String tpTela) throws Exception {
		super.doAfterCompose(win,possuiBarraferramenta,tpTela);
		onInicio();
	} 
	public void onInicio(){	
		onInicializacaoTela(tipoTela);
	}
	

}
