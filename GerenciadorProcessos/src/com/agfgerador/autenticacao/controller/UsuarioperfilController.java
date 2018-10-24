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
 import com.agfgerador.compartilhado.controller.IPaginacao;
 import com.agfgerador.compartilhado.controller.IFilhas;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
 import com.agfgerador.compartilhado.util.AGFComponente;
 import com.agfgerador.autenticacao.domain.UsuarioPerfil;
 import com.agfgerador.autenticacao.service.UsuarioPerfilService;
 import com.agfgerador.autenticacao.domain.Perfil;
 import com.agfgerador.autenticacao.service.PerfilService;
 import org.zkoss.zul.Listbox;
 import com.agfgerador.compartilhado.util.AGFBandbox;
 import org.zkoss.zul.Bandbox;
 import com.agfgerador.autenticacao.domain.Usuario;
 import com.agfgerador.autenticacao.service.UsuarioService;
 import org.zkoss.zul.Label;
 import org.zkoss.zul.Toolbarbutton;

   public class UsuarioperfilController extends ControllerAGF implements IPaginacao{
    private static final long serialVersionUID = 1L;

     public UsuarioPerfil usuarioperfil;
     private UsuarioPerfilService usuarioPerfilService;
     private int valid;
     private List<ObjetoPadrao> objs = null;
     private List<ObjetoPadraoSemId> objsemid = null;
     private UsuarioPerfil compAux = new UsuarioPerfil();
     private int totalSize = 0;
     private Integer pageSizeBandbox = 5;

   
    /////////////Perfil
    Perfil perfil = new Perfil();
    private Bandbox bandboxPerfil;
    private Longbox longboxPerfil;
    private Listbox listboxPerfil;
    private Paging paginacaoPerfil;
    private Perfil perfilBandbox;
    private PerfilService perfilService;
 
   
    /////////////Usuario
    Usuario usuario = new Usuario();
    private Bandbox bandboxUsuario;
    private Longbox longboxUsuario;
    private Listbox listboxUsuario;
    private Paging paginacaoUsuario;
    private Usuario usuarioBandbox;
    private UsuarioService usuarioService;
 
    private Checkbox checkboxAtivo;
 private Label labelNomePoup;
 private Toolbarbutton toolbarButton;
    private Checkbox checkboxAdministrador;

     public void doAfterCompose(Component win) throws Exception {
       win.setAttribute("controller",this);
       win.setAttribute("nomeTela", "UsuarioPerfil");
       super.doAfterCompose(win,true,"all");
       renderizarBandboxPerfil();
       renderizarBandboxUsuario();
     }

     public void renderizarListaPrincipal() {
       listbox.setItemRenderer(new ListitemRenderer() {
         public void render(Listitem arg0, Object arg1) throws Exception {
           UsuarioPerfil m = (UsuarioPerfil) arg1;
           Listcell lc = new Listcell();
           lc.appendChild(new Checkbox());
           arg0.appendChild(lc);
           arg0.appendChild(new Listcell(m.getId().toString()));
           try{
           arg0.appendChild(new Listcell(m.getPerfil().getNome()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell(m.getUsuario().getNome()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell((m.isAtivo() == true ? "Ativo" : "Inativo")));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
           try{
           arg0.appendChild(new Listcell((m.isAdministrador() == true ? "Ativo" : "Inativo")));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
         }
       });
     }

     public void inicializarRelacoesNpara1() {

}

     /* 
      * BandBox Perfil - Arthur Freire 
      */ 
     public void onClick$labelPerfil(){
      labelLink("perfil", null);
     }
     
     public void onOK$longboxPerfil(){
        perfil = new Perfil();
        setCompPerfil((Perfil)AGFBandbox.onOKLongbox(longboxPerfil, perfilBandbox, perfilService));
     }
     
     public void onOK$bandboxPerfil(){
        onChange$bandboxPerfil();
     }
     
     public void onChange$bandboxPerfil(){
        perfil.setNome(bandboxPerfil.getValue());
        inicRecepPerfil();
        objs = perfilService.filter(perfil, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = perfilService.getNumberRecordsFilter(perfil).intValue();
        AGFBandbox.onChange(bandboxPerfil, listboxPerfil, perfilService, paginacaoPerfil, pageSizeBandbox, totalSize, objs);
     }
     
     public void onOpen$bandboxPerfil(){
        perfil.setNome(null);
        listaPerfils();
     }
     
     public void onClick$listboxPerfil(){	
        perfil.setNome(null);
        setCompPerfil((Perfil)AGFBandbox.onClickList(bandboxPerfil, perfilBandbox, listboxPerfil));
     }
    
     public void listaPerfils(){
        inicRecepPerfil();
        objs = perfilService.filter(perfil, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = perfilService.getNumberRecordsFilter(perfil).intValue();
        AGFBandbox.listaElementos(listboxPerfil, perfilService, paginacaoPerfil, pageSizeBandbox, totalSize, objs);
     }
    
     public void onPaging$paginacaoPerfil(){
        inicRecepPerfil();
        objs = perfilService.filter(perfil, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoPerfil,pageSizeBandbox,paginaAnterior));
        AGFBandbox.onPaging(null, listboxPerfil, perfilService, paginacaoPerfil, pageSizeBandbox, paginaAnterior, objs);
     }
     
     public void inicRecepPerfil(){
        perfil.setId(0l);
        totalSize = 0;
        objs = new ArrayList<ObjetoPadrao>();
     }
     
     public void setCompPerfil(Perfil p){
        if(p!=null){	
           perfilBandbox = p;
           bandboxPerfil.setValue(perfilBandbox.getNome()); 
           longboxPerfil.setValue(perfilBandbox.getId());
        }else{
	          perfilBandbox = null;
	          bandboxPerfil.setValue(" "); 
	          longboxPerfil.setValue(null);
	          bandboxPerfil.setValue(null); 
        }
	    }
     
     public void renderizarBandboxPerfil(){
        listboxPerfil.setItemRenderer(new ListitemRenderer() {
           public void render(Listitem arg0, Object arg1) throws Exception {
              Perfil m = (Perfil) arg1;
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
                 try{
                    arg0.appendChild(new Listcell(m.getLogin()));
                 }catch(Exception e){
                    arg0.appendChild(new Listcell("")); 
                 }
                 try{
                    arg0.appendChild(new Listcell((m.isHabilitado() == true ? "Ativo" : "Inativo")));
                 }catch(Exception e){
                    arg0.appendChild(new Listcell("")); 
                 }
                 try{
                    arg0.appendChild(new Listcell(m.getEmail()));
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
       if(perfilBandbox!=null)
          usuarioperfil.setPerfil(perfilBandbox);
       else
          usuarioperfil.setPerfil(null);
       
       if(usuarioBandbox!=null)
          usuarioperfil.setUsuario(usuarioBandbox);
       else
          usuarioperfil.setUsuario(null);
       
       usuarioperfil.setAtivo(checkboxAtivo.isChecked());
       usuarioperfil.setAdministrador(checkboxAdministrador.isChecked());
     }
     public void setRelacoesNpara1() {
       setCompPerfil(usuarioperfil.getPerfil());
       setCompUsuario(usuarioperfil.getUsuario());
       checkboxAtivo.setChecked(usuarioperfil.isAtivo());
       checkboxAdministrador.setChecked(usuarioperfil.isAdministrador());
     }
     public void limparRelacoesNpara1() {
       setCompPerfil(null);
       setCompUsuario(null);
       checkboxAtivo.setChecked(false);
       checkboxAdministrador.setChecked(false);
     }

     public boolean isValidForm() {
       boolean ret = true;
       valid = 0;
       if(usuarioperfil.getPerfil()==null){
         valid = 1;
         ret = false;
       }
       if(usuarioperfil.getUsuario()==null){
         valid = 2;
         ret = false;
       }
       return ret;
     }

     public void onClickbtSalvar() throws InterruptedException {
         UsuarioPerfil comp = (UsuarioPerfil) btSalvar(usuarioperfil, usuarioPerfilService);
      	if(comp!=null){
           usuarioperfil = comp;
           }
        else {
         switch (valid) {
           case 1:
             AGFComponente.showMessage("info","Informe o campo: PERFIL.");
           break;
           case 2:
             AGFComponente.showMessage("info","Informe o campo: USU�RIO.");
           break;
           }
         }
       }

     public void onOK$auxhead(Event event) {
       compAux = new UsuarioPerfil();
       String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
       if(idAux.equals("")){
         compAux.setId(0L);
       }else {
         compAux.setId(Long.valueOf(idAux));
       }
       compAux.setPerfil(new Perfil()); 
       compAux.getPerfil().setNome(((Textbox) auxhead.getFellow("filtroPerfil")).getValue());
       compAux.getPerfil().setId(0l);
       compAux.setUsuario(new Usuario()); 
       compAux.getUsuario().setNome(((Textbox) auxhead.getFellow("filtroUsuario")).getValue());
       compAux.getUsuario().setId(0l);
       String ativo = ((Textbox) auxhead.getFellow("filtroAtivo")).getValue();
       compAux.setAtivo(ativo.equalsIgnoreCase("") ? null : ativo.equalsIgnoreCase("Ativo")? true : false);
       String administrador = ((Textbox) auxhead.getFellow("filtroAdministrador")).getValue();
       compAux.setAdministrador(administrador.equalsIgnoreCase("") ? null : administrador.equalsIgnoreCase("Ativo")? true : false);
       int totalSize = 0;
       objs = new ArrayList<ObjetoPadrao>();
       objs = usuarioPerfilService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
       totalSize = usuarioPerfilService.getNumberRecordsFilter(compAux).intValue();
       paginacao.setActivePage(0);
       AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, usuarioPerfilService,totalSize,objs);
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, usuarioPerfilService, objs,null);
     }

     public void onClickbtNovo() {
       usuarioperfil = new UsuarioPerfil();
       btNovo();
     }

     public void onClickbtCancelar() {
       usuarioperfil = (UsuarioPerfil) btCancelar(usuarioperfil, usuarioPerfilService);
     }

     public void onClickbtRemover() throws InterruptedException {
       btRemover(usuarioperfil, usuarioPerfilService);
     }

     public void onClickbtLista() {
       btLista();
       AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, usuarioPerfilService, null, null);
       objs = null;
     }


     public void onPaging$paginacao() {
       if(objs!=null){
          objs = usuarioPerfilService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
       }
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, usuarioPerfilService, objs,null);
     }

     public void carregarObjeto(Object obj) {
       usuarioperfil = (UsuarioPerfil) obj;
       carregarObj(usuarioperfil);
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
     
        public void onClick$aInfoativo(){
           labelNomePoup.setValue("ATIVO");
           toolbarButton.setLabel("Est� ativo ou n�o o perfil");
        }
 
        public void onClick$aInfoadministrador(){
           labelNomePoup.setValue("ADMINISTRADOR");
           toolbarButton.setLabel("Se esse perfil � de administrador");
        }
 
       public void onClickbtInformacoes() throws InterruptedException{
          Messagebox.show("Informar o Perfil do Usu�rio", "Informa��es da tela: Usu�rio Perfil", Messagebox.OK, Messagebox.INFORMATION);
       }


     public void setControllerFilha() {

     }

     public void setObjetosFilha() {

     }

   }
