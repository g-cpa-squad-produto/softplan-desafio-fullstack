package com.agfgerador.compartilhado.util;

import java.util.List;

import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
import com.agfgerador.compartilhado.service.ServicePadrao;
import com.agfgerador.compartilhado.service.ServicePadraoSemId;

public class AGFBandbox {
	public static ObjetoPadrao onOKLongbox(Longbox longbox,ObjetoPadrao obj,ServicePadrao service){
		obj = null;
		if(longbox.getValue()!=null)
			obj = service.findById(longbox.getValue());
		return obj;
	}
	public static ObjetoPadraoSemId onOKLongbox(Longbox longbox,ObjetoPadraoSemId obj,ServicePadraoSemId service){
		obj = null;
		if(longbox.getValue()!=null)
			obj = service.findById(longbox.getValue());
		return obj;
	}

	public static void onChange(Bandbox bandbox,Listbox listbox,ServicePadrao service,Paging paginacao,int pageSize,Integer totalSize,List<ObjetoPadrao> objs){   
		if((bandbox.getValue()!=null)&&(!bandbox.getValue().equals(""))){
			if((totalSize==null)&&(objs==null)){
				totalSize = Integer.parseInt(service.getNumberRecords(bandbox.getValue()).toString());
				objs = service.findAll(bandbox.getValue(), pageSize, 0);
			}
			bandbox.open();
			AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, service, totalSize, objs);
		}
	}
	
	public static void onChange(Bandbox bandbox,Listbox listbox,ServicePadraoSemId service,Paging paginacao,int pageSize,Integer totalSize,List<ObjetoPadraoSemId> objs){   
		if((bandbox.getValue()!=null)&&(!bandbox.getValue().equals(""))){
			if((totalSize==null)&&(objs==null)){
				totalSize = Integer.parseInt(service.getNumberRecords(bandbox.getValue()).toString());
				objs = service.findAll(bandbox.getValue(), pageSize, 0);
			}
			bandbox.open();
			AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, service, totalSize, objs);
		}
	}

	public static ObjetoPadrao onClickList(Bandbox bandbox,ObjetoPadrao obj,Listbox listbox){	
		obj =null;
		if(listbox.getSelectedIndex()!= -1){
			obj =  (ObjetoPadrao) listbox.getModel().getElementAt(listbox.getSelectedIndex()); 
			bandbox.close();
		}
		return obj;
	}
	
	public static ObjetoPadraoSemId onClickList(Bandbox bandbox,ObjetoPadraoSemId obj,Listbox listbox){	
		obj =null;
		if(listbox.getSelectedIndex()!= -1){
			obj =  (ObjetoPadraoSemId) listbox.getModel().getElementAt(listbox.getSelectedIndex()); 
			bandbox.close();
		}
		return obj;
	}

	public static void listaElementos(Listbox listbox,ServicePadrao service,Paging paginacao,int pageSize,Integer totalSize,List<ObjetoPadrao> objs){
		AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, service, totalSize, objs);
	}
	public static void listaElementos(Listbox listbox,ServicePadraoSemId service,Paging paginacao,int pageSize,Integer totalSize,List<ObjetoPadraoSemId> objs){
		AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, service, totalSize, objs);
	}	

	public static void onPaging(String value,Listbox listbox,ServicePadrao service,Paging paginacao,int pageSize,int paginaAnterior,List<ObjetoPadrao> objs){	
		AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, service, objs,value);
	}
	
	public static void onPaging(String value,Listbox listbox,ServicePadraoSemId service,Paging paginacao,int pageSize,int paginaAnterior,List<ObjetoPadraoSemId> objs){	
		AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, service, objs,value);
	}
	
	
	
}
