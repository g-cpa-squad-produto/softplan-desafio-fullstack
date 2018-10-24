package com.agfgerador.compartilhado.controller;

import java.util.List;

import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;

import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.Permissao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;

public class ComponenteZKControllerSemId {

	public void registrarPermissaoPrincipal(Componente componente,Include barraFerramentas,List<Permissao> permissaoList){
		if(componente!=null)
		{	boolean temPermissao = false;
			for(Permissao p : permissaoList){
				if(p.getComponente().getId().toString().equals(componente.getId().toString())){
					temPermissao = true;
					if(p.isExcluir() == false){ 
						barraFerramentas.getFellow("btRemover").setVisible(false); 
					}
					if(p.isSomenteLeitura() == true){
						barraFerramentas.getFellow("btSalvar").setVisible(false); 
					}
					break;
				}
			}
			if(!temPermissao){
				barraFerramentas.getFellow("btRemover").setVisible(false); 
				barraFerramentas.getFellow("btNovo").setVisible(false); 
				barraFerramentas.getFellow("btSalvar").setVisible(false); 
			}
		}
	}

	public ObjetoPadraoSemId onDoubleClick$listbox(Listbox listbox)
	{	ObjetoPadraoSemId obj = null;
		if(listbox.getSelectedIndex() != -1){
			 obj =  (ObjetoPadraoSemId) listbox.getModel().getElementAt(listbox.getSelectedIndex());  
		}
		return obj;
	}

	public  ObjetoPadraoSemId  onClickbtProximo(Listbox listbox)
	{	ObjetoPadraoSemId obj = null;
		if(listbox.getSelectedIndex() != -1){
			if(listbox.getSelectedIndex() < (listbox.getModel().getSize() - 1)){
				listbox.setSelectedIndex(listbox.getSelectedIndex() + 1);
				obj =  (ObjetoPadraoSemId) listbox.getModel().getElementAt(listbox.getSelectedIndex()); 
				
			}
		}
		return obj;
	}

	public ObjetoPadraoSemId onClickbtAnterior(Listbox listbox)
	{	ObjetoPadraoSemId obj = null;
		if(listbox.getSelectedIndex() != -1){
			if(listbox.getSelectedIndex() > 0){
				listbox.setSelectedIndex(listbox.getSelectedIndex() - 1);
				obj = (ObjetoPadraoSemId) listbox.getModel().getElementAt(listbox.getSelectedIndex());
			}
		}
		return obj;
	}
}
