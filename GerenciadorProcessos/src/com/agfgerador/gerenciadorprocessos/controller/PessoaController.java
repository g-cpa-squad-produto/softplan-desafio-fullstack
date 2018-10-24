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
 import com.agfgerador.gerenciadorprocessos.domain.Pessoa;
 import com.agfgerador.gerenciadorprocessos.service.PessoaService;
 import com.agfgerador.gerenciadorprocessos.domain.Tipopessoa;
 import com.agfgerador.gerenciadorprocessos.service.TipopessoaService;
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

   
    /////////////Tipopessoa
    Tipopessoa tipopessoa = new Tipopessoa();
    private Bandbox bandboxTipopessoa;
    private Longbox longboxTipopessoa;
    private Listbox listboxTipopessoa;
    private Paging paginacaoTipopessoa;
    private Tipopessoa tipopessoaBandbox;
    private TipopessoaService tipopessoaService;
 
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
       renderizarBandboxTipopessoa();
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
           arg0.appendChild(new Listcell(m.getTipopessoa().getDescricao()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
           }
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
           arg0.appendChild(new Listcell(m.getUf()));
           }catch(Exception e){
              arg0.appendChild(new Listcell("")); 
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

     public void inicializarRelacoesNpara1() {
       imagem = new Image();
       imagem.setVisible(false);

}

     /* 
      * BandBox Tipopessoa - Arthur Freire 
      */ 
     public void onClick$labelTipopessoa(){
      labelLink("tipopessoa", null);
     }
     
     public void onOK$longboxTipopessoa(){
        tipopessoa = new Tipopessoa();
        setCompTipopessoa((Tipopessoa)AGFBandbox.onOKLongbox(longboxTipopessoa, tipopessoaBandbox, tipopessoaService));
     }
     
     public void onOK$bandboxTipopessoa(){
        onChange$bandboxTipopessoa();
     }
     
     public void onChange$bandboxTipopessoa(){
        tipopessoa.setDescricao(bandboxTipopessoa.getValue());
        inicRecepTipopessoa();
        objs = tipopessoaService.filter(tipopessoa, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = tipopessoaService.getNumberRecordsFilter(tipopessoa).intValue();
        AGFBandbox.onChange(bandboxTipopessoa, listboxTipopessoa, tipopessoaService, paginacaoTipopessoa, pageSizeBandbox, totalSize, objs);
     }
     
     public void onOpen$bandboxTipopessoa(){
        tipopessoa.setDescricao(null);
        listaTipopessoas();
     }
     
     public void onClick$listboxTipopessoa(){	
        tipopessoa.setDescricao(null);
        setCompTipopessoa((Tipopessoa)AGFBandbox.onClickList(bandboxTipopessoa, tipopessoaBandbox, listboxTipopessoa));
     }
    
     public void listaTipopessoas(){
        inicRecepTipopessoa();
        objs = tipopessoaService.filter(tipopessoa, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
        totalSize = tipopessoaService.getNumberRecordsFilter(tipopessoa).intValue();
        AGFBandbox.listaElementos(listboxTipopessoa, tipopessoaService, paginacaoTipopessoa, pageSizeBandbox, totalSize, objs);
     }
    
     public void onPaging$paginacaoTipopessoa(){
        inicRecepTipopessoa();
        objs = tipopessoaService.filter(tipopessoa, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoTipopessoa,pageSizeBandbox,paginaAnterior));
        AGFBandbox.onPaging(null, listboxTipopessoa, tipopessoaService, paginacaoTipopessoa, pageSizeBandbox, paginaAnterior, objs);
     }
     
     public void inicRecepTipopessoa(){
        tipopessoa.setId(0l);
        totalSize = 0;
        objs = new ArrayList<ObjetoPadrao>();
     }
     
     public void setCompTipopessoa(Tipopessoa p){
        if(p!=null){	
           tipopessoaBandbox = p;
           bandboxTipopessoa.setValue(tipopessoaBandbox.getDescricao()); 
           longboxTipopessoa.setValue(tipopessoaBandbox.getId());
        }else{
	          tipopessoaBandbox = null;
	          bandboxTipopessoa.setValue(" "); 
	          longboxTipopessoa.setValue(null);
	          bandboxTipopessoa.setValue(null); 
        }
	    }
     
     public void renderizarBandboxTipopessoa(){
        listboxTipopessoa.setItemRenderer(new ListitemRenderer() {
           public void render(Listitem arg0, Object arg1) throws Exception {
              Tipopessoa m = (Tipopessoa) arg1;
               arg0.appendChild(new Listcell(m.getId().toString()));
 
                 try{
                    arg0.appendChild(new Listcell(m.getDescricao()));
                 }catch(Exception e){
                    arg0.appendChild(new Listcell("")); 
                 }
           }
        });
     }
 
     /* 
      * Fim BandBox Banco - Arthur Freire 
      */ 
     
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
       if(tipopessoaBandbox!=null)
          pessoa.setTipopessoa(tipopessoaBandbox);
       else
          pessoa.setTipopessoa(null);
       
       if(imagem.getContent()!=null){
          byte[] bra = AGFImagem .converterImageToByte(imagem);
          pessoa.setImagem(bra);
       }else{           pessoa.setImagem(null);
       }
       pessoa.setData(dateboxData.getValue());
     }
     public void setRelacoesNpara1() {
       setCompTipopessoa(pessoa.getTipopessoa());
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
       setCompTipopessoa(null);
       imagem = new Image();
       imagem.setSrc(null);
       pics0.getChildren().clear();
     }

     public boolean isValidForm() {
       boolean ret = true;
       valid = 0;
       if(pessoa.getTipopessoa()==null){
         valid = 1;
         ret = false;
       }
       if((pessoa.getNome()==null)||(pessoa.getNome().equals(""))){
         valid = 3;
         ret = false;
       }
       if((pessoa.getCep()==null)||(pessoa.getCep().equals(""))){
         valid = 7;
         ret = false;
       }
       if((pessoa.getUf()==null)||(pessoa.getUf().equals(""))){
         valid = 8;
         ret = false;
       }
       if((pessoa.getCidade()==null)||(pessoa.getCidade().equals(""))){
         valid = 9;
         ret = false;
       }
       if((pessoa.getTipologradouro()==null)||(pessoa.getTipologradouro().equals(""))){
         valid = 10;
         ret = false;
       }
       if((pessoa.getLogradouro()==null)||(pessoa.getLogradouro().equals(""))){
         valid = 11;
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
           case 1:
             AGFComponente.showMessage("info","Informe o campo: FÍSICA / JURÍDICA.");
           break;
           case 3:
             AGFComponente.showMessage("info","Informe o campo: NOME.");
           break;
           case 7:
             AGFComponente.showMessage("info","Informe o campo: CEP.");
           break;
           case 8:
             AGFComponente.showMessage("info","Informe o campo: UF.");
           break;
           case 9:
             AGFComponente.showMessage("info","Informe o campo: CIDADE.");
           break;
           case 10:
             AGFComponente.showMessage("info","Informe o campo: TIPO LOGRADOURO.");
           break;
           case 11:
             AGFComponente.showMessage("info","Informe o campo: LOGRADOURO.");
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
       compAux.setTipopessoa(new Tipopessoa()); 
       compAux.getTipopessoa().setDescricao(((Textbox) auxhead.getFellow("filtroTipopessoa")).getValue());
       compAux.getTipopessoa().setId(0l);
       compAux.setNome(((Textbox) auxhead.getFellow("filtroNome")).getValue());
       compAux.setData(null);
       compAux.setCpf(((Textbox) auxhead.getFellow("filtroCpfCnpj")).getValue());
       compAux.setCnpj(((Textbox) auxhead.getFellow("filtroCpfCnpj")).getValue());
       compAux.setCep(null);
       compAux.setUf(((Textbox) auxhead.getFellow("filtroUf")).getValue());
       compAux.setCidade(((Textbox) auxhead.getFellow("filtroCidade")).getValue());
       compAux.setLogradouro(((Textbox) auxhead.getFellow("filtroLogradouro")).getValue());
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
