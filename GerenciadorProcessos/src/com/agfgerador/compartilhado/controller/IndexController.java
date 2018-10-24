package com.agfgerador.compartilhado.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;

import com.agfgerador.autenticacao.domain.Email;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.service.EmailService;
import com.agfgerador.autenticacao.service.UsuarioService;
import com.agfgerador.compartilhado.util.AGFUtil;

public class IndexController extends GenericForwardComposer{
	private static final long serialVersionUID = 1L;
	int valid;
	private Textbox loginRecovery;
	private UsuarioService usuarioService;
	private EmailService emailService;
	
	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
		win.setAttribute("controller",this);
		Clients.evalJavaScript("ieWarning();");
	}		
	public boolean isValid()
	{	boolean ret = true;
		valid =0;
		if((loginRecovery.getValue()==null)||(loginRecovery.getValue().equals("")))
		{	valid = 1;
			ret = false;
		}
		return ret;
	}
	public void onClick$btResetar(){
		if(isValid())
		{	Usuario user = usuarioService.findByLogin(loginRecovery.getValue());
			if(user!=null)
			{
				if(enviaEmail(user))
					Clients.evalJavaScript("setMensagem('success', 'Senha resetada com sucesso. Verifique seu email.')"); 
				else
					Clients.evalJavaScript("setMensagem('danger', 'Ocorreu um erro, tente mais tarde.')");  
			}
			else
				Clients.evalJavaScript("setMensagem('danger', 'Login não encontrado.')");   
			
		}
		switch (valid) {
		case 1:
			Clients.evalJavaScript("setMensagem('info', 'informe o login.')");
			break;
			
		default:
			break;
		}
	}
	
	public boolean enviaEmail(Usuario u)
	{	boolean ret = true;
		Email email = (Email)emailService.findById((long)1);
		if(email!=null)
		{	try{
				String senha = AGFUtil.geraAlfanumerico(6);
				ret = atualizaUsuario(u, senha);
				if(ret)
					AGFUtil.enviaEmail(email, u, "Nova Senha AGF - Gerador!", "Alteração de Senha", msgEmail(senha));
			}catch (Exception e){
				System.out.println("erro ao enviar o email. "+e);
				ret = false;
			}
			
		}
		return ret;
		
	}
	
	public boolean atualizaUsuario(Usuario u,String senha)
	{	boolean ret = true;
		try{  u.setSenha(AGFUtil.MD5(senha));
			  usuarioService.updateSemLog(u);
		}
		catch(Exception e){
			System.out.println("erro ao atualizar o usuário "+e);
			ret = false;
			
		}
		return ret;
		
	}
	
	
	public String msgEmail(String senha)
	{
		String msg ="";
		msg =   "<html>\n" +
	    		"<head>\n" + 
	    		"<h3>Senha Alterada</h3>\n" +
	    		"</head>\n" +
	    		"<body>\n" +
	    		"<!-- <font size='7'/>\n-->" +
				"<br/>\n" +
				"Olá, foi gerada uma nova senha: "+senha+ ".\n" +
				"<br/>\n" +
				"Por motivo de segurança, na próxima vez que acessar o sistema, altere a sua senha.\n" +
				"<br/>\n" +
				"<br/>\n" +
				"<br/>\n" +
				"___________________________________________________________________________________\n" +
				"<br/>\n" +
				"Este é um email automatizado e sua resposta não será recebida.\n" +
				"<br/>\n" +
				"Para mais informações, contate o suporte de atendimento ao contribuinte. \n" +
				"<br/>\n" +
				"</html>"; 
		
		return msg;
	}

}
