package com.agfgerador.compartilhado.util;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zul.Window;

public class AGFModal {

	public void createModal(String url,String position,String title,String with,String heigth,Window window) throws Exception {
		try {  
			Map<String, Object> arg = new HashMap<String, Object>();
            arg.put("ismodal", true);
            arg.put("controller", window.getAttribute("controller"));
            arg.put("controllerPai", window.getRoot().getAttribute("controller"));
			Window modal = (Window) Executions.createComponents(url,null, arg);
			modal.setClosable(true);
			if(position!=null)
				modal.setPosition(position);
			modal.setTitle(title);
			if(with!=null)
				modal.setWidth(with);
			if(heigth!=null)
				modal.setHeight("95%");
			modal.setSclass("shortcut-modal");
			modal.doModal();
			

		} catch (SuspendNotAllowedException e) {
			e.printStackTrace();
		}
	}

	

}
