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
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import com.agfgerador.compartilhado.controller.ControllerAGF;
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
import org.zkoss.zul.Checkbox;
import com.agfgerador.compartilhado.util.AGFPaginacao;
import com.agfgerador.compartilhado.util.AGFUtil;
import com.agfgerador.compartilhado.controller.IPaginacao;
import com.agfgerador.compartilhado.controller.IFilhas;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.util.AGFComponente;
import com.agfgerador.autenticacao.domain.Parametros;
import com.agfgerador.autenticacao.service.ParametrosService;
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbarbutton;

public class ParametrosController extends ControllerAGF implements IPaginacao,IFilhas,IModal,ICam{
	private static final long serialVersionUID = 1L;

	public Parametros parametros;
	private ParametrosService parametrosService;
	private int valid;
	private List<ObjetoPadrao> objs = null;
	private Parametros compAux = new Parametros();

	private AGFModal mbmodal;
	private Image brasao;
	private Div pics0;
	private Label labelNomePoup;
	private Toolbarbutton toolbarButton;

	public void doAfterCompose(Component win) throws Exception {
		win.setAttribute("controller",this);
		win.setAttribute("nomeTela", "Parametros");
		win.setAttribute("istemfilha", true);
		mbmodal = new AGFModal();
		super.doAfterCompose(win,true,"all");
		btInformacoes.setVisible(false);
		btRemover.setVisible(false);
	}

	public void renderizarListaPrincipal() {
		listbox.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem arg0, Object arg1) throws Exception {
				Parametros m = (Parametros) arg1;
				Listcell lc = new Listcell();
				lc.appendChild(new Checkbox());
				arg0.appendChild(lc);
				arg0.appendChild(new Listcell(m.getId().toString()));
				try{
					brasao = new Image();
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

	public void inicializarRelacoesNpara1() {
		brasao = new Image();
		brasao.setVisible(false);

	}

	private void inicializaElementosBrasao(){
		brasao.setParent(pics0);
		brasao.setHeight("100px");
		brasao.setWidth("100px");
	}
	/**
	 * Upload da foto de uma pessoa
	 * @author Arthur Freire/
	 */
	public void onUpload$btAlterarBrasao(){
		Media media = (Media) window.getAttribute("media");
		pics0.getChildren().clear();
		Map<String,Object> ret = AGFComponente.uploadImage(media, brasao,102400, pics0);
		brasao.setStyle("display: flex;");
		String erro = (String) ret.get("erro");
		brasao = (Image) ret.get("imagem");
		if(brasao==null){
			try {
				Messagebox.show(erro,"Error", Messagebox.OK, Messagebox.ERROR);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void getRelacoesNpara1() {
		if(brasao.getContent()!=null){
			byte[] bra = AGFImagem .converterImageToByte(brasao);
			parametros.setBrasao(bra);
		}else{           parametros.setBrasao(null);
		}
	}
	public void setRelacoesNpara1() {
		pics0.getChildren().clear();
		brasao.setParent(pics0);
		brasao.setVisible(false);
		try{
			if(parametros.getBrasao()!= null){
				brasao.setContent(AGFImagem.converterByteToBufferedImage(parametros.getBrasao()));
				brasao.setVisible(true);
			}else{
				brasao.setSrc(null);
				brasao.setVisible(false);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		inicializaElementosBrasao();
	}
	public void limparRelacoesNpara1() {
		brasao = new Image();
		brasao.setSrc(null);
		pics0.getChildren().clear();
	}

	public boolean isValidForm() {
		boolean ret = true;
		valid = 0;
		if((parametros.getNome()==null)||(parametros.getNome().equals(""))){
			valid = 2;
			ret = false;
		}
		if((parametros.getNomereduzido()==null)||(parametros.getNomereduzido().equals(""))){
			valid = 3;
			ret = false;
		}
		System.out.println("cnpj valido: "+AGFUtil.isValidCNPJ(parametros.getCnpj()));
		if((parametros.getCnpj()==null)||(parametros.getCnpj().equals(""))||(!AGFUtil.isValidCNPJ(parametros.getCnpj()))){
			valid = 4;
			ret = false;
		}
		return ret;
	}

	public void onClickbtSalvar() throws InterruptedException {
		Parametros comp = (Parametros) btSalvar(parametros, parametrosService);
		if(comp!=null){
			parametros = comp;
			Long total = parametrosService.getNumberRecords();
			if(total!=null)
				if(total>=1) {
					btNovo.setVisible(false);
				}else {
					btNovo.setVisible(true);
				}
		}
		else {
			switch (valid) {
			case 2:
				AGFComponente.showMessage("info","Informe o campo: NOME.");
				break;
			case 3:
				AGFComponente.showMessage("info","Informe o campo: NOME REDUZIDO.");
				break;
			case 4:
				AGFComponente.showMessage("info","Informe o campo: CNPJ corretamente.");
				break;
			}
		}
	}

	public void onOK$auxhead(Event event) {
		compAux = new Parametros();
		String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
		if(idAux.equals("")){
			compAux.setId(0L);
		}else {
			compAux.setId(Long.valueOf(idAux));
		}
		compAux.setNome(((Textbox) auxhead.getFellow("filtroNome")).getValue());
		compAux.setNomereduzido(((Textbox) auxhead.getFellow("filtroNomereduzido")).getValue());
		compAux.setCnpj(((Textbox) auxhead.getFellow("filtroCnpj")).getValue());
		int totalSize = 0;
		objs = new ArrayList<ObjetoPadrao>();
		objs = parametrosService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
		totalSize = parametrosService.getNumberRecordsFilter(compAux).intValue();
		paginacao.setActivePage(0);
		AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, parametrosService,totalSize,objs);
		AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, parametrosService, objs,null);
	}

	public void onClickbtNovo() {
		parametros = new Parametros();
		btNovo();
		controllerEmail.onClickbtLista();
	}

	public void onClickbtCancelar() {
		parametros = (Parametros) btCancelar(parametros, parametrosService);
	}

	public void onClickbtRemover() throws InterruptedException {
		btRemover(parametros, parametrosService);
	}

	public void onClickbtLista() {
		btLista();
		AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, parametrosService, null, null);       
		objs = null;
		Long total = parametrosService.getNumberRecords();
		if(total!=null) {
			if(total>=1) {
				btNovo.setVisible(false);
				btRemover.setVisible(false);
			}else {
				btNovo.setVisible(true);
			}
		}
	}


	public void onPaging$paginacao() {
		if(objs!=null){
			objs = parametrosService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
		}
		AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, parametrosService, objs,null);
	}

	public void carregarObjeto(Object obj) {
		parametros = (Parametros) obj;
		carregarObj(parametros);
		controllerEmail.onClickbtLista();
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
		Clients.evalJavaScript("setBrasao('"+img.getUuid()+"', '"+brasao.getUuid()+"')");
		pics0.getChildren().clear();
		brasao.setParent(pics0);
		brasao.setContent(imgb);
		brasao.setVisible(true);
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

	public void onClick$aInfonome(){
		labelNomePoup.setValue("NOME");
		toolbarButton.setLabel("Nome completo da Razão social");
	}

	public void onClick$aInfonomereduzido(){
		labelNomePoup.setValue("NOME REDUZIDO");
		toolbarButton.setLabel("Nome Reduzido da Razão Social, esse nome ficará na pagina de inicio.");
	}

	/**
	 * Filhas
	 * @author Arthur Freire
	 */ 

	private IncludeEmail controllerEmail = null;
	private Include includeEmail;

	@Override
	public void setControllerFilha() {
		controllerEmail = (IncludeEmail)includeEmail.getAttribute("controller");
	}

	@Override
	public void setObjetosFilha() {
		objetos = new ArrayList<Object>();
		objetos.add(parametros);
		objetos.add(binder);
		controllerEmail.onInicio(objetos);
	}

	/*//////////////////////////Abas//////////////////////////*/ 
	public void onClick$tabEmail(){
		controllerEmail.onClickbtLista();
	}


}
