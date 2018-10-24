 package com.agfgerador.autenticacao.controller;

 import java.util.List;
 import java.util.ArrayList;
 import org.zkoss.zul.Paging;
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
import com.agfgerador.compartilhado.util.AGFUtil;
import com.agfgerador.compartilhado.controller.IPaginacao;
 import com.agfgerador.compartilhado.controller.IFilhas;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
 import com.agfgerador.compartilhado.util.AGFComponente;
 import com.agfgerador.autenticacao.domain.Componente;
 import com.agfgerador.autenticacao.service.ComponenteService;
 import org.zkoss.zul.Label;
 import org.zkoss.zul.Toolbarbutton;
 import com.agfgerador.autenticacao.domain.Componente;
 import com.agfgerador.autenticacao.service.ComponenteService;
 import org.zkoss.zul.Listbox;
 import com.agfgerador.compartilhado.util.AGFBandbox;
 import org.zkoss.zul.Bandbox;
 import com.agfgerador.autenticacao.domain.Tipomenu;
 import com.agfgerador.autenticacao.domain.Tipomenu;
 import com.agfgerador.autenticacao.service.TipomenuService;
 import com.agfgerador.autenticacao.domain.Icones;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.service.IconesService;

   public class ComponenteController extends ControllerAGF implements IPaginacao,IFilhas{
    private static final long serialVersionUID = 1L;

     public Componente componente;
     private ComponenteService componenteService;
     private int valid;
     private List<ObjetoPadrao> objs = null;
     private List<ObjetoPadraoSemId> objsemid = null;
     private Componente compAux = new Componente();
     private int totalSize = 0;
     private Integer pageSizeBandbox = 5;
     private Div divtextboxDescricao,divtextboxNome,divtextboxUrl,divtextboxMenu,divtextboxInformacao,divbandboxComponentemenu,
     divbandboxIcone;
     
     private Label labelNomePoup;
     private Toolbarbutton toolbarButton;
   
    /////////////Componente
    Componente componentemenu = new Componente();
    private Bandbox bandboxComponentemenu;
    private Longbox longboxComponentemenu;
    private Listbox listboxComponentemenu;
    private Paging paginacaoComponentemenu;
    private Componente componenteBandbox;
 
    /////////////Tipomenu
    Tipomenu tipomenu = new Tipomenu();
    private Bandbox bandboxTipomenu;
    private Longbox longboxTipomenu;
    private Listbox listboxTipomenu;
    private Paging paginacaoTipomenu;
    private Tipomenu tipomenuBandbox;
    private TipomenuService tipomenuService; 
   
    /////////////Icones
    Icones icone = new Icones();
    private Bandbox bandboxIcone;
    private Longbox longboxIcone;
    private Listbox listboxIcone;
    private Paging paginacaoIcone;
    private Icones iconesBandbox;
    private IconesService iconesService;

     public void doAfterCompose(Component win) throws Exception {
       win.setAttribute("controller",this);
       win.setAttribute("nomeTela", "Componente");
       win.setAttribute("istemfilha", true);
       super.doAfterCompose(win,true,"all");
       renderizarBandboxComponentemenu();
       renderizarBandboxTipomenu();
       renderizarBandboxIcone();
     }

     public void renderizarListaPrincipal() {
       listbox.setItemRenderer(new ListitemRenderer() {
         public void render(Listitem arg0, Object arg1) throws Exception {
           Componente m = (Componente) arg1;
           Listcell lc = new Listcell();
           lc.appendChild(new Checkbox());
           arg0.appendChild(lc);
           arg0.appendChild(new Listcell(m.getId().toString()));
           try{
           arg0.appendChild(new Listcell(m.getDescricao()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell(m.getInformacao()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell(m.getMenu()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell(m.getTipomenu().getNome()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
         }
       });
     }

     public void inicializarRelacoesNpara1() {

     }

     /* 
      * BandBox Componente - Arthur Freire 
      */ 
     public void onClick$labelComponentemenu(){
      labelLink("componente", null);
     }
     
     public void onOK$longboxComponentemenu(){
        componentemenu = new Componente();
        setCompComponentemenu((Componente)AGFBandbox.onOKLongbox(longboxComponentemenu, componenteBandbox, componenteService));
     }
     
     public void onOK$bandboxComponentemenu(){
        onChange$bandboxComponentemenu();
     }
     
     public void onChange$bandboxComponentemenu(){
        componentemenu.setDescricao(bandboxComponentemenu.getValue());
        inicRecepComponentemenu();
        objs = componenteService.filtercombobox(componentemenu, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = componenteService.getNumberRecordsFiltercombobox(componentemenu).intValue();
        AGFBandbox.onChange(bandboxComponentemenu, listboxComponentemenu, componenteService, paginacaoComponentemenu, pageSizeBandbox, totalSize, objs);
     }
     
     public void onOpen$bandboxComponentemenu(){
        componentemenu.setDescricao(null);
        listaComponentemenus();
     }
     
     public void onClick$listboxComponentemenu(){	
        componentemenu.setDescricao(null);
        setCompComponentemenu((Componente)AGFBandbox.onClickList(bandboxComponentemenu, componenteBandbox, listboxComponentemenu));
     }
    
     public void listaComponentemenus(){
        inicRecepComponentemenu();
        objs = componenteService.filtercombobox(componentemenu, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = componenteService.getNumberRecordsFiltercombobox(componentemenu).intValue();
        AGFBandbox.listaElementos(listboxComponentemenu, componenteService, paginacaoComponentemenu, pageSizeBandbox, totalSize, objs);
     }
    
     public void onPaging$paginacaoComponentemenu(){
        inicRecepComponentemenu();
        objs = componenteService.filtercombobox(componentemenu, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoComponentemenu,pageSizeBandbox,paginaAnterior));
        AGFBandbox.onPaging(null, listboxComponentemenu, componenteService, paginacaoComponentemenu, pageSizeBandbox, paginaAnterior, objs);
     }
     
     public void inicRecepComponentemenu(){
        componentemenu.setId(0l);
        componentemenu.setTipomenu(new Tipomenu());
        componentemenu.getTipomenu().setNome(null);
        componentemenu.getTipomenu().setId(0l);
        componentemenu.setMenu("");
        componentemenu.setNome("");
        totalSize = 0;
        objs = new ArrayList<ObjetoPadrao>();
     }
     
     public void setCompComponentemenu(Componente p){
        if(p!=null){	
        	componenteBandbox = p;
        	System.out.println("nome comp: "+componenteBandbox.getDescricao()+", id: "+componenteBandbox.getId());
           bandboxComponentemenu.setValue(componenteBandbox.getDescricao()); 
           longboxComponentemenu.setValue(componenteBandbox.getId());
        }else{
	          componenteBandbox = null;
	          bandboxComponentemenu.setValue(" "); 
	          longboxComponentemenu.setValue(null);
	          bandboxComponentemenu.setValue(null); 
        }
	    }
     
     public void renderizarBandboxComponentemenu(){
        listboxComponentemenu.setItemRenderer(new ListitemRenderer() {
           public void render(Listitem arg0, Object arg1) throws Exception {
              Componente m = (Componente) arg1;
               arg0.appendChild(new Listcell(m.getId().toString()));
 
                 try{
                    arg0.appendChild(new Listcell(m.getDescricao()));
                 }catch(Exception e){
                    arg0.appendChild(new Listcell("")); 
                 }
                 try{
                    arg0.appendChild(new Listcell(m.getTipomenu().getNome().toString()));
                 }catch(Exception e){
                    arg0.appendChild(new Listcell("")); 
                 }
           }
        });
     }
 
     /* 
      * Fim BandBox Banco - Arthur Freire 
      */ 
     
     /* 
      * BandBox Tipomenu - Arthur Freire 
      */ 
     public void onClick$labelTipomenu(){
      labelLink("tipomenu", null);
     }
     
     public void onOK$longboxTipomenu(){
        tipomenu = new Tipomenu();
        setCompTipomenu((Tipomenu)AGFBandbox.onOKLongbox(longboxTipomenu, tipomenuBandbox, tipomenuService));
        camposVisivel();
     }
     
     public void onOK$bandboxTipomenu(){
        onChange$bandboxTipomenu();
     }
     
     public void onChange$bandboxTipomenu(){
        tipomenu.setNome(bandboxTipomenu.getValue());
        inicRecepTipomenu();
        objs = tipomenuService.filter(tipomenu, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = tipomenuService.getNumberRecordsFilter(tipomenu).intValue();
        AGFBandbox.onChange(bandboxTipomenu, listboxTipomenu, tipomenuService, paginacaoTipomenu, pageSizeBandbox, totalSize, objs);
     }
     
     public void onOpen$bandboxTipomenu(){
        tipomenu.setNome(null);
        listaTipomenus();
     }
     
     public void onClick$listboxTipomenu(){	
        tipomenu.setNome(null);
        setCompTipomenu((Tipomenu)AGFBandbox.onClickList(bandboxTipomenu, tipomenuBandbox, listboxTipomenu));
        camposVisivel();
     }
    
     public void listaTipomenus(){
        inicRecepTipomenu();
        objs = tipomenuService.filter(tipomenu, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = tipomenuService.getNumberRecordsFilter(tipomenu).intValue();
        AGFBandbox.listaElementos(listboxTipomenu, tipomenuService, paginacaoTipomenu, pageSizeBandbox, totalSize, objs);
     }
    
     public void onPaging$paginacaoTipomenu(){
        inicRecepTipomenu();
        objs = tipomenuService.filter(tipomenu, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoTipomenu,pageSizeBandbox,paginaAnterior));
        AGFBandbox.onPaging(null, listboxTipomenu, tipomenuService, paginacaoTipomenu, pageSizeBandbox, paginaAnterior, objs);
     }
     
     public void inicRecepTipomenu(){
        tipomenu.setId(0l);
        totalSize = 0;
        objs = new ArrayList<ObjetoPadrao>();
     }
     
     public void setCompTipomenu(Tipomenu p){
        if(p!=null){	
           tipomenuBandbox = p;
           bandboxTipomenu.setValue(tipomenuBandbox.getNome()); 
           longboxTipomenu.setValue(tipomenuBandbox.getId());
        }else{
	          tipomenuBandbox = null;
	          bandboxTipomenu.setValue(" "); 
	          longboxTipomenu.setValue(null);
	          bandboxTipomenu.setValue(null); 
        }
	    }
     
     public void renderizarBandboxTipomenu(){
        listboxTipomenu.setItemRenderer(new ListitemRenderer() {
           public void render(Listitem arg0, Object arg1) throws Exception {
              Tipomenu m = (Tipomenu) arg1;
               arg0.appendChild(new Listcell(m.getId().toString()));
 
                 try{
                    arg0.appendChild(new Listcell(m.getNome()));
                 }catch(Exception e){
                    arg0.appendChild(new Listcell("")); 
                 }
           }
        });
     }
 
     /* 
      * Fim BandBox Banco - Arthur Freire 
      */ 
     
     /* 
      * BandBox Icones - Arthur Freire 
      */ 
     public void onClick$labelIcone(){
      labelLink("icones", null);
     }
     
     public void onOK$longboxIcone(){
        icone = new Icones();
        setCompIcone((Icones)AGFBandbox.onOKLongbox(longboxIcone, iconesBandbox, iconesService));
     }
     
     public void onOK$bandboxIcone(){
        onChange$bandboxIcone();
     }
     
     public void onChange$bandboxIcone(){
        icone.setNome(bandboxIcone.getValue());
        inicRecepIcone();
        objs = iconesService.filter(icone, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = iconesService.getNumberRecordsFilter(icone).intValue();
        AGFBandbox.onChange(bandboxIcone, listboxIcone, iconesService, paginacaoIcone, pageSizeBandbox, totalSize, objs);
     }
     
     public void onOpen$bandboxIcone(){
        icone.setNome(null);
        listaIcones();
     }
     
     public void onClick$listboxIcone(){	
        icone.setNome(null);
        setCompIcone((Icones)AGFBandbox.onClickList(bandboxIcone, iconesBandbox, listboxIcone));
     }
    
     public void listaIcones(){
        inicRecepIcone();
        objs = iconesService.filter(icone, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = iconesService.getNumberRecordsFilter(icone).intValue();
        AGFBandbox.listaElementos(listboxIcone, iconesService, paginacaoIcone, pageSizeBandbox, totalSize, objs);
     }
    
     public void onPaging$paginacaoIcone(){
        inicRecepIcone();
        objs = iconesService.filter(icone, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoIcone,pageSizeBandbox,paginaAnterior));
        AGFBandbox.onPaging(null, listboxIcone, iconesService, paginacaoIcone, pageSizeBandbox, paginaAnterior, objs);
     }
     
     public void inicRecepIcone(){
        icone.setId(0l);
        totalSize = 0;
        objs = new ArrayList<ObjetoPadrao>();
     }
     
     public void setCompIcone(Icones p){
        if(p!=null){	
           iconesBandbox = p;
           bandboxIcone.setValue(iconesBandbox.getNome()); 
           longboxIcone.setValue(iconesBandbox.getId());
        }else{
	          iconesBandbox = null;
	          bandboxIcone.setValue(" "); 
	          longboxIcone.setValue(null);
	          bandboxIcone.setValue(null); 
        }
	    }
     
     public void renderizarBandboxIcone(){
        listboxIcone.setItemRenderer(new ListitemRenderer() {
           public void render(Listitem arg0, Object arg1) throws Exception {
              Icones m = (Icones) arg1;
               arg0.appendChild(new Listcell(m.getId().toString()));
 
                 try{
                    arg0.appendChild(new Listcell(m.getNome()));
                 }catch(Exception e){
                    arg0.appendChild(new Listcell("")); 
                 }
                 try{
                    arg0.appendChild(new Listcell(m.getUrl()));
                 }catch(Exception e){
                    arg0.appendChild(new Listcell("")); 
                 }
           }
        });
     }
 
     /* 
      * Fim BandBox Banco - Arthur Freire 
      */ 
     
     public void camposVisivel(){
    	 if(tipomenuBandbox.getId()==1) {
    	       divtextboxDescricao.setVisible(true);divtextboxNome.setVisible(true);
    	       divbandboxComponentemenu.setVisible(false);divbandboxIcone.setVisible(false);
    	       divtextboxUrl.setVisible(false);divtextboxMenu.setVisible(false);divtextboxInformacao.setVisible(false);
    	 }else if(tipomenuBandbox.getId()==2) {
    	       divtextboxDescricao.setVisible(true);divtextboxNome.setVisible(true);divtextboxUrl.setVisible(true);
    	       divtextboxMenu.setVisible(false);divtextboxInformacao.setVisible(true);divbandboxComponentemenu.setVisible(true);
    	       divbandboxIcone.setVisible(false);
    	 }
     }
     

     public void getRelacoesNpara1() {
       if(tipomenuBandbox!=null)
          componente.setTipomenu(tipomenuBandbox);
       else
          componente.setTipomenu(null);
       
       if(iconesBandbox!=null)
          componente.setIcone(iconesBandbox);
       else
          componente.setIcone(null);
       
       componente.setSistema(TipoSistema.ProjetoBase);
       
       if(componenteBandbox!=null) {
    	   componente.setMenu(componenteBandbox.getNome());
       }else {
    	   componente.setMenu(null);
       }
       
     }
     public void setRelacoesNpara1() {
       setCompTipomenu(componente.getTipomenu());
       setCompIcone(componente.getIcone());
       if(componente.getTipomenu().getId() == 2) {
    	   System.out.println("menu da classe autal: "+componente.getMenu());
    	   Componente comp = new Componente();
    	   comp.setId(0l);
    	   comp.setTipomenu(new Tipomenu());
    	   comp.getTipomenu().setNome(null);
    	   comp.getTipomenu().setId(0l);
    	   comp.setMenu("");
    	   comp.setNome(componente.getMenu());
    	   comp.setSistema(TipoSistema.ProjetoBase);
	       setCompComponentemenu((Componente)componenteService.filtercombobox(comp));
       }
       camposVisivel();
       
     }
     public void limparRelacoesNpara1() {
       setCompComponentemenu(null);
       setCompTipomenu(null);
       setCompIcone(null);
       divtextboxDescricao.setVisible(false);divtextboxNome.setVisible(false);divtextboxUrl.setVisible(false);
       divtextboxMenu.setVisible(false);divtextboxInformacao.setVisible(false);divbandboxComponentemenu.setVisible(false);
       divbandboxIcone.setVisible(false);
     }

     public boolean isValidForm() {
       boolean ret = true;
       valid = 0;
       if((componente.getDescricao()==null)||(componente.getDescricao().equals(""))){
         valid = 1;
         ret = false;
       }
       if((componente.getNome()==null)||(componente.getNome().equals(""))){
         valid = 2;
         ret = false;
       }else if(componente.getNome().length()<3){
  			valid = 6;
  			ret = false;
  	   }else if(componente.getTipomenu()==null){
         valid = 7;
         ret = false;
       }
       
       if(componente.getTipomenu().getId()==2) {
    	   if((componente.getUrl()==null)||(componente.getUrl().equals(""))) {
    		   valid = 8;
    	       ret = false;
    	   }else if((componente.getMenu()==null)||(componente.getMenu().equals(""))) {
    		   valid = 9;
    	       ret = false;
    	   }
       }
       return ret;
     }

     public void onClickbtSalvar() throws InterruptedException {
         Componente comp = (Componente) btSalvar(componente, componenteService);
      	if(comp!=null){
           componente = comp;
           }
        else {
         switch (valid) {
           case 1:
             AGFComponente.showMessage("info","Informe o campo: NOME VISÍVEL.");
           break;
           case 2:
             AGFComponente.showMessage("info","Informe o campo: NOME PARA MENU.");
           break;
           case 6:
               AGFComponente.showMessage("info","O campo NOME PARA MENU não pode ter menos de 3 caracteres.");
           break;
           case 7:
             AGFComponente.showMessage("info","Informe o campo: TIPO MENU.");
           break;
           case 8:
               AGFComponente.showMessage("info","Informe o campo: URL.");
           break;
           case 9:
               AGFComponente.showMessage("info","Informe o campo: MENU.");
           break;
           }
         }
       }

     public void onOK$auxhead(Event event) {
       compAux = new Componente();
       String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
       if(idAux.equals("")){
         compAux.setId(0L);
       }else {
         compAux.setId(Long.valueOf(idAux));
       }
       compAux.setDescricao(((Textbox) auxhead.getFellow("filtroDescricao")).getValue());
       compAux.setInformacao(((Textbox) auxhead.getFellow("filtroInformacao")).getValue());
       compAux.setTipomenu(new Tipomenu()); 
       compAux.getTipomenu().setNome(((Textbox) auxhead.getFellow("filtroTipomenu")).getValue());
       compAux.getTipomenu().setId(0l);
       int totalSize = 0;
       objs = new ArrayList<ObjetoPadrao>();
       objs = componenteService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
       totalSize = componenteService.getNumberRecordsFilter(compAux).intValue();
       paginacao.setActivePage(0);
       AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, componenteService,totalSize,objs);
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, componenteService, objs,null);
     }

     public void onClickbtNovo() {
       componente = new Componente();
       btNovo();
       controllerPerfilvinculado.onClickbtLista();
     }

     public void onClickbtCancelar() {
       componente = (Componente) btCancelar(componente, componenteService);
     }

     public void onClickbtRemover() throws InterruptedException {
       btRemover(componente, componenteService);
     }

     public void onClickbtLista() {
       btLista();
       AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, componenteService, null, null);
       objs = null;
     }


     public void onPaging$paginacao() {
       if(objs!=null){
          objs = componenteService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
       }
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, componenteService, objs,null);
     }

     public void carregarObjeto(Object obj) {
       componente = (Componente) obj;
       carregarObj(componente);
       controllerPerfilvinculado.onClickbtLista();
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
     
        public void onClick$aInfodescricao(){
           labelNomePoup.setValue("NOME VISÍVEL");
           toolbarButton.setLabel("Nome visível para o usuário");
        }
 
        public void onClick$aInfonome(){
           labelNomePoup.setValue("NOME PARA MENU");
           toolbarButton.setLabel("Nome Usado internamente para criação do item no MENU");
        }
 
        public void onClick$aInfourl(){
           labelNomePoup.setValue("URL");
           toolbarButton.setLabel("URL aonde fica a tabela");
        }
 
        public void onClick$aInfoinformacao(){
           labelNomePoup.setValue("INFORMAÇÕES");
           toolbarButton.setLabel("Informações sobre o Componente");
        }
 
        public void onClick$aInfocomponentemenu(){
           labelNomePoup.setValue("MENU");
           toolbarButton.setLabel("Seleciona o Menu que o componente estará");
        }
 
       public void onClickbtInformacoes() throws InterruptedException{
          Messagebox.show("O componente pode ser tanto um Menu como um Componente", "Informações da tela: Componente do Menu", Messagebox.OK, Messagebox.INFORMATION);
       }

     /**
     * Filhas
     * @author Arthur Freire
     */ 

     private IncludePerfilvinculado controllerPerfilvinculado = null;
     private Include includePerfilvinculado;
     
     @Override
     public void setControllerFilha() {
     controllerPerfilvinculado = (IncludePerfilvinculado)includePerfilvinculado.getAttribute("controller");
     }
     
     @Override
     public void setObjetosFilha() {
        objetos = new ArrayList<Object>();
        objetos.add(componente);
        objetos.add(binder);
     controllerPerfilvinculado.onInicio(objetos);
     }
     
     /*//////////////////////////Abas//////////////////////////*/ 
     public void onClick$tabPerfilvinculado(){
        controllerPerfilvinculado.onClickbtLista();
     }


   }
