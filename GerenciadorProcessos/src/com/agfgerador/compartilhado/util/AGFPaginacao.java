package com.agfgerador.compartilhado.util;

import java.util.List;





import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
import com.agfgerador.compartilhado.service.ServicePadrao;
import com.agfgerador.compartilhado.service.ServicePadraoSemId;

public class AGFPaginacao {

	public static void btListaPaginacao(Listbox listbox,Paging paginacao,int pageSize, ServicePadrao service,Integer totalSize,List<ObjetoPadrao> objs)
	{	Integer tSize;
		List<ObjetoPadrao> objetos;
		if(listbox.getPagingChild()!=null){
			listbox.getPagingChild().setVisible(false);
		}
		paginacao.setActivePage(0);
		
		if(totalSize!=null){
			tSize = totalSize;
		}else{
			tSize = Integer.parseInt(service.getNumberRecords().toString());
		}
		paginacao.setTotalSize(tSize);
		paginacao.setPageSize(pageSize);
		paginacao.setDetailed(true);
		if(paginacao.getTotalSize() > pageSize){
			paginacao.setVisible(true);
		}else{
			paginacao.setVisible(false);
		}
		if(objs!=null){
			objetos = objs;
		}else{
			objetos = service.findAll(pageSize, 0);
		}
		ListModel ls = new ListModelList(objetos);		
		listbox.setModel(ls);
	}
	public static void btListaPaginacao(Listbox listbox,Paging paginacao,int pageSize, ServicePadraoSemId service,Integer totalSize,List<ObjetoPadraoSemId> objs)
	{	Integer tSize;
		List<ObjetoPadraoSemId> objetos;
		if(listbox.getPagingChild()!=null){
			listbox.getPagingChild().setVisible(false);
		}
		paginacao.setActivePage(0);
		
		if(totalSize!=null){
			tSize = totalSize;
		}else{
			tSize = Integer.parseInt(service.getNumberRecords().toString());
		}
		paginacao.setTotalSize(tSize);
		paginacao.setPageSize(pageSize);
		paginacao.setDetailed(true);
		if(paginacao.getTotalSize() > pageSize){
			paginacao.setVisible(true);
		}else{
			paginacao.setVisible(false);
		}
		if(objs!=null){
			objetos = objs;
		}else{
			objetos = service.findAll(pageSize, 0);
		}
		ListModel ls = new ListModelList(objetos);		
		listbox.setModel(ls);
	}

	public static void paginacao(Listbox listbox,Paging paginacao,int pageSize,int paginaAnterior,ServicePadrao service,List<ObjetoPadrao> objs,String value){
		List<ObjetoPadrao> lista;
		
		paginacao.setDetailed(true);
		
		if(listbox.getPagingChild() != null){
			listbox.getPagingChild().setVisible(false);
		}
		paginacao.setVisible(true);
		
		if(objs==null){
			if((value!=null)&&(!value.equals(""))){
				lista = service.findAll(value,pageSize, getPagePaginacao(paginacao,pageSize,paginaAnterior));
			}else{
				lista = service.findAll(pageSize, getPagePaginacao(paginacao,pageSize,paginaAnterior));
			}
		}else{
			lista = objs;
		}
		ListModel ls = new ListModelList(lista);
		listbox.setModel(ls);
		paginaAnterior = paginacao.getActivePage();
	}
	
	public static void paginacao(Listbox listbox,Paging paginacao,int pageSize,int paginaAnterior,ServicePadraoSemId service,List<ObjetoPadraoSemId> objs,String value){
		List<ObjetoPadraoSemId> lista;
		
		paginacao.setDetailed(true);
		
		if(listbox.getPagingChild() != null){
			listbox.getPagingChild().setVisible(false);
		}
		paginacao.setVisible(true);
		
		if(objs==null){
			if((value!=null)&&(!value.equals(""))){
				lista = service.findAll(value,pageSize, getPagePaginacao(paginacao,pageSize,paginaAnterior));
			}else{
				lista = service.findAll(pageSize, getPagePaginacao(paginacao,pageSize,paginaAnterior));
			}
		}else{
			lista = objs;
		}
		ListModel ls = new ListModelList(lista);
		listbox.setModel(ls);
		paginaAnterior = paginacao.getActivePage();
	}

	public static Integer getPagePaginacao(Paging paginacao,int pageSize,int paginaAnterior) {
		Integer page;
		if(paginaAnterior<paginacao.getActivePage()){
			if(paginacao.getActivePage() < paginacao.getPageCount())
				page = paginacao.getActivePage()*pageSize;
			else
				page = paginacao.getPageCount()*pageSize;
		}else{
			if (paginacao.getActivePage() == 0)
				page = 0;
			else
				page = paginacao.getActivePage()*pageSize;
		}
		return page;
	}


}
