package com.agfgerador.compartilhado.controller;

import java.util.List;

import org.zkforge.json.simple.JSONArray;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.A;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.service.ServicePadrao;
import com.agfgerador.compartilhado.util.AGFComponente;

public abstract class IncludePadrao extends GenericForwardComposer {
	
	private static final long serialVersionUID = 1L;
	protected Include barraFerramentasInclude;
	protected Div list, form; 
	protected A btNovo, btSalvar, btCadastrarTodos, btRemover, btLista, btDeletarTodos;
	protected Listbox listbox;
	protected AnnotateDataBinder binder;
	protected JSONArray barraFerramentasButtons;
	protected ServicePadrao service;
	protected ObjetoPadrao objp;
	private boolean removeAll;

	public void doAfterCompose(Component win) throws Exception {
		super.doAfterCompose(win);
		win.setAttribute("controller",this); 
		setController(win); 
		barraFerramentas();
		renderizarListaPrincipal();
	}  

	public abstract void onInicio(List<Object>objetos);

	public void inicio(List<Object>objetos)
	{	barraFerramentas();
	}

	public abstract void renderizarListaPrincipal();

	public abstract void inicializarRelacoesNpara1();

	public abstract void getRelacoesNpara1();

	public abstract void setRelacoesNpara1();
	
	public abstract void limparRelacoesNpara1();

	public abstract boolean isValidForm();
   
	abstract void onClickbtSalvar()throws InterruptedException;

	public ObjetoPadrao btSalvar(ObjetoPadrao obj, ServicePadrao service) throws InterruptedException {
		getRelacoesNpara1();
		if(isValidForm()){
			
			if(obj.getId() == null ){
				
				try{
					try{	
						service.createNew(obj);
						AGFComponente.showMessage("sucesso","O Registro foi adicionado com sucesso.");
					}
					catch (Exception e) {
						AGFComponente.showMessage("erro","Erro ao adicionar o registro.");
					}
				
				} catch(Exception e){
					AGFComponente.showMessage("erro","Erro ao adicionar o registro.");
				}
			}else{
				try{
					try{	
						service.update(obj);
						AGFComponente.showMessage("sucesso","O Registro foi atualizado com sucesso.");
					}
					catch (Exception e) {
						AGFComponente.showMessage("erro","Erro ao atualizar o registro.");
					}
				} catch (Exception e) {
					AGFComponente.showMessage("erro","Erro ao atualizar o registro.");
				}
			}
			changeView(form.getUuid(), list.getUuid());
			onClickbtLista();
			binder.loadAll();
		}
		else
			obj = null;
		
		return obj;
		
		
	}

	public void onDoubleClick$listbox(){  
		if(listbox.getSelectedIndex() != -1){
			menuViewInclude("form");
			Object obj =  listbox.getModel().getElementAt(listbox.getSelectedIndex());  
			carregarObjeto(obj);   
		}
	}

	public abstract void onClickbtNovo();

	public  void btNovo()
	{
		inicializarRelacoesNpara1();
		limparRelacoesNpara1();
		changeView(list.getUuid(), form.getUuid()); 
		menuViewInclude("form");
		binder.loadAll();
	}

	public abstract void onClickbtRemover() throws InterruptedException;
	
	public void btRemover(ObjetoPadrao obj, ServicePadrao s) throws InterruptedException {
		service = s;
		objp = obj;
		if(removeAll){ 
			Messagebox.show("Remover este(s) registro(s)?", "Remove", Messagebox.YES|Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {

				public void onEvent(Event evt) {
					switch (((Integer)evt.getData()).intValue()) {
					case Messagebox.YES:
						try{
							boolean remover = false;
							for (Object item : listbox.getItems()) {
								for (Object lbCell : ((Listitem) item).getChildren()) {
									if(remover == true){
										remover = false;
										Long idLista = Long.valueOf(((Listcell) lbCell).getLabel());
										ObjetoPadrao b= service.findById(idLista);
										service.remove(b);	
									}
									if(((Listcell) lbCell).getChildren().size() == 1){
										Checkbox check = (Checkbox) ((Listcell) lbCell).getChildren().get(0);	
										if(check.isChecked()){		
											remover = true;
										}
									}
								}
							}
							AGFComponente.showMessage("sucesso", "O Registro removido com sucesso.");
							onClickbtLista();			
						} catch (Exception e) {
							AGFComponente.showMessage("erro","Erro ao excluir o registro.");
						}
						break;
					case Messagebox.NO:
						break; 
					}
				}
			}); 
		}else{
			Messagebox.show("Remover este registro?", "Remove", Messagebox.YES|Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {
				public void onEvent(Event evt) {
					switch (((Integer)evt.getData()).intValue()) {
					case Messagebox.YES:
						try{
							service.remove(objp);
							AGFComponente.showMessage("sucesso", "O Registro removido com sucesso.");
							onClickbtLista();	
						} catch (Exception e) {
							AGFComponente.showMessage("erro","Erro ao excluir o registro.");
						}
						break; 
					case Messagebox.NO:
						break; 
					}
				}
			}); 
		}

	}

	public abstract void onClickbtLista(); 
	public void btLista(){
		inicializarRelacoesNpara1();
		changeView(form.getUuid(), list.getUuid());
		menuViewInclude("list");
	}
	

	public abstract void carregarObjeto(Object obj);

	public void carregarObj(Object obj){
		inicializarRelacoesNpara1(); 
		setRelacoesNpara1();
		changeView(list.getUuid(), form.getUuid()); 
		menuViewInclude("form");
		binder.loadAll();
	}

	public abstract void setController(Component win);
	
	public void barraFerramentas(){
		barraFerramentasButtons = new JSONArray();
		
		btNovo = (A) barraFerramentasInclude.getFellow("btNovo");
		btNovo.addForward("onClick", self, "onClickbtNovo");
		barraFerramentasButtons = AGFComponente.addItemBF(barraFerramentasButtons, btNovo, "all");
		
		btSalvar = (A) barraFerramentasInclude.getFellow("btSalvar");
		btSalvar.addForward("onClick", self, "onClickbtSalvar");
		barraFerramentasButtons = AGFComponente.addItemBF(barraFerramentasButtons, btSalvar, "form");
		
		btRemover = (A) barraFerramentasInclude.getFellow("btRemover");
		btRemover.addForward("onClick", self, "onClickbtRemover");
		barraFerramentasButtons = AGFComponente.addItemBF(barraFerramentasButtons, btRemover, "all");

		btLista = (A) barraFerramentasInclude.getFellow("btLista"); 
		btLista.addForward("onClick", self, "onClickbtLista");
		barraFerramentasButtons = AGFComponente.addItemBF(barraFerramentasButtons, btLista, "form");
	

	}
	

	public void changeView(String div1, String div2){ 
		Clients.evalJavaScript("changeView('"+div1+"','"+div2+"')"); 
	}

	public void menuViewInclude(String tipo){ 
		if(tipo.equals("form"))
			removeAll = false;
		else
			removeAll = true;
		Clients.evalJavaScript("menuViewInclude('"+tipo+"', '"+barraFerramentasButtons+"')");
	}

}
