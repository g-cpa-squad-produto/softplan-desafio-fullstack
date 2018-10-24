 package com.agfgerador.autenticacao.controller;

 import java.util.List;
 import java.util.ArrayList;
 import org.zkoss.zul.Paging;
 import org.zkoss.zk.ui.Component;
 import org.zkoss.zk.ui.event.Event;
 import org.zkoss.zul.Listcell;
 import org.zkoss.zul.Listitem;
 import org.zkoss.zul.ListitemRenderer;
 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
 import org.zkoss.zul.Textbox;
 import org.zkoss.zul.Intbox;
 import org.zkoss.zul.Image;
 import com.agfgerador.compartilhado.controller.ControllerAGF;
 import com.agfgerador.compartilhado.util.AGFImagem;
 import org.zkoss.zul.Longbox;
 import org.zkoss.zul.Checkbox;
 import com.agfgerador.compartilhado.util.AGFPaginacao;
 import com.agfgerador.compartilhado.controller.IPaginacao;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
 import com.agfgerador.compartilhado.util.AGFComponente;
 import com.agfgerador.autenticacao.domain.Email;
 import com.agfgerador.autenticacao.service.EmailService;
 import com.agfgerador.autenticacao.domain.Parametros;
 import com.agfgerador.autenticacao.service.ParametrosService;
 import org.zkoss.zul.Listbox;
 import com.agfgerador.compartilhado.util.AGFBandbox;
 import org.zkoss.zul.Bandbox;

   public class EmailController extends ControllerAGF implements IPaginacao{
    private static final long serialVersionUID = 1L;

     public Email email;
     private EmailService emailService;
     private int valid;
     private List<ObjetoPadrao> objs = null;
     private List<ObjetoPadraoSemId> objsemid = null;
     private Email compAux = new Email();
     private int totalSize = 0;
     private Integer pageSizeBandbox = 5;

   
    /////////////Parametros
    Parametros parametro = new Parametros();
    private Bandbox bandboxParametro;
    private Longbox longboxParametro;
    private Listbox listboxParametro;
    private Paging paginacaoParametro;
    private Parametros parametrosBandbox;
    private ParametrosService parametrosService;
 

     public void doAfterCompose(Component win) throws Exception {
       win.setAttribute("controller",this);
       win.setAttribute("nomeTela", "Email");
       super.doAfterCompose(win,true,"all");
       renderizarBandboxParametro();
       btInformacoes.setVisible(false);
     }

     public void renderizarListaPrincipal() {
       listbox.setItemRenderer(new ListitemRenderer() {
         public void render(Listitem arg0, Object arg1) throws Exception {
           Email m = (Email) arg1;
           Listcell lc = new Listcell();
           lc.appendChild(new Checkbox());
           arg0.appendChild(lc);
           arg0.appendChild(new Listcell(m.getId().toString()));
           try{
           arg0.appendChild(new Listcell(m.getServidor()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell(m.getPorta().toString()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell(m.getLogin()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell(m.getEmail()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell(m.getParametro().getNome()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
         }
       });
     }

     public void inicializarRelacoesNpara1() {

}

     /* 
      * BandBox Parametros - Arthur Freire 
      */ 
     public void onClick$labelParametro(){
      labelLink("parametros", null);
     }
     
     public void onOK$longboxParametro(){
        parametro = new Parametros();
        setCompParametro((Parametros)AGFBandbox.onOKLongbox(longboxParametro, parametrosBandbox, parametrosService));
     }
     
     public void onOK$bandboxParametro(){
        onChange$bandboxParametro();
     }
     
     public void onChange$bandboxParametro(){
        parametro.setNome(bandboxParametro.getValue());
        inicRecepParametro();
        objs = parametrosService.filter(parametro, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = parametrosService.getNumberRecordsFilter(parametro).intValue();
        AGFBandbox.onChange(bandboxParametro, listboxParametro, parametrosService, paginacaoParametro, pageSizeBandbox, totalSize, objs);
     }
     
     public void onOpen$bandboxParametro(){
        parametro.setNome(null);
        listaParametros();
     }
     
     public void onClick$listboxParametro(){	
        parametro.setNome(null);
        setCompParametro((Parametros)AGFBandbox.onClickList(bandboxParametro, parametrosBandbox, listboxParametro));
     }
    
     public void listaParametros(){
        inicRecepParametro();
        objs = parametrosService.filter(parametro, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = parametrosService.getNumberRecordsFilter(parametro).intValue();
        AGFBandbox.listaElementos(listboxParametro, parametrosService, paginacaoParametro, pageSizeBandbox, totalSize, objs);
     }
    
     public void onPaging$paginacaoParametro(){
        inicRecepParametro();
        objs = parametrosService.filter(parametro, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoParametro,pageSizeBandbox,paginaAnterior));
        AGFBandbox.onPaging(null, listboxParametro, parametrosService, paginacaoParametro, pageSizeBandbox, paginaAnterior, objs);
     }
     
     public void inicRecepParametro(){
        parametro.setId(0l);
        totalSize = 0;
        objs = new ArrayList<ObjetoPadrao>();
     }
     
     public void setCompParametro(Parametros p){
        if(p!=null){	
           parametrosBandbox = p;
           bandboxParametro.setValue(parametrosBandbox.getNome()); 
           longboxParametro.setValue(parametrosBandbox.getId());
        }else{
	          parametrosBandbox = null;
	          bandboxParametro.setValue(" "); 
	          longboxParametro.setValue(null);
	          bandboxParametro.setValue(null); 
        }
	    }
     
     public void renderizarBandboxParametro(){
        listboxParametro.setItemRenderer(new ListitemRenderer() {
           public void render(Listitem arg0, Object arg1) throws Exception {
              Parametros m = (Parametros) arg1;
               arg0.appendChild(new Listcell(m.getId().toString()));
 
                 try{
                    Image brasao = new Image();
                    brasao.setContent(AGFImagem.converterByteToBufferedImage(m.getBrasao()));
                    Listcell cell = new Listcell();
                    if(brasao.getSrc() == null) {
                       cell.setImageContent(brasao.getContent());
                    }else{
                       cell.setImage(brasao.getSrc());
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
                 try{
                    arg0.appendChild(new Listcell(m.getNomereduzido()));
                 }catch(Exception e){
                    arg0.appendChild(new Listcell("")); 
                 }
                 try{
                    arg0.appendChild(new Listcell(m.getCnpj()));
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
       if(parametrosBandbox!=null)
          email.setParametro(parametrosBandbox);
       else
          email.setParametro(null);
       
     }
     public void setRelacoesNpara1() {
       setCompParametro(email.getParametro());
     }
     public void limparRelacoesNpara1() {
       setCompParametro(null);
     }

     public boolean isValidForm() {
       boolean ret = true;
       valid = 0;
       if((email.getServidor()==null)||(email.getServidor().equals(""))){
         valid = 1;
         ret = false;
       }
       if(email.getPorta()==null){
         valid = 2;
         ret = false;
       }
       if((email.getLogin()==null)||(email.getLogin().equals(""))){
         valid = 3;
         ret = false;
       }
       if((email.getSenha()==null)||(email.getSenha().equals(""))){
         valid = 4;
         ret = false;
       }
       if((email.getEmail()==null)||(email.getEmail().equals(""))){
         valid = 5;
         ret = false;
       }
       if((email.getFinalidade()==null)||(email.getFinalidade().equals(""))){
         valid = 6;
         ret = false;
       }
       return ret;
     }

     public void onClickbtSalvar() throws InterruptedException {
         Email comp = (Email) btSalvar(email, emailService);
      	if(comp!=null){
           email = comp;
           }
        else {
         switch (valid) {
           case 1:
             AGFComponente.showMessage("info","Informe o campo: SERVIDOR.");
           break;
           case 2:
             AGFComponente.showMessage("info","Informe o campo: PORTA.");
           break;
           case 3:
             AGFComponente.showMessage("info","Informe o campo: LOGIN.");
           break;
           case 4:
             AGFComponente.showMessage("info","Informe o campo: SENHA.");
           break;
           case 5:
             AGFComponente.showMessage("info","Informe o campo: EMAIL.");
           break;
           case 6:
             AGFComponente.showMessage("info","Informe o campo: OBS.");
           break;
           }
         }
       }

     public void onOK$auxhead(Event event) {
       compAux = new Email();
       String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
       if(idAux.equals("")){
         compAux.setId(0L);
       }else {
         compAux.setId(Long.valueOf(idAux));
       }
       compAux.setServidor(((Textbox) auxhead.getFellow("filtroServidor")).getValue());
       compAux.setPorta(((Intbox) auxhead.getFellow("filtroPorta")).getValue());
       compAux.setLogin(((Textbox) auxhead.getFellow("filtroLogin")).getValue());
       compAux.setSenha(null);
       compAux.setEmail(((Textbox) auxhead.getFellow("filtroEmail")).getValue());
       compAux.setParametro(new Parametros()); 
       compAux.getParametro().setNome(((Textbox) auxhead.getFellow("filtroParametro")).getValue());
       compAux.getParametro().setId(0l);
       int totalSize = 0;
       objs = new ArrayList<ObjetoPadrao>();
       objs = emailService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
       totalSize = emailService.getNumberRecordsFilter(compAux).intValue();
       paginacao.setActivePage(0);
       AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, emailService,totalSize,objs);
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, emailService, objs,null);
     }

     public void onClickbtNovo() {
       email = new Email();
       btNovo();
     }

     public void onClickbtCancelar() {
       email = (Email) btCancelar(email, emailService);
     }

     public void onClickbtRemover() throws InterruptedException {
       btRemover(email, emailService);
     }

     public void onClickbtLista() {
       btLista();
       AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, emailService, null, null);
       objs = null;
     }


     public void onPaging$paginacao() {
       if(objs!=null){
          objs = emailService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
       }
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, emailService, objs,null);
     }

     public void carregarObjeto(Object obj) {
       email = (Email) obj;
       carregarObj(email);
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
     

     public void setControllerFilha() {

     }

     public void setObjetosFilha() {

     }

   }
