 package com.agfgerador.gerenciadorprocessos.controller;

 import java.util.List;
 import java.util.ArrayList;
 import org.zkoss.zul.Paging;
import org.zkoss.zul.Tab;
import org.zkoss.zk.ui.Component;
 import org.zkoss.zk.ui.event.Event;
 import org.zkoss.zul.ListModel;
 import org.zkoss.zul.ListModelList;
 import org.zkoss.zul.Listcell;
 import org.zkoss.zk.ui.event.UploadEvent;
 import org.zkoss.util.media.AMedia;
 import org.zkoss.zul.Listitem;
 import org.zkoss.zul.ListitemRenderer;
 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
 import org.zkoss.zul.Textbox;
 import org.zkoss.zul.Intbox;
 import org.zkoss.zul.Image;
 import org.zkoss.zul.Include;

import com.agfgerador.autenticacao.domain.Perfil;
import com.agfgerador.autenticacao.domain.Permissao;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.autenticacao.service.PermissaoService;
import com.agfgerador.autenticacao.service.UsuarioPerfilService;
import com.agfgerador.compartilhado.controller.ControllerAGF;
import com.agfgerador.compartilhado.controller.ControllerAGFSemId;
 import org.zkoss.zul.Window;
 import org.zkoss.zk.ui.util.Clients;
 import org.zkoss.zul.Messagebox;
 import java.util.Map;
 import org.zkoss.util.media.Media;
 import org.zkforge.json.simple.JSONArray;
 import org.zkoss.zul.A;
 import org.zkoss.zul.Div;
 import java.awt.image.BufferedImage;
 import com.agfgerador.compartilhado.util.AGFImagem;
 import com.agfgerador.compartilhado.controller.ICam;
 import com.agfgerador.compartilhado.controller.IModal;
 import com.agfgerador.compartilhado.util.AGFJs;
 import com.agfgerador.compartilhado.util.AGFModal;
 import org.zkoss.zul.Longbox;
 import org.zkoss.zul.api.Doublebox;
 import org.zkoss.zul.Datebox;
 import org.zkoss.zul.Timebox;
 import org.zkoss.zul.Checkbox;
 import org.zkoss.zul.Combobox;
 import java.text.SimpleDateFormat;
 import org.zkoss.zul.Iframe;
 import com.agfgerador.compartilhado.controller.ControllerAutenticacao;
 import com.agfgerador.compartilhado.util.AGFPaginacao;
 import com.agfgerador.compartilhado.controller.IPaginacao;
 import com.agfgerador.compartilhado.controller.IFilhas;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
 import com.agfgerador.compartilhado.util.AGFComponente;
 import com.agfgerador.gerenciadorprocessos.domain.Processo;
 import com.agfgerador.gerenciadorprocessos.service.ProcessoService;
import com.agfgerador.gerenciadorprocessos.domain.Parecer;
import com.agfgerador.gerenciadorprocessos.domain.Pessoa;
import com.agfgerador.gerenciadorprocessos.service.ParecerService;
import com.agfgerador.gerenciadorprocessos.service.PessoaService;
 import org.zkoss.zul.Listbox;
 import com.agfgerador.compartilhado.util.AGFBandbox;
 import org.zkoss.zul.Bandbox;
 import com.agfgerador.gerenciadorprocessos.domain.Tipopessoa;
 import org.zkoss.zul.Label;
 import org.zkoss.zul.Toolbarbutton;

   public class ProcessoController extends ControllerAGF implements IPaginacao,IFilhas{
    private static final long serialVersionUID = 1L;

     public Processo processo;
     private ProcessoService processoService;
     private int valid;
     private List<ObjetoPadrao> objs = null;
     private List<ObjetoPadraoSemId> objsemid = null;
     private Processo compAux = new Processo();
     private int totalSize = 0;
     private Integer pageSizeBandbox = 5, objnull = 0;
     private UsuarioPerfilService usuarioPerfilService;
     private UsuarioPerfil usuarioperfil = new UsuarioPerfil();
     private Perfil perfil = new Perfil();
     private Tab tabParecer;
     private ParecerService parecerService;
     private Intbox intboxNumprocesso;
     private Textbox textboxObs;
     private A labelPessoa;
     

   
    /////////////Pessoa
    Pessoa pessoa = new Pessoa();
    private Bandbox bandboxPessoa;
    private Longbox longboxPessoa;
    private Listbox listboxPessoa;
    private Paging paginacaoPessoa;
    private Pessoa pessoaBandbox;
    private PessoaService pessoaService;
 
    private Label labelNomePoup;
    private Toolbarbutton toolbarButton;
     private Datebox dateboxDtabertura;

     public void doAfterCompose(Component win) throws Exception {
       win.setAttribute("controller",this);
       win.setAttribute("nomeTela", "Processo");
       win.setAttribute("istemfilha", true);
       super.doAfterCompose(win,true,"all");
       renderizarBandboxPessoa();
       btInformacoes.setVisible(false);
       //perfil = getTriadorFinalizadorGeral();
     }

     public void renderizarListaPrincipal() {
       listbox.setItemRenderer(new ListitemRenderer() {
         public void render(Listitem arg0, Object arg1) throws Exception {
           Processo m = (Processo) arg1;
           Listcell lc = new Listcell();
           lc.appendChild(new Checkbox());
           arg0.appendChild(lc);
           arg0.appendChild(new Listcell(m.getId().toString()));
           try{
           arg0.appendChild(new Listcell(m.getNumprocesso().toString()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell(m.getPessoa().getNome()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
              SimpleDateFormat formatodate = new SimpleDateFormat("E dd/MM/yyyy");
              arg0.appendChild(new Listcell(String.valueOf(formatodate.format(m.getDtabertura()))));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell(m.getObs()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
         }
       });
     }

     public void inicializarRelacoesNpara1() {

}

     /* 
      * BandBox Pessoa - Arthur Freire 
      */ 
     public void onClick$labelPessoa(){
      labelLink("pessoa", null);
     }
     
     public void onOK$longboxPessoa(){
        pessoa = new Pessoa();
        setCompPessoa((Pessoa)AGFBandbox.onOKLongbox(longboxPessoa, pessoaBandbox, pessoaService));
     }
     
     public void onOK$bandboxPessoa(){
        onChange$bandboxPessoa();
     }
     
     public void onChange$bandboxPessoa(){
        pessoa.setNome(bandboxPessoa.getValue());
        inicRecepPessoa();
        objs = pessoaService.filter(pessoa, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = pessoaService.getNumberRecordsFilter(pessoa).intValue();
        AGFBandbox.onChange(bandboxPessoa, listboxPessoa, pessoaService, paginacaoPessoa, pageSizeBandbox, totalSize, objs);
     }
     
     public void onOpen$bandboxPessoa(){
        pessoa.setNome(null);
        listaPessoas();
     }
     
     public void onClick$listboxPessoa(){	
        pessoa.setNome(null);
        setCompPessoa((Pessoa)AGFBandbox.onClickList(bandboxPessoa, pessoaBandbox, listboxPessoa));
     }
    
     public void listaPessoas(){
        inicRecepPessoa();
        objs = pessoaService.filter(pessoa, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = pessoaService.getNumberRecordsFilter(pessoa).intValue();
        AGFBandbox.listaElementos(listboxPessoa, pessoaService, paginacaoPessoa, pageSizeBandbox, totalSize, objs);
     }
    
     public void onPaging$paginacaoPessoa(){
        inicRecepPessoa();
        objs = pessoaService.filter(pessoa, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoPessoa,pageSizeBandbox,paginaAnterior));
        AGFBandbox.onPaging(null, listboxPessoa, pessoaService, paginacaoPessoa, pageSizeBandbox, paginaAnterior, objs);
     }
     
     public void inicRecepPessoa(){
        pessoa.setId(0l);
        pessoa.setTipopessoa(new Tipopessoa());
        pessoa.getTipopessoa().setDescricao(null);
        pessoa.getTipopessoa().setId(0l);
        totalSize = 0;
        objs = new ArrayList<ObjetoPadrao>();
     }
     
     public void setCompPessoa(Pessoa p){
        if(p!=null){	
           pessoaBandbox = p;
           bandboxPessoa.setValue(pessoaBandbox.getNome()); 
           longboxPessoa.setValue(pessoaBandbox.getId());
        }else{
	          pessoaBandbox = null;
	          bandboxPessoa.setValue(" "); 
	          longboxPessoa.setValue(null);
	          bandboxPessoa.setValue(null); 
        }
	    }
     
     public void renderizarBandboxPessoa(){
         listboxPessoa.setItemRenderer(new ListitemRenderer() {
            public void render(Listitem arg0, Object arg1) throws Exception {
               Pessoa m = (Pessoa) arg1;
                arg0.appendChild(new Listcell(m.getId().toString()));
  
                  try{
                     arg0.appendChild(new Listcell(m.getTipopessoa().getDescricao().toString()));
                  }catch(Exception e){
                     arg0.appendChild(new Listcell("")); 
                  }
                  try{
                     Image imagem = new Image();
                     imagem.setContent(AGFImagem.converterByteToBufferedImage(m.getImagem()));
                     Listcell cell = new Listcell();
                     if(imagem.getSrc() == null) {
                        cell.setImageContent(imagem.getContent());
                     }else{
                        cell.setImage(imagem.getSrc());
                     }
                     arg0.appendChild(cell);
                  }catch(Exception e){
                     arg0.appendChild(new Listcell("")); 
                  }
                  try{
                     arg0.appendChild(new Listcell(m.getNome()));
                  }catch(Exception e){
                     arg0.appendChild(new Listcell("")); 
                  }
                  if(m.getTipopessoa().getId()==1) {
       	           try{
       	           arg0.appendChild(new Listcell(m.getCpf()));
       	           }catch(Exception e){
       	              arg0.appendChild(new Listcell("")); 
       	           }
                  }else if(m.getTipopessoa().getId()==2){
       	           try{
       	           arg0.appendChild(new Listcell(m.getCnpj()));
       	           }catch(Exception e){
       	              arg0.appendChild(new Listcell("")); 
       	           }
                  }                
                  try{
                     arg0.appendChild(new Listcell(m.getCidade()));
                  }catch(Exception e){
                     arg0.appendChild(new Listcell("")); 
                  }
                  try{
                     arg0.appendChild(new Listcell(m.getLogradouro()));
                  }catch(Exception e){
                     arg0.appendChild(new Listcell("")); 
                  }
            }
         });
      }
 
     /* 
      * Fim BandBox Banco - Arthur Freire 
      */ 
     

     public void getRelacoesNpara1() {
       if(pessoaBandbox!=null)
          processo.setPessoa(pessoaBandbox);
       else
          processo.setPessoa(null);
       
       processo.setDtabertura(dateboxDtabertura.getValue());
     }
     public void setRelacoesNpara1() {
       setCompPessoa(processo.getPessoa());
       dateboxDtabertura.setValue(processo.getDtabertura());
     }
     public void limparRelacoesNpara1() {
       setCompPessoa(null);
     }

     public boolean isValidForm() {
       boolean ret = true;
       valid = 0;
       if(processo.getPessoa()==null){
         valid = 1;
         ret = false;
       }
       if((processo.getDtabertura() == null)||(processo.getDtabertura().equals(""))){
         valid = 2;
         ret =  false;
       }
       if(processo.getNumprocesso()==null){
         valid = 5;
         ret = false;
       }
       return ret;
     }

     public void onClickbtSalvar() throws InterruptedException {
         Processo comp = (Processo) btSalvar(processo, processoService);
      	if(comp!=null){
           processo = comp;
           triadorFinalizador(false);
        }
        else {
         switch (valid) {
           case 1:
             AGFComponente.showMessage("info","Informe o campo: PESSOA.");
           break;
           case 2:
             AGFComponente.showMessage("info","Informe o campo: DATA ABERTURA.");
           break;
           case 5:
             AGFComponente.showMessage("info","Informe o campo: N. PROCESSO .");
           break;
           }
         }
       }

     public void onOK$auxhead(Event event) {
       compAux = new Processo();
       String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
       if(idAux.equals("")){
         compAux.setId(0l);
       }else {
         compAux.setId(Long.valueOf(idAux));
       }
       compAux.setPessoa(new Pessoa()); 
       compAux.getPessoa().setNome(((Textbox) auxhead.getFellow("filtroPessoa")).getValue());
       compAux.getPessoa().setId(0l);
       compAux.setDtabertura(((Datebox) auxhead.getFellow("filtroDtabertura")).getValue() );
       compAux.setObs(((Textbox) auxhead.getFellow("filtroObs")).getValue());
       compAux.setNumprocesso(((Intbox) auxhead.getFellow("filtroNumprocesso")).getValue());
       totalSize = 0;
       if(perfil!=null && perfil.getId()==8) {  
    	   parecerPendente(compAux, false);
    	   objnull = 1;
       }else {
    	   objs = new ArrayList<ObjetoPadrao>();
    	   objs = processoService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
    	   totalSize = processoService.getNumberRecordsFilter(compAux).intValue();
       }
       paginacao.setActivePage(0);
       AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, processoService,totalSize,objs);
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, processoService, objs,null);
     }

     public void onClickbtNovo() {
       processo = new Processo();
       btNovo();
       controllerParecer.onClickbtLista();
       triadorFinalizador(true);
     }

     public void onClickbtCancelar() {
       processo = (Processo) btCancelar(processo, processoService);
     }

     public void onClickbtRemover() throws InterruptedException {
       btRemover(processo, processoService);
     }

     public void onClickbtLista() {
       btLista();
       perfil = triadorFinalizadorGeral();
	  		 if(perfil!=null && perfil.getId()==8) {  
	  			parecerPendente(null, false);
		  		AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, processoService, totalSize, objs);
		  		objnull = 0;
		     }else {
		    	AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, processoService, null, null);
		     }
	  		objs = null;
     }


     public void onPaging$paginacao() {
       if(perfil!=null && perfil.getId()==8) {
    	   if(objnull==0){
    		   parecerPendente(null, true);
    		   objnull = 0;
    	   }else {
    		   parecerPendente(compAux, true);
    		   objnull = 1;
    	   }    	  
       }else {
	       if(objs!=null){
	          objs = processoService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
	       }
       }    
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, processoService, objs,null);
     }

     public void carregarObjeto(Object obj) {
       processo = (Processo) obj;
       carregarObj(processo);
       controllerParecer.onClickbtLista();
       triadorFinalizador(false);
     }

     public void setObjetoTelaForm() {

     }

     public void atualizaComboLink(String combo) {

     }

     public void onClickbtImprimir() {

     }

     public void onClick$btInformacoes() {

     }

     @Override
     public Boolean removeDependencias(ObjetoPadrao obj) {
       return null;
     }

     @Override
     public Boolean removeDependencias(ObjetoPadraoSemId obj) {
        return null;
     }
 
     public void onClick$aInfodtabertura(){
        labelNomePoup.setValue("DATA ABERTURA");
        toolbarButton.setLabel("Data de abertura do processo.");
     }
 
     /**
     * Filhas
     * @author Arthur Freire
     */ 

     private IncludeParecer controllerParecer = null;
     private Include includeParecer;
     
     @Override
     public void setControllerFilha() {
     controllerParecer = (IncludeParecer)includeParecer.getAttribute("controller");
     }
     
     @Override
     public void setObjetosFilha() {
        objetos = new ArrayList<Object>();
        objetos.add(processo);
        objetos.add(binder);
        objetos.add(perfil);
     controllerParecer.onInicio(objetos);
     }
     
     /*//////////////////////////Abas//////////////////////////*/ 
     public void onClick$tabParecer(){
        controllerParecer.onClickbtLista();
     }
     //////////////////////////////////
     
     private Perfil triadorFinalizadorGeral() {
         UsuarioPerfil usuperf = new UsuarioPerfil();
         usuperf.setId(0l);
         usuperf.setPerfil(new Perfil());
         usuperf.getPerfil().setNome(null);
         usuperf.getPerfil().setId(0l);
         usuperf.setUsuario(usuarioAux);
         usuperf.setAtivo(null);
         usuperf.setAdministrador(null);
        for(ObjetoPadrao temp:usuarioPerfilService.filter(usuperf)) {
      	   System.out.println("perfil: "+((UsuarioPerfil)temp).getPerfil().getNome()+", Nome Usuário: "+((UsuarioPerfil)temp).getUsuario().getNome());
      	   if(((UsuarioPerfil)temp).getPerfil().getId()==7) {
      		   btRemover.setVisible(false);
      		   tabParecer.setLabel("USUÁRIOS QUE REALIZARAM O PARECER");
      		   return ((UsuarioPerfil)temp).getPerfil();
      	   }else
      	   if(((UsuarioPerfil)temp).getPerfil().getId()==8) {
      		   btRemover.setVisible(false);
      		   btSalvar.setVisible(false);
      		   btNovo.setVisible(false);
      		   tabParecer.setLabel("INFORME O SEU PARECER");
      		   intboxNumprocesso.setDisabled(true);
      		   labelPessoa.setDisabled(true);
      		   longboxPessoa.setDisabled(true);
      		   bandboxPessoa.setDisabled(true);
      		   dateboxDtabertura.setDisabled(true);
      		   textboxObs.setDisabled(true);
      		   return ((UsuarioPerfil)temp).getPerfil();
      	   }
         }
        return null;
     }

     private void triadorFinalizador(Boolean b) {
  	   if(perfil!=null) {
	  		 if(perfil.getId() == 7) {
	      	   btSalvar.setVisible(b);
	         }else if(perfil.getId()==8) { 
	           btRemover.setVisible(false);
	      	   btSalvar.setVisible(false);
	      	   btNovo.setVisible(false); 
	    	 }
  	   }
     }
     
     private void parecerPendente(Processo pro, Boolean pag) {
    	 List<ObjetoPadraoSemId> listaparecer = new ArrayList<ObjetoPadraoSemId>();
    	 Parecer parecer = new Parecer();
		 	parecer.setId(0l);
		 	parecer.setUsuario(usuarioAux);
		 	if(pro==null) {
			 	parecer.setProcesso(new Processo());
			 	//parecer.getProcesso().setObs(null);
			 	parecer.getProcesso().setId(0l);
			 	parecer.getProcesso().setPessoa(new Pessoa());
			 	
		 	}else {
		 		parecer.setProcesso(pro);
		 	}
		 	parecer.setDescricao(" ");
		 	parecer.setDtparecer(null);
		 	totalSize = parecerService.getNumberRecordsFilter(parecer).intValue();
		 	objs = new ArrayList<ObjetoPadrao>();
		 	if(totalSize>0) {
		 		if(pag) {
		 			listaparecer = parecerService.filter(parecer,pageSize,AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
		 		}else {
		 			listaparecer = parecerService.filter(parecer,pageSize,0);
		 		}
			 	for(ObjetoPadraoSemId temp: listaparecer) {
			 		objs.add(((Parecer)temp).getProcesso());
			 	}
		 	}
     	}

   }
