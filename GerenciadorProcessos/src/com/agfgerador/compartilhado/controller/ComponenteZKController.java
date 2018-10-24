package com.agfgerador.compartilhado.controller;

import java.util.List;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;

import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.domain.Permissao;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;


public class ComponenteZKController {

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

	public ObjetoPadrao onDoubleClick$listbox(Listbox listbox)
	{	ObjetoPadrao obj = null;
		if(listbox.getSelectedIndex() != -1){
			 obj =  (ObjetoPadrao) listbox.getModel().getElementAt(listbox.getSelectedIndex());  
		}
		return obj;
	}

	public  ObjetoPadrao  onClickbtProximo(Listbox listbox)
	{	ObjetoPadrao obj = null;
		if(listbox.getSelectedIndex() != -1){
			if(listbox.getSelectedIndex() < (listbox.getModel().getSize() - 1)){
				listbox.setSelectedIndex(listbox.getSelectedIndex() + 1);
				obj =  (ObjetoPadrao) listbox.getModel().getElementAt(listbox.getSelectedIndex()); 
				
			}
		}
		return obj;
	}

	public ObjetoPadrao onClickbtAnterior(Listbox listbox)
	{	ObjetoPadrao obj = null;
		if(listbox.getSelectedIndex() != -1){
			if(listbox.getSelectedIndex() > 0){
				listbox.setSelectedIndex(listbox.getSelectedIndex() - 1);
				obj = (ObjetoPadrao) listbox.getModel().getElementAt(listbox.getSelectedIndex());
			}
		}
		return obj;
	}
}
