package com.agfgerador.compartilhado.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;

import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.service.UsuarioService;

public class RedirectController  extends GenericForwardComposer {
	
	
	private static final long serialVersionUID = 1L;
	private Textbox login,senha;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
		onInicio();
	}
	
	public void onInicio(){	
		
		String param1=null,param2=null,param3=null;
		Usuario user = null;
		HttpServletRequest request =  (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		if("POST".equalsIgnoreCase(request.getMethod())){
			param1 = request.getParameter("param1");
			param2 = request.getParameter("param2");
			param3 = request.getParameter("param3");
			user = new Usuario();
			if((param1!=null)&&(!param1.equals(""))){	
				user.setId(Long.parseLong(param1));
				user = (Usuario) usuarioService.findById(user.getId());
				if(user!=null){
					login.setValue(user.getLogin());
					senha.setValue(param3+"@"+param2);
					Clients.evalJavaScript("submitForm('form')");
				}
			}
		}
	}
	
	
	

}
