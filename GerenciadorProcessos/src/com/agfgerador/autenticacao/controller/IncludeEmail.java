 package com.agfgerador.autenticacao.controller;

 import java.util.List;
 import java.util.ArrayList;
 import org.zkoss.zul.Paging;
 import org.zkoss.zk.ui.Component;
 import org.zkoss.zk.ui.event.Event;
 import org.zkoss.zul.ListModel;
 import org.zkoss.zul.ListModelList;
 import org.zkoss.zul.Listcell;
 import org.zkoss.zul.Listitem;
 import org.zkoss.zul.ListitemRenderer;
 import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
 import org.zkoss.zul.Textbox;
 import org.zkoss.zul.Intbox;
 import org.zkoss.zul.Messagebox;
 import org.zkoss.zul.Checkbox;
 import com.agfgerador.compartilhado.util.AGFPaginacao;
 import com.agfgerador.compartilhado.controller.IPaginacao;
 import com.agfgerador.compartilhado.controller.IncludeBase;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
 import com.agfgerador.compartilhado.util.AGFComponente;
 import com.agfgerador.autenticacao.domain.Email;
 import com.agfgerador.autenticacao.service.EmailService;
 import com.agfgerador.autenticacao.domain.Parametros;
 
   public class IncludeEmail extends IncludeBase implements IPaginacao{
    private static final long serialVersionUID = 1L;

     public Email email;
     private Parametros parametros = new Parametros();
     private EmailService emailService;
     private int valid;
     private List<ObjetoPadrao> objs = null;
     private Email compAux = new Email();


     public void doAfterCompose(Component win) throws Exception {
       win.setAttribute("controller",this);
       super.doAfterCompose(win,"all");
       emailService.findAll();
     }

 	@Override
 	public void onInicio(List<Object> objetos) {
 		onInicio();
 		parametros = (Parametros)objetos.get(0);
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
         }
       });
     }

     public void inicializarRelacoesNpara1() {

}


     public void getRelacoesNpara1() {
       if(parametros!=null){
          email.setParametro(new Parametros());
          email.setParametro(parametros);
       }else{
          email.setParametro(null);
       }
     }
     public void setRelacoesNpara1() {
     }
     public void limparRelacoesNpara1() {
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
       /*if((email.getFinalidade()==null)||(email.getFinalidade().equals(""))){
         valid = 6;
         ret = false;
       }*/
       return ret;
     }

     public void onClickbtSalvar() throws InterruptedException {
         Email comp = (Email) btSalvar(email, emailService);
      	if(comp!=null){
           email = comp;
           Integer totalSize = emailService.getNumberRecordsFilter(email).intValue();
           if(totalSize!=null) {
	             if(totalSize>=1) {
	                 barraFerramentasInclude.getFellow("btNovo").setVisible(false);
	             }else {
	            	 barraFerramentasInclude.getFellow("btNovo").setVisible(true);
	             }
           }
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
       try{
         if(parametros.getId()==null){
            ListModel ls = new ListModelList();
            listbox.setModel(ls);
         }else {
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
             compAux.setSenha(((Textbox) auxhead.getFellow("filtroSenha")).getValue());
             compAux.setEmail(((Textbox) auxhead.getFellow("filtroEmail")).getValue());
             compAux.setParametro(new Parametros()); 
             compAux.setParametro(parametros);
             int totalSize = 0;
             objs = new ArrayList<ObjetoPadrao>();
             objs = emailService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
             totalSize = emailService.getNumberRecordsFilter(compAux).intValue();
             paginacao.setActivePage(0);
             AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, emailService,totalSize,objs);
             AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, emailService, objs,null);
          }
       }catch(Exception e){
          ListModel ls = new ListModelList();
          listbox.setModel(ls);
          e.printStackTrace();
       }
     }

     public void onClickbtNovo() {
       if(parametros.getId() == null ){
          try {
             Messagebox.show("Salve o formulário antes de inserir um novo registro");
          } catch (InterruptedException e) {
             e.printStackTrace();
          }
       }else{
          email = new Email();
          btNovo();
       }
     }

     public void onClickbtCancelar() {
       email = (Email) btCancelar(email, emailService);
     }

     public void onClickbtRemover() throws InterruptedException {
       btRemover(email, emailService);
     }

     public void onClickbtLista() {
       btLista();
       try{
         if(parametros.getId()==null){
            ListModel ls = new ListModelList();
            listbox.setModel(ls);
         }else {
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
             compAux.setParametro(parametros);
             Integer totalSize = 0;
             objs = new ArrayList<ObjetoPadrao>();
             objs = emailService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
             totalSize = emailService.getNumberRecordsFilter(compAux).intValue();
             paginacao.setActivePage(0);
             AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, emailService,totalSize,objs);
             AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, emailService, objs,null);
             if(totalSize!=null) {
	             if(totalSize>=1) {
	                 barraFerramentasInclude.getFellow("btNovo").setVisible(false);
	             }else {
	            	 barraFerramentasInclude.getFellow("btNovo").setVisible(true);
	             }
             }
          }
       }catch(Exception e){
          ListModel ls = new ListModelList();
          listbox.setModel(ls);
          e.printStackTrace();
       }
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
     

   }
