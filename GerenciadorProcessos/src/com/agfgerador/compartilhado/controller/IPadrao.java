package com.agfgerador.compartilhado.controller;

import org.zkoss.zk.ui.event.Event;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;

public interface IPadrao{

	void onInicio();

	void renderizarListaPrincipal();

	void inicializarRelacoesNpara1();

	void getRelacoesNpara1();

	void setRelacoesNpara1();

	void limparRelacoesNpara1();

	boolean isValidForm();
	void onClickbtSalvar()throws InterruptedException;

	 void onOK$auxhead(Event event);

	void onClickbtNovo();

	void onClickbtCancelar();

	void onClickbtRemover() throws InterruptedException;

	Boolean removeDependencias(ObjetoPadrao obj);
	Boolean removeDependencias(ObjetoPadraoSemId obj);

	void onClickbtLista(); 

	void carregarObjeto(Object obj);

}
