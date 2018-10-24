package com.agfgerador.compartilhado.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Image;
import org.zkoss.zul.Window;

import com.agfgerador.compartilhado.util.AGFImagem;


public class CamController extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image previa;
	private Window windowCam;
	
	private ICam controller;
	
	@Override
	public void doAfterCompose(Component win) throws Exception {
		win.setAttribute("controller", this);
		super.doAfterCompose(win);	
		controller = (ICam) execution.getArg().get("controller");
		Clients.evalJavaScript("startWeb()");
	}

	public void onClick$saveImg(){
		controller.setImageWebCam(previa,AGFImagem.base64StringToImg(previa.getSrc()));
		windowCam.onClose();
	}
	
	public void onClose(){ 
		Clients.evalJavaScript("Webcam.reset();");
	}
	
}