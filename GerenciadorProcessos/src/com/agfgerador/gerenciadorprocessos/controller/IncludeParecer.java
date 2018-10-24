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

   public class IncludeParecer extends IncludeBaseSemId implements IPaginacao{
    private static final long serialVersionUID = 1L;

     public Parecer parecer;
     private Processo processo = new Processo();
     private ParecerService parecerService;
     private int valid;
     private List<ObjetoPadrao> objs = null;
     private List<ObjetoPadraoSemId> objsemid = null;
     private Parecer compAux = new Parecer();
     private int totalSize = 0;
     private Integer pageSizeBandbox = 5;

   
    /////////////Usuario
    Usuario usuario = new Usuario();
    private Bandbox bandboxUsuario;
    private Longbox longboxUsuario;
    private Listbox listboxUsuario;
    private Paging paginacaoUsuario;
    private Usuario usuarioBandbox;
    private UsuarioService usuarioService;
 
    private Label labelNomePoup;
    private Toolbarbutton toolbarButton;
     private Datebox dateboxDtparecer;

     public void doAfterCompose(Component win) throws Exception {
       win.setAttribute("controller",this);
       super.doAfterCompose(win,"all");
       renderizarBandboxUsuario();
     }

 	@Override
 	public void onInicio(List<Object> objetos) {
 		onInicio();
 		processo = (Processo)objetos.get(0);
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
           arg0.appendChild(new Listcell(m.getUsuario().getNome()));
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
      * BandBox Usuario - Arthur Freire 
      */ 
     public void onClick$labelUsuario(){
      labelLink("usuario", null);
     }
     
     public void onOK$longboxUsuario(){
        usuario = new Usuario();
        setCompUsuario((Usuario)AGFBandbox.onOKLongbox(longboxUsuario, usuarioBandbox, usuarioService));
     }
     
     public void onOK$bandboxUsuario(){
        onChange$bandboxUsuario();
     }
     
     public void onChange$bandboxUsuario(){
        usuario.setNome(bandboxUsuario.getValue());
        inicRecepUsuario();
        objs = usuarioService.filter(usuario, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = usuarioService.getNumberRecordsFilter(usuario).intValue();
        AGFBandbox.onChange(bandboxUsuario, listboxUsuario, usuarioService, paginacaoUsuario, pageSizeBandbox, totalSize, objs);
     }
     
     public void onOpen$bandboxUsuario(){
        usuario.setNome(null);
        listaUsuarios();
     }
     
     public void onClick$listboxUsuario(){	
        usuario.setNome(null);
        setCompUsuario((Usuario)AGFBandbox.onClickList(bandboxUsuario, usuarioBandbox, listboxUsuario));
     }
    
     public void listaUsuarios(){
        inicRecepUsuario();
        objs = usuarioService.filter(usuario, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = usuarioService.getNumberRecordsFilter(usuario).intValue();
        AGFBandbox.listaElementos(listboxUsuario, usuarioService, paginacaoUsuario, pageSizeBandbox, totalSize, objs);
     }
    
     public void onPaging$paginacaoUsuario(){
        inicRecepUsuario();
        objs = usuarioService.filter(usuario, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoUsuario,pageSizeBandbox,paginaAnterior));
        AGFBandbox.onPaging(null, listboxUsuario, usuarioService, paginacaoUsuario, pageSizeBandbox, paginaAnterior, objs);
     }
     
     public void inicRecepUsuario(){
        usuario.setId(0l);
        totalSize = 0;
        objs = new ArrayList<ObjetoPadrao>();
     }
     
     public void setCompUsuario(Usuario p){
        if(p!=null){	
           usuarioBandbox = p;
           bandboxUsuario.setValue(usuarioBandbox.getNome()); 
           longboxUsuario.setValue(usuarioBandbox.getId());
        }else{
	          usuarioBandbox = null;
	          bandboxUsuario.setValue(" "); 
	          longboxUsuario.setValue(null);
	          bandboxUsuario.setValue(null); 
        }
	    }
     
     public void renderizarBandboxUsuario(){
        listboxUsuario.setItemRenderer(new ListitemRenderer() {
           public void render(Listitem arg0, Object arg1) throws Exception {
              Usuario m = (Usuario) arg1;
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
     

     public void getRelacoesNpara1() {
       if(usuarioBandbox!=null)
          parecer.setUsuario(usuarioBandbox);
       else
          parecer.setUsuario(null);
       
       if(processo!=null){
          parecer.setProcesso(new Processo());
          parecer.setProcesso(processo);
       }else{
          parecer.setProcesso(null);
       }
       parecer.setDtparecer(dateboxDtparecer.getValue());
     }
     public void setRelacoesNpara1() {
       setCompUsuario(parecer.getUsuario());
       dateboxDtparecer.setValue(parecer.getDtparecer());
     }
     public void limparRelacoesNpara1() {
       setCompUsuario(null);
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
         if(processo.getId()==null){
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
             compAux.getUsuario().setNome(((Textbox) auxhead.getFellow("filtroUsuario")).getValue());
             compAux.getUsuario().setId(0l);
             compAux.setProcesso(new Processo()); 
             compAux.setProcesso(processo);
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
       if(processo.getId() == null ){
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
         if(processo.getId()==null){
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
             compAux.getUsuario().setNome(((Textbox) auxhead.getFellow("filtroUsuario")).getValue());
             compAux.getUsuario().setId(0l);
             compAux.setProcesso(new Processo()); 
             compAux.setProcesso(processo);
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
          buscas.getUsuario().setNome(((Textbox) auxhead.getFellow("filtroUsuario")).getValue());
          buscas.getUsuario().setId(0l);
          buscas.setProcesso(new Processo()); 
          buscas.setProcesso(processo);
          buscas.setDescricao(null);
          buscas.setDtparecer(null);
          Integer totalSize = parecerService.getNumberRecordsFilter(buscas).intValue();
    	     if(totalSize!=null) {
	            if(totalSize>=5) {
	               barraFerramentasInclude.getFellow("btNovo").setVisible(false);
	            }else {
	               barraFerramentasInclude.getFellow("btNovo").setVisible(true);
	            }
          }
       }

   }
