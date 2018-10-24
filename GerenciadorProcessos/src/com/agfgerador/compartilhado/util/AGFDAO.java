package com.agfgerador.compartilhado.util;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
import com.agfgerador.compartilhado.service.ServicePadrao;
import com.agfgerador.compartilhado.service.ServicePadraoSemId;

public class AGFDAO {

	public boolean createnew(ObjetoPadrao obj,ServicePadrao service)
	{ 	boolean ret = true;
		try 
		{	service.createNew(obj);
		} 
		catch (Exception e) {
			AGFComponente.showMessage("erro","Erro ao inserir o registro.");
			System.out.println("erro no create "+e);
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}
	
	public boolean createnewSemId(ObjetoPadraoSemId obj,ServicePadraoSemId service)
	{ 	boolean ret = true;
		try 
		{	service.createNewSemId(obj);
		} 
		catch (Exception e) {
			System.out.println("erro 3");
			AGFComponente.showMessage("erro","Erro ao inserir o registro.");
			System.out.println("erro no create "+e);
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}

	public ObjetoPadrao merge(ObjetoPadrao obj,ServicePadrao service)
	{	try{
		System.out.println("teste onClick$asalvar2222");
			service.update(obj);
			System.out.println("teste onClick$asalvar333");
			obj = service.findById(obj.getId());
		}
		catch(Exception e){
			obj = null;
			AGFComponente.showMessage("erro","Erro ao atualizar o registro.");
			System.out.println("erro no merge "+e);
			e.printStackTrace();
		}
		return obj;
		
	}
	
	public ObjetoPadraoSemId merge(ObjetoPadraoSemId obj,ServicePadraoSemId service)
	{	try{
			service.update(obj);
			obj = service.findById(obj.getId());
		}
		catch(Exception e){
			obj = null;
			AGFComponente.showMessage("erro","Erro ao atualizar o registro.");
			System.out.println("erro no merge "+e);
			e.printStackTrace();
		}
		return obj;
		
	}

	public ObjetoPadrao createOrMerge(ObjetoPadrao obj,ServicePadrao service)
	{
		if(obj.getId()==null)
			createnew(obj, service);
		else
			obj = merge(obj, service);
			
		return obj;
	}

	public boolean remove(ObjetoPadrao obj, ServicePadrao s)
	{	boolean ret = true;
		try{
			s.remove(obj);
		}catch(Exception e){
			AGFComponente.showMessage("erro","Erro ao deletar o registro.");
			System.out.println("erro no remove "+e);
			ret = false;
		}
		
		return ret;
		
	}
	
	public boolean remove(ObjetoPadraoSemId obj, ServicePadraoSemId s)
	{	boolean ret = true;
		try{
			s.remove(obj);
		}catch(Exception e){
			AGFComponente.showMessage("erro","Erro ao deletar o registro.");
			System.out.println("erro no remove "+e);
			e.printStackTrace();
			ret = false;
		}
		
		return ret;
		
	}
	

}
