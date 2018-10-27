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
import com.agfgerador.compartilhado.controller.ControllerAGF;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Checkbox;
import com.agfgerador.compartilhado.util.AGFPaginacao;
import com.agfgerador.compartilhado.controller.IPaginacao;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.util.AGFComponente;
import com.agfgerador.autenticacao.domain.Permissao;
import com.agfgerador.autenticacao.service.PermissaoService;
import com.agfgerador.autenticacao.domain.Perfil;
import com.agfgerador.autenticacao.service.PerfilService;
import org.zkoss.zul.Listbox;
import com.agfgerador.compartilhado.util.AGFBandbox;
import org.zkoss.zul.Bandbox;
import com.agfgerador.autenticacao.domain.Componente;
import com.agfgerador.autenticacao.service.ComponenteService;
import com.agfgerador.autenticacao.domain.Tipomenu;
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbarbutton;

public class PermissaoController extends ControllerAGF implements IPaginacao{
	private static final long serialVersionUID = 1L;

	public Permissao permissao;
	private PermissaoService permissaoService;
	private int valid;
	private List<ObjetoPadrao> objs = null;
	private Permissao compAux = new Permissao();
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


	/////////////Componente
	Componente componente = new Componente();
	private Bandbox bandboxComponente;
	private Longbox longboxComponente;
	private Listbox listboxComponente;
	private Paging paginacaoComponente;
	private Componente componenteBandbox;
	private ComponenteService componenteService;

	private Label labelNomePoup;
	private Toolbarbutton toolbarButton;
	private Checkbox checkboxAtivo;
	private Checkbox checkboxAtalho;
	private Checkbox checkboxSomenteleitura;
	private Checkbox checkboxExcluir;

	public void doAfterCompose(Component win) throws Exception {
		win.setAttribute("controller",this);
		win.setAttribute("nomeTela", "Permissao");
		super.doAfterCompose(win,true,"all");
		renderizarBandboxPerfil();
		renderizarBandboxComponente();
		btInformacoes.setVisible(false);
	}

	public void renderizarListaPrincipal() {
		listbox.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem arg0, Object arg1) throws Exception {
				Permissao m = (Permissao) arg1;
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
					arg0.appendChild(new Listcell(m.getComponente().getDescricao()));
				}catch(Exception e){
					arg0.appendChild(new Listcell("")); 
				}
				try{
					arg0.appendChild(new Listcell((m.isAtivo() == true ? "Ativo" : "Inativo")));
				}catch(Exception e){
					arg0.appendChild(new Listcell("")); 
				}
				try{
					arg0.appendChild(new Listcell((m.isAtalho() == true ? "Ativo" : "Inativo")));
				}catch(Exception e){
					arg0.appendChild(new Listcell("")); 
				}
				try{
					arg0.appendChild(new Listcell((m.isSomenteLeitura() == true ? "Ativo" : "Inativo")));
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
	 * BandBox Componente - Arthur Freire 
	 */ 
	public void onClick$labelComponente(){
		labelLink("componente", null);
	}

	public void onOK$longboxComponente(){
		componente = new Componente();
		setCompComponente((Componente)AGFBandbox.onOKLongbox(longboxComponente, componenteBandbox, componenteService));
	}

	public void onOK$bandboxComponente(){
		onChange$bandboxComponente();
	}

	public void onChange$bandboxComponente(){
		componente.setDescricao(bandboxComponente.getValue());
		inicRecepComponente();
		objs = componenteService.filter(componente, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
		totalSize = componenteService.getNumberRecordsFilter(componente).intValue();
		AGFBandbox.onChange(bandboxComponente, listboxComponente, componenteService, paginacaoComponente, pageSizeBandbox, totalSize, objs);
	}

	public void onOpen$bandboxComponente(){
		componente.setDescricao(null);
		listaComponentes();
	}

	public void onClick$listboxComponente(){	
		componente.setDescricao(null);
		setCompComponente((Componente)AGFBandbox.onClickList(bandboxComponente, componenteBandbox, listboxComponente));
	}

	public void listaComponentes(){
		inicRecepComponente();
		objs = componenteService.filter(componente, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
		totalSize = componenteService.getNumberRecordsFilter(componente).intValue();
		AGFBandbox.listaElementos(listboxComponente, componenteService, paginacaoComponente, pageSizeBandbox, totalSize, objs);
	}

	public void onPaging$paginacaoComponente(){
		inicRecepComponente();
		objs = componenteService.filter(componente, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoComponente,pageSizeBandbox,paginaAnterior));
		AGFBandbox.onPaging(null, listboxComponente, componenteService, paginacaoComponente, pageSizeBandbox, paginaAnterior, objs);
	}

	public void inicRecepComponente(){
		componente.setId(0l);
		componente.setTipomenu(new Tipomenu());
		componente.getTipomenu().setNome(null);
		componente.getTipomenu().setId(0l);
		totalSize = 0;
		objs = new ArrayList<ObjetoPadrao>();
	}

	public void setCompComponente(Componente p){
		if(p!=null){	
			componenteBandbox = p;
			bandboxComponente.setValue(componenteBandbox.getDescricao()); 
			longboxComponente.setValue(componenteBandbox.getId());
		}else{
			componenteBandbox = null;
			bandboxComponente.setValue(" "); 
			longboxComponente.setValue(null);
			bandboxComponente.setValue(null); 
		}
	}

	public void renderizarBandboxComponente(){
		listboxComponente.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem arg0, Object arg1) throws Exception {
				Componente m = (Componente) arg1;
				arg0.appendChild(new Listcell(m.getId().toString()));

				try{
					arg0.appendChild(new Listcell(m.getDescricao()));
				}catch(Exception e){
					arg0.appendChild(new Listcell("")); 
				}
				try{
					arg0.appendChild(new Listcell(m.getInformacao()));
				}catch(Exception e){
					arg0.appendChild(new Listcell("")); 
				}
				try{
					arg0.appendChild(new Listcell(m.getMenu()));
				}catch(Exception e){
					arg0.appendChild(new Listcell("")); 
				}
				try{
					arg0.appendChild(new Listcell(m.getTipomenu().getNome().toString()));
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
			permissao.setPerfil(perfilBandbox);
		else
			permissao.setPerfil(null);

		if(componenteBandbox!=null)
			permissao.setComponente(componenteBandbox);
		else
			permissao.setComponente(null);

		permissao.setAtivo(checkboxAtivo.isChecked());
		permissao.setAtalho(checkboxAtalho.isChecked());
		permissao.setSomenteLeitura(checkboxSomenteleitura.isChecked());
		permissao.setExcluir(checkboxExcluir.isChecked());
		permissao.setControle1(false);
		permissao.setControle2(false);
		permissao.setControle3(false);
		permissao.setControle4(false);
		permissao.setControle5(false);
	}
	public void setRelacoesNpara1() {
		setCompPerfil(permissao.getPerfil());
		setCompComponente(permissao.getComponente());
		checkboxAtivo.setChecked(permissao.isAtivo());
		checkboxAtalho.setChecked(permissao.isAtalho());
		checkboxSomenteleitura.setChecked(permissao.isSomenteLeitura());
		checkboxExcluir.setChecked(permissao.isExcluir());
	}
	public void limparRelacoesNpara1() {
		setCompPerfil(null);
		setCompComponente(null);
		checkboxAtivo.setChecked(false);
		checkboxAtalho.setChecked(false);
		checkboxSomenteleitura.setChecked(false);
		checkboxExcluir.setChecked(false);
	}

	public boolean isValidForm() {
		boolean ret = true;
		valid = 0;
		if(permissao.getPerfil()==null){
			valid = 1;
			ret = false;
		}
		if(permissao.getComponente()==null){
			valid = 2;
			ret = false;
		}
		return ret;
	}

	public void onClickbtSalvar() throws InterruptedException {
		Permissao comp = (Permissao) btSalvar(permissao, permissaoService);
		if(comp!=null){
			permissao = comp;
		}
		else {
			switch (valid) {
			case 1:
				AGFComponente.showMessage("info","Informe o campo: PERFIL.");
				break;
			case 2:
				AGFComponente.showMessage("info","Informe o campo: COMPONENTES DO MENU.");
				break;
			}
		}
	}

	public void onOK$auxhead(Event event) {
		compAux = new Permissao();
		String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
		if(idAux.equals("")){
			compAux.setId(0L);
		}else {
			compAux.setId(Long.valueOf(idAux));
		}
		compAux.setPerfil(new Perfil()); 
		compAux.getPerfil().setNome(((Textbox) auxhead.getFellow("filtroPerfil")).getValue());
		compAux.getPerfil().setId(0l);
		compAux.setComponente(new Componente()); 
		compAux.getComponente().setDescricao(((Textbox) auxhead.getFellow("filtroComponente")).getValue());
		compAux.getComponente().setId(0l);
		String ativo = ((Textbox) auxhead.getFellow("filtroAtivo")).getValue();
		compAux.setAtivo(ativo.equalsIgnoreCase("") ? null : ativo.equalsIgnoreCase("Ativo")? true : false);
		String atalho = ((Textbox) auxhead.getFellow("filtroAtalho")).getValue();
		compAux.setAtalho(atalho.equalsIgnoreCase("") ? null : atalho.equalsIgnoreCase("Ativo")? true : false);
		String somenteleitura = ((Textbox) auxhead.getFellow("filtroSomenteleitura")).getValue();
		compAux.setSomenteLeitura(somenteleitura.equalsIgnoreCase("") ? null : somenteleitura.equalsIgnoreCase("Ativo")? true : false);
		int totalSize = 0;
		objs = new ArrayList<ObjetoPadrao>();
		objs = permissaoService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
		totalSize = permissaoService.getNumberRecordsFilter(compAux).intValue();
		paginacao.setActivePage(0);
		AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, permissaoService,totalSize,objs);
		AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, permissaoService, objs,null);
	}

	public void onClickbtNovo() {
		permissao = new Permissao();
		btNovo();
	}

	public void onClickbtCancelar() {
		permissao = (Permissao) btCancelar(permissao, permissaoService);
	}

	public void onClickbtRemover() throws InterruptedException {
		btRemover(permissao, permissaoService);
	}

	public void onClickbtLista() {
		btLista();
		AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, permissaoService, null, null);
		objs = null;
	}


	public void onPaging$paginacao() {
		if(objs!=null){
			objs = permissaoService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
		}
		AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, permissaoService, objs,null);
	}

	public void carregarObjeto(Object obj) {
		permissao = (Permissao) obj;
		carregarObj(permissao);
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

	public void onClick$aInfocomponente(){
		labelNomePoup.setValue("COMPONENTES DO MENU");
		toolbarButton.setLabel("Selecione Menu ou Componente");
	}

	public void onClick$aInfoatalho(){
		labelNomePoup.setValue("ATALHO");
		toolbarButton.setLabel("Se for marcado como atalho o componente irá aparecer na tela inicial do programa");
	}

	public void onClick$aInfosomenteleitura(){
		labelNomePoup.setValue("SOMENTE LEITURA");
		toolbarButton.setLabel("Se for selecionado ficará somente para leitura dos itens da pagina.");
	}

	public void onClick$aInfoexcluir(){
		labelNomePoup.setValue("Excluir");
		toolbarButton.setLabel("Se for selecionado o usuário poderá excluir o Objeto.");
	}


	public void setControllerFilha() {

	}

	public void setObjetosFilha() {

	}

}
