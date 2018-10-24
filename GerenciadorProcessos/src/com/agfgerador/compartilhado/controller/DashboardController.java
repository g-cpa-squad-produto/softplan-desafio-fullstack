package com.agfgerador.compartilhado.controller;

import org.zkoss.zk.ui.Component;

import com.agfgerador.autenticacao.domain.TipoSistema;

public class DashboardController extends DashboardPadrao {

	private static final long serialVersionUID = 1L;

	@Override
	public void doAfterCompose(Component win) throws Exception {
		doAfterCompose(win, TipoSistema.ProjetoBase, "07", "3.0.00.00","ProjetoBase");
	}

}
