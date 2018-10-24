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
import com.agfgerador.autenticacao.domain.TipoUsuario;
import com.agfgerador.autenticacao.domain.Usuario;
 import com.agfgerador.autenticacao.service.UsuarioService;
 import org.zkoss.zul.Label;
 import org.zkoss.zul.Toolbarbutton;

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
     private String senhaAntiga;

 private Label labelNomePoup;
 private Toolbarbutton toolbarButton;
    private Checkbox checkboxHabilitado;

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

     public void inicializarRelacoesNpara1() {

}


     public void getRelacoesNpara1() {
       usuario.setHabilitado(checkboxHabilitado.isChecked());
       
       usuario.setTipo(TipoUsuario.Comum);
       if(usuario.getTelefone()!=null) {
	       if(usuario.getTelefone().replaceAll("_", "").length()<14) {
				usuario.setTelefone("");
	       }else if(usuario.getTelefoneAlternativo().replaceAll("_", "").length()<15) {
	       	usuario.setTelefoneAlternativo("");
	       }
       }
     }
     public void setRelacoesNpara1() {
       checkboxHabilitado.setChecked(usuario.isHabilitado());
       senhaAntiga = usuario.getSenha();
     }
     public void limparRelacoesNpara1() {
       checkboxHabilitado.setChecked(false);
     }

     public boolean isValidForm() {
       boolean ret = true;
       valid = 0;
       if((usuario.getNome()==null)||(usuario.getNome().equals(""))){
         valid = 1;
         ret = false;
       }else if((usuario.getNome().indexOf(" ")<0)||((usuario.getNome().indexOf(" ")+1)==usuario.getNome().length())){
			 valid = 9;
	         ret = false;
	   }else if((usuario.getLogin()==null)||(usuario.getLogin().equals(""))){
         valid = 2;
         ret = false;
       }else if(usuario.getLogin().indexOf(" ")>0||(!AGFUtil.isVeriCaractEspecial(usuario.getLogin()))){
    	   valid = 5;
			ret = false;
	   }else if(usuario.getLogin().length()<6){
			valid = 6;
			ret = false;
	   }else if((usuario.getSenha()==null)||(usuario.getSenha().equals(""))){
         valid = 3;
         ret = false;
       }else if(usuario.getSenha().length()<6) {
    	   valid = 4;
			ret = false;
	   }else if((usuario.getEmail() == null)||(usuario.getEmail().equals(""))||(!AGFUtil.isValidEmail(usuario.getEmail()))){
			valid = 8;
	        ret = false;	
       }
       
       List<ObjetoPadrao> obs = new ArrayList<>();
		if(ret == true) {
			Usuario usu = new Usuario();
			usu.setId(0l);
			usu.setNome(null);
			usu.setLogin(usuario.getLogin());
			usu.setEmail(null);	
			usu.setHabilitado(null);
			obs = usuarioService.filter(usu);
			if(obs.size()>0) {
				if(obs.get(0).getId()!=usuario.getId()) {
					valid = 12;
					ret = false;
				}
			}else {
				usu.setLogin(null);
				usu.setEmail(usuario.getEmail());
				obs = new ArrayList<>();
				obs = usuarioService.filter(usu);
				if(obs.size()>0) {
					if(obs.get(0).getId()!=usuario.getId()) {
						valid = 13;
						ret = false;
					}
				}
			 }
		  }
		
		if(ret == true){
			if(usuario.getId()==null) {
				if(usuario.getSenha()!=null) {
				       if((!usuario.getSenha().equals(""))&&((usuario.getSenha().length()>5))){
				       usuario.setSenha(AGFUtil.MD5(usuario.getSenha()));
				       }
			    }
			}else {
				if(usuario.getSenha()!=null) {
					if(senhaAntiga!=usuario.getSenha()) {
						usuario.setSenha(AGFUtil.MD5(usuario.getSenha()));
					}
				}
				
			}
		}
       
       return ret;
     }

     public void onClickbtSalvar() throws InterruptedException {
         Usuario comp = (Usuario) btSalvar(usuario, usuarioService);
      	if(comp!=null){
           usuario = comp;
           senhaAntiga = usuario.getSenha();
           }
        else {
         switch (valid) {
           case 1:
             AGFComponente.showMessage("info","Informe o campo: NOME.");
           break;
           case 2:
             AGFComponente.showMessage("info","Informe o campo: LOGIN.");
           break;
           case 3:
             AGFComponente.showMessage("info","Informe o campo: SENHA.");
           break;
           case 4:
               AGFComponente.showMessage("info","SENHA não pode ter menos que 6 caracteres");
             break;
           case 5:
               AGFComponente.showMessage("info","LOGIN não pode ter espaços em branco ou caracteres especias");
             break;
           case 6:
               AGFComponente.showMessage("info","LOGIN não pode ter menos que 6 caracteres");
             break;
           /*case 7:
             AGFComponente.showMessage("info","Informe o campo: CELULAR.");
             break;*/
           case 8:
               AGFComponente.showMessage("info","E-MAIL inválido.");
             break;
           case 9:
               AGFComponente.showMessage("info","Campo NOME tem que ter um Nome e um Sobrenome.");
             break;
           case 10:
               AGFComponente.showMessage("info","Campo TELEFONE tem que ser preenchido de forma correta.");
             break;
           case 11:
               AGFComponente.showMessage("info","Campo Celular tem que ser preenchido de forma correta.");
             break;
           case 12:
               AGFComponente.showMessage("info","LOGIN já cadastrado.");
             break;
           case 13:
               AGFComponente.showMessage("info","E-mail já em uso.");
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
       compAux.setLogin(((Textbox) auxhead.getFellow("filtroLogin")).getValue());
       String habilitado = ((Textbox) auxhead.getFellow("filtroHabilitado")).getValue();
       compAux.setHabilitado(habilitado.equalsIgnoreCase("") ? null : habilitado.equalsIgnoreCase("Ativo")? true : false);
       compAux.setEmail(((Textbox) auxhead.getFellow("filtroEmail")).getValue());
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
       controllerUsuarioperfil.onClickbtLista();
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
       controllerUsuarioperfil.onClickbtLista();
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
     
        public void onClick$aInfonome(){
           labelNomePoup.setValue("NOME");
           toolbarButton.setLabel("nome do usuário");
        }
 
        public void onClick$aInfologin(){
           labelNomePoup.setValue("LOGIN");
           toolbarButton.setLabel("Login pra entra no sistema");
        }
 
        public void onClick$aInfosenha(){
           labelNomePoup.setValue("SENHA");
           toolbarButton.setLabel("senha que o usuário irá usar para o login no sistema");
        }
 
        public void onClick$aInfohabilitado(){
           labelNomePoup.setValue("HABILITADO");
           toolbarButton.setLabel("Se esse usuário está habilitado ou não para usar o sistema.");
        }
 
        public void onClick$aInfoemail(){
           labelNomePoup.setValue("EMAIL");
           toolbarButton.setLabel("Email do usuário");
        }
 
        public void onClick$aInfotelefone(){
           labelNomePoup.setValue("TELEFONE");
           toolbarButton.setLabel("Telefone residencial");
        }
 
        public void onClick$aInfotelefonealternativo(){
           labelNomePoup.setValue("CELULAR");
           toolbarButton.setLabel("Informar o celular");
        }
 
     /**
     * Filhas
     * @author Arthur Freire
     */ 

     private IncludeUsuarioperfil controllerUsuarioperfil = null;
     private Include includeUsuarioperfil;
     
     @Override
     public void setControllerFilha() {
     controllerUsuarioperfil = (IncludeUsuarioperfil)includeUsuarioperfil.getAttribute("controller");
     }
     
     @Override
     public void setObjetosFilha() {
        objetos = new ArrayList<Object>();
        objetos.add(usuario);
        objetos.add(binder);
     controllerUsuarioperfil.onInicio(objetos);
     }
     
     /*//////////////////////////Abas//////////////////////////*/ 
     public void onClick$tabUsuarioperfil(){
        controllerUsuarioperfil.onClickbtLista();
     }


   }
