 package com.agfgerador.gerenciadorprocessos.controller;

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
 import com.agfgerador.compartilhado.controller.IncludeBaseSemId;
 import com.agfgerador.compartilhado.controller.IncludeBase;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
 import com.agfgerador.compartilhado.util.AGFComponente;
 import com.agfgerador.gerenciadorprocessos.domain.Parecer;
 import com.agfgerador.gerenciadorprocessos.service.ParecerService;
 import com.agfgerador.autenticacao.domain.Usuario;
 import com.agfgerador.autenticacao.service.UsuarioService;
 import org.zkoss.zul.Listbox;
 import com.agfgerador.compartilhado.util.AGFBandbox;
 import org.zkoss.zul.Bandbox;
 import com.agfgerador.gerenciadorprocessos.domain.Processo;
 import com.agfgerador.gerenciadorprocessos.service.ProcessoService;
 import com.agfgerador.gerenciadorprocessos.domain.Pessoa;
 import org.zkoss.zul.Label;
 import org.zkoss.zul.Toolbarbutton;

   public class IncludeParecerr extends IncludeBaseSemId implements IPaginacao{
    private static final long serialVersionUID = 1L;

     public Parecer parecer;
     private Usuario usuario = new Usuario();
     private ParecerService parecerService;
     private int valid;
     private List<ObjetoPadrao> objs = null;
     private List<ObjetoPadraoSemId> objsemid = null;
     private Parecer compAux = new Parecer();
     private int totalSize = 0;
     private Integer pageSizeBandbox = 5;

   
    /////////////Processo
    Processo processo = new Processo();
    private Bandbox bandboxProcesso;
    private Longbox longboxProcesso;
    private Listbox listboxProcesso;
    private Paging paginacaoProcesso;
    private Processo processoBandbox;
    private ProcessoService processoService;
 
    private Label labelNomePoup;
    private Toolbarbutton toolbarButton;
     private Datebox dateboxDtparecer;

     public void doAfterCompose(Component win) throws Exception {
       win.setAttribute("controller",this);
       super.doAfterCompose(win,"all");
       renderizarBandboxProcesso();
     }

 	@Override
 	public void onInicio(List<Object> objetos) {
 		onInicio();
 		usuario = (Usuario)objetos.get(0);
 	}

      public void renderizarListaPrincipal() {
       listbox.setItemRenderer(new ListitemRenderer() {
         public void render(Listitem arg0, Object arg1) throws Exception {
           Parecer m = (Parecer) arg1;
           Listcell lc = new Listcell();
           lc.appendChild(new Checkbox());
           arg0.appendChild(lc);
           arg0.appendChild(new Listcell(m.getId().toString()));
           try{
           arg0.appendChild(new Listcell(m.getProcesso().getObs()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell(m.getDescricao()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
              SimpleDateFormat formatodate = new SimpleDateFormat("E dd/MM/yyyy");
              arg0.appendChild(new Listcell(String.valueOf(formatodate.format(m.getDtparecer()))));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
         }
       });
     }

     public void inicializarRelacoesNpara1() {

}

     /* 
      * BandBox Processo - Arthur Freire 
      */ 
     public void onClick$labelProcesso(){
      labelLink("processo", null);
     }
     
     public void onOK$longboxProcesso(){
        processo = new Processo();
        setCompProcesso((Processo)AGFBandbox.onOKLongbox(longboxProcesso, processoBandbox, processoService));
     }
     
     public void onOK$bandboxProcesso(){
        onChange$bandboxProcesso();
     }
     
     public void onChange$bandboxProcesso(){
        processo.setObs(bandboxProcesso.getValue());
        inicRecepProcesso();
        objs = processoService.filter(processo, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = processoService.getNumberRecordsFilter(processo).intValue();
        AGFBandbox.onChange(bandboxProcesso, listboxProcesso, processoService, paginacaoProcesso, pageSizeBandbox, totalSize, objs);
     }
     
     public void onOpen$bandboxProcesso(){
        processo.setObs(null);
        listaProcessos();
     }
     
     public void onClick$listboxProcesso(){	
        processo.setObs(null);
        setCompProcesso((Processo)AGFBandbox.onClickList(bandboxProcesso, processoBandbox, listboxProcesso));
     }
    
     public void listaProcessos(){
        inicRecepProcesso();
        objs = processoService.filter(processo, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = processoService.getNumberRecordsFilter(processo).intValue();
        AGFBandbox.listaElementos(listboxProcesso, processoService, paginacaoProcesso, pageSizeBandbox, totalSize, objs);
     }
    
     public void onPaging$paginacaoProcesso(){
        inicRecepProcesso();
        objs = processoService.filter(processo, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoProcesso,pageSizeBandbox,paginaAnterior));
        AGFBandbox.onPaging(null, listboxProcesso, processoService, paginacaoProcesso, pageSizeBandbox, paginaAnterior, objs);
     }
     
     public void inicRecepProcesso(){
        processo.setId(0l);
        processo.setPessoa(new Pessoa());
        processo.getPessoa().setNome(null);
        processo.getPessoa().setId(0l);
        totalSize = 0;
        objs = new ArrayList<ObjetoPadrao>();
     }
     
     public void setCompProcesso(Processo p){
        if(p!=null){	
           processoBandbox = p;
           bandboxProcesso.setValue(processoBandbox.getObs()); 
           longboxProcesso.setValue(processoBandbox.getId());
        }else{
	          processoBandbox = null;
	          bandboxProcesso.setValue(" "); 
	          longboxProcesso.setValue(null);
	          bandboxProcesso.setValue(null); 
        }
	    }
     
     public void renderizarBandboxProcesso(){
        listboxProcesso.setItemRenderer(new ListitemRenderer() {
           public void render(Listitem arg0, Object arg1) throws Exception {
              Processo m = (Processo) arg1;
               arg0.appendChild(new Listcell(m.getId().toString()));
 
                 try{
                    arg0.appendChild(new Listcell(m.getPessoa().getNome().toString()));
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
                 try{
                    arg0.appendChild(new Listcell(m.getNumprocesso().toString()));
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
       if(usuario!=null){
          parecer.setUsuario(new Usuario());
          parecer.setUsuario(usuario);
       }else{
          parecer.setUsuario(null);
       }
       if(processoBandbox!=null)
          parecer.setProcesso(processoBandbox);
       else
          parecer.setProcesso(null);
       
       parecer.setDtparecer(dateboxDtparecer.getValue());
     }
     public void setRelacoesNpara1() {
       setCompProcesso(parecer.getProcesso());
       dateboxDtparecer.setValue(parecer.getDtparecer());
     }
     public void limparRelacoesNpara1() {
       setCompProcesso(null);
     }

     public boolean isValidForm() {
       boolean ret = true;
       valid = 0;
       if(parecer.getUsuario()==null){
         valid = 1;
         ret = false;
       }
       if(parecer.getProcesso()==null){
         valid = 2;
         ret = false;
       }
       return ret;
     }

     public void onClickbtSalvar() throws InterruptedException {
         Parecer comp = (Parecer) btSalvar(parecer, parecerService);
      	if(comp!=null){
           parecer = comp;
           limitarQuantidade();
           }
        else {
         switch (valid) {
           case 1:
             AGFComponente.showMessage("info","Informe o campo: USUARIO.");
           break;
           case 2:
             AGFComponente.showMessage("info","Informe o campo: PROCESSO.");
           break;
           }
         }
       }

     public void onOK$auxhead(Event event) {
       try{
         if(usuario.getId()==null){
            ListModel ls = new ListModelList();
            listbox.setModel(ls);
         }else {
             compAux = new Parecer();
             String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
             if(idAux.equals("")){
               compAux.setId(0L);
             }else {
               compAux.setId(Long.valueOf(idAux));
             }
             compAux.setUsuario(new Usuario()); 
             compAux.setUsuario(usuario);
             compAux.setProcesso(new Processo()); 
             compAux.getProcesso().setObs(((Textbox) auxhead.getFellow("filtroProcesso")).getValue());
             compAux.getProcesso().setId(0l);
             compAux.setDescricao(((Textbox) auxhead.getFellow("filtroDescricao")).getValue());
             compAux.setDtparecer(((Datebox) auxhead.getFellow("filtroDtparecer")).getValue() );
             int totalSize = 0;
             objsemid = new ArrayList<ObjetoPadraoSemId>();
             objsemid = parecerService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
             totalSize = parecerService.getNumberRecordsFilter(compAux).intValue();
             paginacao.setActivePage(0);
             AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, parecerService,totalSize,objsemid);
             AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, parecerService, objsemid,null);
          }
       }catch(Exception e){
          ListModel ls = new ListModelList();
          listbox.setModel(ls);
          e.printStackTrace();
       }
     }

     public void onClickbtNovo() {
       if(usuario.getId() == null ){
          try {
             Messagebox.show("Salve o formulário antes de inserir um novo registro");
          } catch (InterruptedException e) {
             e.printStackTrace();
          }
       }else{
          parecer = new Parecer();
          btNovo();
       }
     }

     public void onClickbtCancelar() {
       parecer = (Parecer) btCancelar(parecer, parecerService);
     }

     public void onClickbtRemover() throws InterruptedException {
       btRemover(parecer, parecerService);
     }

     public void onClickbtLista() {
       btLista();
       try{
         if(usuario.getId()==null){
            ListModel ls = new ListModelList();
            listbox.setModel(ls);
         }else {
             compAux = new Parecer();
             String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
             if(idAux.equals("")){
               compAux.setId(0L);
             }else {
               compAux.setId(Long.valueOf(idAux));
             }
             compAux.setUsuario(new Usuario()); 
             compAux.setUsuario(usuario);
             compAux.setProcesso(new Processo()); 
             compAux.getProcesso().setObs(((Textbox) auxhead.getFellow("filtroProcesso")).getValue());
             compAux.getProcesso().setId(0l);
             compAux.setDescricao(((Textbox) auxhead.getFellow("filtroDescricao")).getValue());
             compAux.setDtparecer(((Datebox) auxhead.getFellow("filtroDtparecer")).getValue() );
             Integer totalSize = 0;
             objsemid = new ArrayList<ObjetoPadraoSemId>();
             objsemid = parecerService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
             totalSize = parecerService.getNumberRecordsFilter(compAux).intValue();
             paginacao.setActivePage(0);
             AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, parecerService,totalSize,objsemid);
             AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, parecerService, objsemid,null);
             limitarQuantidade();
          }
       }catch(Exception e){
          ListModel ls = new ListModelList();
          listbox.setModel(ls);
          e.printStackTrace();
       }
     }


     public void onPaging$paginacao() {
       if(objsemid!=null){
          objsemid = parecerService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
       }
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, parecerService, objsemid,null);
     }

     public void carregarObjeto(Object obj) {
       parecer = (Parecer) obj;
       carregarObj(parecer);
     }

     public void setObjetoTelaForm() {

     }

     public void atualizaComboLink(String combo) {

     }

     public void onClickbtImprimir() {

     }

     public void onClick$btInformacoes() {

     }

     public void setControllerFilha() {

     }

     public void setObjetosFilha() {

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
           labelNomePoup.setValue("DESCRIÇÃO");
           toolbarButton.setLabel("Descrição do parecer");
        }
 
       public void limitarQuantidade() {
          Parecer buscas = new Parecer();
          buscas.setId(0l);
          buscas.setUsuario(new Usuario()); 
          buscas.setUsuario(usuario);
          buscas.setProcesso(new Processo()); 
          buscas.getProcesso().setObs(((Textbox) auxhead.getFellow("filtroProcesso")).getValue());
          buscas.getProcesso().setId(0l);
          buscas.setDescricao(null);
          buscas.setDtparecer(null);
          Integer totalSize = parecerService.getNumberRecordsFilter(buscas).intValue();
    	     if(totalSize!=null) {
	            if(totalSize>=8) {
	               barraFerramentasInclude.getFellow("btNovo").setVisible(false);
	            }else {
	               barraFerramentasInclude.getFellow("btNovo").setVisible(true);
	            }
          }
       }

   }
