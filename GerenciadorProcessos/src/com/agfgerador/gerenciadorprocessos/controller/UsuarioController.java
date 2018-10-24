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
 import com.agfgerador.autenticacao.domain.Usuario;
 import com.agfgerador.autenticacao.service.UsuarioService;

   public class UsuarioController extends ControllerAGF implements IPaginacao,IFilhas{
    private static final long serialVersionUID = 1L;

     public Usuario usuario;
     private UsuarioService usuarioService;
     private int valid;
     private List<ObjetoPadrao> objs = null;
     private List<ObjetoPadraoSemId> objsemid = null;
     private Usuario compAux = new Usuario();
     private int totalSize = 0;
     private Integer pageSizeBandbox = 5;


     public void doAfterCompose(Component win) throws Exception {
       win.setAttribute("controller",this);
       win.setAttribute("nomeTela", "Usuario");
       win.setAttribute("istemfilha", true);
       super.doAfterCompose(win,true,"all");
       btInformacoes.setVisible(false);
     }

     public void renderizarListaPrincipal() {
       listbox.setItemRenderer(new ListitemRenderer() {
         public void render(Listitem arg0, Object arg1) throws Exception {
           Usuario m = (Usuario) arg1;
           Listcell lc = new Listcell();
           lc.appendChild(new Checkbox());
           arg0.appendChild(lc);
           arg0.appendChild(new Listcell(m.getId().toString()));
           try{
           arg0.appendChild(new Listcell(m.getNome()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
         }
       });
     }

     public void inicializarRelacoesNpara1() {

}


     public void getRelacoesNpara1() {
     }
     public void setRelacoesNpara1() {
     }
     public void limparRelacoesNpara1() {
     }

     public boolean isValidForm() {
       boolean ret = true;
       valid = 0;
       if((usuario.getNome()==null)||(usuario.getNome().equals(""))){
         valid = 1;
         ret = false;
       }
       return ret;
     }

     public void onClickbtSalvar() throws InterruptedException {
         Usuario comp = (Usuario) btSalvar(usuario, usuarioService);
      	if(comp!=null){
           usuario = comp;
           }
        else {
         switch (valid) {
           case 1:
             AGFComponente.showMessage("info","Informe o campo: NOME.");
           break;
           }
         }
       }

     public void onOK$auxhead(Event event) {
       compAux = new Usuario();
       String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
       if(idAux.equals("")){
         compAux.setId(0L);
       }else {
         compAux.setId(Long.valueOf(idAux));
       }
       compAux.setNome(((Textbox) auxhead.getFellow("filtroNome")).getValue());
       int totalSize = 0;
       objs = new ArrayList<ObjetoPadrao>();
       objs = usuarioService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
       totalSize = usuarioService.getNumberRecordsFilter(compAux).intValue();
       paginacao.setActivePage(0);
       AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, usuarioService,totalSize,objs);
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, usuarioService, objs,null);
     }

     public void onClickbtNovo() {
       usuario = new Usuario();
       btNovo();
       controllerParecerr.onClickbtLista();
     }

     public void onClickbtCancelar() {
       usuario = (Usuario) btCancelar(usuario, usuarioService);
     }

     public void onClickbtRemover() throws InterruptedException {
       btRemover(usuario, usuarioService);
     }

     public void onClickbtLista() {
       btLista();
       AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, usuarioService, null, null);
       objs = null;
     }


     public void onPaging$paginacao() {
       if(objs!=null){
          objs = usuarioService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
       }
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, usuarioService, objs,null);
     }

     public void carregarObjeto(Object obj) {
       usuario = (Usuario) obj;
       carregarObj(usuario);
       controllerParecerr.onClickbtLista();
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
     
     /**
     * Filhas
     * @author Arthur Freire
     */ 

     private IncludeParecerr controllerParecerr = null;
     private Include includeParecerr;
     
     @Override
     public void setControllerFilha() {
     controllerParecerr = (IncludeParecerr)includeParecerr.getAttribute("controller");
     }
     
     @Override
     public void setObjetosFilha() {
        objetos = new ArrayList<Object>();
        objetos.add(usuario);
        objetos.add(binder);
     controllerParecerr.onInicio(objetos);
     }
     
     /*//////////////////////////Abas//////////////////////////*/ 
     public void onClick$tabParecerr(){
        controllerParecerr.onClickbtLista();
     }


   }
