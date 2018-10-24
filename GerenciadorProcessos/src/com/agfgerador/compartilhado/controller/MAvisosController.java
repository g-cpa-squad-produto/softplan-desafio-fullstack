package com.agfgerador.compartilhado.controller;

import java.io.File;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.compartilhado.service.SqlJdbcsService;
import com.agfgerador.compartilhado.util.AGFComponente;

public class MAvisosController extends GenericForwardComposer {
	private static final long serialVersionUID = 1L;
	
	private Window windowMAviso;
	private Label descricao,nomedoc;
	private Div divdoc;
	private Usuario usuario;
	protected SqlJdbcsService sqlJdbcService;
	@Override
	public void doAfterCompose(Component win) throws Exception {
		win.setAttribute("controller", this);
		super.doAfterCompose(win);	
	}

	public void setObjetos(Usuario user)
	{	usuario = user;
		descricao.setValue(user.getAviso().getDescricao());
		windowMAviso.setTitle(user.getAviso().getTitulo());
		if((user.getAviso().getNomedoc()!=null)&&(user.getAviso().getUrldoc()!=null)){
			divdoc.setVisible(true);
			nomedoc.setValue(user.getAviso().getNomedoc());
		}
	}
	
	public void onClose(){ 
		sqlJdbcService.executeSql("UPDATE `aut_usuario` a SET a.aviso_id = NULL WHERE a.id= "+usuario.getId());
	}
	
	public void onClick$btDow()
	{
		AGFComponente.fileDownload(new File(usuario.getAviso().getUrldoc()), null);
	}
}
