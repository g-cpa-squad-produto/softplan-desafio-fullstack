
package com.agfgerador.compartilhado.controller;


import org.zkoss.zk.ui.Component;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox; 
import org.zkoss.zul.Window;

import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.service.UsuarioService;
import com.agfgerador.compartilhado.util.AGFComponente;
import com.agfgerador.compartilhado.util.AGFUtil;



public class AlterarSenhaController extends GenericForwardComposer{

	private static final long serialVersionUID = 1L;
	private Window windowAlterarSenha;
	private Textbox senhaAtual;
	private Textbox senhaNova;
	private Textbox repetirSenha; 
	private int valid;
	private Usuario usuarioAux;
	private UsuarioService usuarioService;
	
	@Override
	public void doAfterCompose(Component win) throws Exception {
		win.setAttribute("controller", this);
		super.doAfterCompose(win);	
		usuarioAux = (Usuario)session.getAttribute("usuarioLogado");
	}
	

	public boolean isValidForm() {
		boolean ret=true;
		if(!usuarioAux.getSenha().equals(AGFUtil.MD5(senhaAtual.getValue()))) {	
			valid = 1;
			ret = false;
		}
		
		else if(!senhaNova.getValue().equals(repetirSenha.getValue())) {
			valid = 2;
			ret = false;
		}
		
		else if(senhaNova.getValue().length()<6) {	
			valid = 3;
			ret=  false;
		} 
		return ret; 
	}
	public void onClick$btAlterar(){
		if(isValidForm()){	
			usuarioAux.setSenha(AGFUtil.MD5(senhaNova.getValue()));
			usuarioService.update(usuarioAux);
			AGFComponente.showMessage("sucesso","O Registro foi atualizado com sucesso."); 
		}else{
			switch (valid) {
			case 1:
				AGFComponente.showMessage("info","A senha atual está Incorreta.");
				break;
			case 2:
				AGFComponente.showMessage("info","A nova senha não conferi com a sua confirmação.");
				break;	
			case 3:
				AGFComponente.showMessage("info","A nova senha não pode conter menos que 6 caracteres.");
				break;	
			default:
			
			} 
		}	
	} 
	public void onCancel(){ 
		windowAlterarSenha.onClose();
	}


	
}
