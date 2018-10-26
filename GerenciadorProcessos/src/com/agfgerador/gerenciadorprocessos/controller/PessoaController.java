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
import com.agfgerador.compartilhado.util.AGFUtil;
import com.agfgerador.compartilhado.controller.IPaginacao;
 import com.agfgerador.compartilhado.controller.IFilhas;
 import com.agfgerador.compartilhado.domain.ObjetoPadrao;
 import com.agfgerador.compartilhado.util.AGFComponente;
 import com.agfgerador.gerenciadorprocessos.domain.Pessoa;
 import com.agfgerador.gerenciadorprocessos.service.PessoaService;
 import org.zkoss.zul.Listbox;
 import com.agfgerador.compartilhado.util.AGFBandbox;
 import org.zkoss.zul.Bandbox;
 import org.zkoss.zul.Label;
 import org.zkoss.zul.Toolbarbutton;

   public class PessoaController extends ControllerAGF implements IPaginacao,IModal,ICam{
    private static final long serialVersionUID = 1L;

     public Pessoa pessoa;
     private PessoaService pessoaService;
     private int valid;
     private List<ObjetoPadrao> objs = null;
     private List<ObjetoPadraoSemId> objsemid = null;
     private Pessoa compAux = new Pessoa();
     private int totalSize = 0;
     private Integer pageSizeBandbox = 5;
 
    private Label labelNomePoup;
    private Toolbarbutton toolbarButton;
    private AGFModal mbmodal;
    private Image imagem;
    private Div pics0;
     private Datebox dateboxData;

     public void doAfterCompose(Component win) throws Exception {
       win.setAttribute("controller",this);
       win.setAttribute("nomeTela", "Pessoa");
       mbmodal = new AGFModal();
       super.doAfterCompose(win,true,"all");
       btInformacoes.setVisible(false);
     }

     public void renderizarListaPrincipal() {
       listbox.setItemRenderer(new ListitemRenderer() {
         public void render(Listitem arg0, Object arg1) throws Exception {
           Pessoa m = (Pessoa) arg1;
           Listcell lc = new Listcell();
           lc.appendChild(new Checkbox());
           arg0.appendChild(lc);
           arg0.appendChild(new Listcell(m.getId().toString()));
           try{
           imagem = new Image();
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
	       try{
	          arg0.appendChild(new Listcell(m.getCpf()));
	       }catch(Exception e){
	          arg0.appendChild(new Listcell("")); 
	       }
         }
       });
     }

     public void inicializarRelacoesNpara1() {
       imagem = new Image();
       imagem.setVisible(false);

}
     
     private void inicializaElementosImagem(){
        imagem.setParent(pics0);
        imagem.setHeight("100px");
        imagem.setWidth("100px");
     }
     /**
      * Upload da foto de uma pessoa
      * @author Arthur Freire/
      */
     public void onUpload$btAlterarImagem(){
        Media media = (Media) window.getAttribute("media");
        pics0.getChildren().clear();
        Map<String,Object> ret = AGFComponente.uploadImage(media, imagem,102400, pics0);
        imagem.setStyle("display: flex;");
        String erro = (String) ret.get("erro");
        imagem = (Image) ret.get("imagem");
        if(imagem==null){
           try {
              Messagebox.show(erro,"Error", Messagebox.OK, Messagebox.ERROR);
           }catch (InterruptedException e) {
             e.printStackTrace();
           }
        }
     }

     public void getRelacoesNpara1() {
       
       if(imagem.getContent()!=null){
          byte[] bra = AGFImagem .converterImageToByte(imagem);
          pessoa.setImagem(bra);
       }else{           pessoa.setImagem(null);
       }
       pessoa.setData(dateboxData.getValue());
     }
     public void setRelacoesNpara1() {
       pics0.getChildren().clear();
       imagem.setParent(pics0);
       imagem.setVisible(false);
       try{
          if(pessoa.getImagem()!= null){
             imagem.setContent(AGFImagem.converterByteToBufferedImage(pessoa.getImagem()));
             imagem.setVisible(true);
          }else{
             imagem.setSrc(null);
             imagem.setVisible(false);
          }
       }catch(Exception e){
          e.printStackTrace();
       }
    inicializaElementosImagem();
       dateboxData.setValue(pessoa.getData());
     }
     public void limparRelacoesNpara1() {

       imagem = new Image();
       imagem.setSrc(null);
       pics0.getChildren().clear();
     }

     public boolean isValidForm() {
       boolean ret = true;
       valid = 0;
       if((pessoa.getNome()==null)||(pessoa.getNome().equals(""))){
         valid = 3;
         ret = false;
       }else if ((pessoa.getCpf()!=null)&&(!AGFUtil.isValidCPF(pessoa.getCpf()))){
			valid = 5;
			ret = false;
		}
       return ret;
     }

     public void onClickbtSalvar() throws InterruptedException {
         Pessoa comp = (Pessoa) btSalvar(pessoa, pessoaService);
      	if(comp!=null){
           pessoa = comp;
           }
        else {
         switch (valid) {
           case 3:
             AGFComponente.showMessage("info","Informe o campo: NOME.");
           break;  
			case 5:
				AGFComponente.showMessage("alerta","Informe o CPF válido para o campo: CPF.");
		    break;
           }
         }
       }

     public void onOK$auxhead(Event event) {
       compAux = new Pessoa();
       String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
       if(idAux.equals("")){
         compAux.setId(0L);
       }else {
         compAux.setId(Long.valueOf(idAux));
       }
       compAux.setNome(((Textbox) auxhead.getFellow("filtroNome")).getValue());
       compAux.setData(null);
       compAux.setCpf(((Textbox) auxhead.getFellow("filtroCpf")).getValue());
       int totalSize = 0;
       objs = new ArrayList<ObjetoPadrao>();
       objs = pessoaService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
       totalSize = pessoaService.getNumberRecordsFilter(compAux).intValue();
       paginacao.setActivePage(0);
       AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, pessoaService,totalSize,objs);
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, pessoaService, objs,null);
     }

     public void onClickbtNovo() {
       pessoa = new Pessoa();
       btNovo();
     }

     public void onClickbtCancelar() {
       pessoa = (Pessoa) btCancelar(pessoa, pessoaService);
     }

     public void onClickbtRemover() throws InterruptedException {
       btRemover(pessoa, pessoaService);
     }

     public void onClickbtLista() {
       btLista();
       AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, pessoaService, null, null);
       objs = null;
     }


     public void onPaging$paginacao() {
       if(objs!=null){
          objs = pessoaService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
       }
       AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, pessoaService, objs,null);
     }

     public void carregarObjeto(Object obj) {
       pessoa = (Pessoa) obj;
       carregarObj(pessoa);
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
     
     @Override
     public void setImageWebCam(Image img, BufferedImage imgb) {
        Clients.evalJavaScript("setImagem('"+img.getUuid()+"', '"+imagem.getUuid()+"')");
        pics0.getChildren().clear();
        imagem.setParent(pics0);
        imagem.setContent(imgb);
        imagem.setVisible(true);
     }

     @Override
     public void onModal(){
        barraFerramentasButtons = new JSONArray();
        btSalvar = (A) barraFerramentas.getFellow("btSalvar");
        btSalvar.addForward("onClick", self, "onClickbtSalvar");
        barraFerramentasButtons = AGFJs.addItemBF(barraFerramentasButtons, btSalvar, "form");
        onClickbtNovo();
     }

     public void onClick$btWebCam(){
        try {
           mbmodal.createModal("/view/compartilhado/view/cammanage.zul", "center", "WebCam", "400px",null,(Window)window);
        } catch (Exception e) {
           e.printStackTrace();
        }
     }

     public void onClick$aInfotipopessoa(){
        labelNomePoup.setValue("FÍSICA / JURÍDICA");
        toolbarButton.setLabel("Informar se a Pessoa é Física / Jurídica");
     }
 
     public void onClick$aInfocep(){
        labelNomePoup.setValue("CEP");
        toolbarButton.setLabel("Pressione enter para completar os dados do endereço automaticamente. Obs: Só será realizado caso o CEP esteja correto.");
     }
 

     public void setControllerFilha() {

     }

     public void setObjetosFilha() {

     }

   }
