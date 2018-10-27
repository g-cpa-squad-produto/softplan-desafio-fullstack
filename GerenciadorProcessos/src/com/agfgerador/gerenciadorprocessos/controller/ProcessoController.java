package com.agfgerador.gerenciadorprocessos.controller;

import java.util.List;
import java.util.ArrayList;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Tab;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import com.agfgerador.autenticacao.domain.Perfil;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.autenticacao.service.UsuarioPerfilService;
import com.agfgerador.compartilhado.controller.ControllerAGF;
import com.agfgerador.compartilhado.util.AGFImagem;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Checkbox;
import java.text.SimpleDateFormat;
import com.agfgerador.compartilhado.util.AGFPaginacao;
import com.agfgerador.compartilhado.controller.IPaginacao;
import com.agfgerador.compartilhado.controller.IFilhas;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.util.AGFComponente;
import com.agfgerador.gerenciadorprocessos.domain.Processo;
import com.agfgerador.gerenciadorprocessos.service.ProcessoService;
import com.agfgerador.gerenciadorprocessos.domain.Parecer;
import com.agfgerador.gerenciadorprocessos.domain.Pessoa;
import com.agfgerador.gerenciadorprocessos.service.ParecerService;
import com.agfgerador.gerenciadorprocessos.service.PessoaService;
import org.zkoss.zul.Listbox;
import com.agfgerador.compartilhado.util.AGFBandbox;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbarbutton;

/**ProcessoController - Classe para controle das funções da View processomanage.zul.
 * 
 * @author Arthur Freire
 */
public class ProcessoController extends ControllerAGF implements IPaginacao,IFilhas{
	private static final long serialVersionUID = 1L;

	public Processo processo;
	private ProcessoService processoService;
	private int valid;
	private List<ObjetoPadrao> objs = null;
	private Processo compAux = new Processo();
	private int totalSize = 0;
	private Integer pageSizeBandbox = 5, objnull = 0;
	private UsuarioPerfilService usuarioPerfilService;
	private Perfil perfil = new Perfil();
	private Tab tabParecer;
	private ParecerService parecerService;
	private Intbox intboxNumprocesso;

	/////////////Pessoa
	Pessoa pessoa = new Pessoa();
	private Bandbox bandboxPessoa;
	private Longbox longboxPessoa;
	private Listbox listboxPessoa;
	private Paging paginacaoPessoa;
	private Pessoa pessoaBandbox;
	private PessoaService pessoaService;

	private Label labelNomePoup;
	private Toolbarbutton toolbarButton;
	private Datebox dateboxDtabertura;

	/**Inicializa todas as funções da classe controller 
	 * 
	 * @author Arthur Freire
	 * @param win Componente - Dados que será usado na view para iniciar a classe controller. 
	 */
	public void doAfterCompose(Component win) throws Exception {
		win.setAttribute("controller",this);
		win.setAttribute("nomeTela", "Processo");
		win.setAttribute("istemfilha", true);
		super.doAfterCompose(win,true,"all");
		renderizarBandboxPessoa();
		btInformacoes.setVisible(false);
		//perfil = getTriadorFinalizadorGeral();
	}
	/**Método aonde é informado os códigos que insere os valores na listagem da view.
	 * 
	 * @author Arthur Freire      
	 */
	public void renderizarListaPrincipal() {
		listbox.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem arg0, Object arg1) throws Exception {
				Processo m = (Processo) arg1;
				Listcell lc = new Listcell();
				lc.appendChild(new Checkbox());
				arg0.appendChild(lc);
				arg0.appendChild(new Listcell(m.getId().toString()));
				try{
					arg0.appendChild(new Listcell(m.getNumprocesso().toString()));
				}catch(Exception e){
					arg0.appendChild(new Listcell("")); 
				}
				try{
					arg0.appendChild(new Listcell(m.getPessoa().getNome()));
				}catch(Exception e){
					arg0.appendChild(new Listcell("")); 
				}
				try{
					SimpleDateFormat formatodate = new SimpleDateFormat("E dd/MM/yyyy");
					arg0.appendChild(new Listcell(String.valueOf(formatodate.format(m.getDtabertura()))));
				}catch(Exception e){
					arg0.appendChild(new Listcell("")); 
				}
			}
		});
	}

	/**Método aonde é informado os códigos que seram inicializado 
	 * as relações que será usado na formação da view. 
	 * 
	 * @author Arthur Freire
	 */
	public void inicializarRelacoesNpara1() {

	}

	/* 
	 * BandBox Pessoa - Arthur Freire 
	 */ 
	/**Método aonde redireciona para a tela de Pessoa 
	 * 
	 * @author Arthur Freire
	 * @deprecated
	 */
	public void onClick$labelPessoa(){
		labelLink("pessoa", null);
	}

	/**Método que busca pelo id do objeto do Bandbox Pessoa. 
	 * 
	 * @author Arthur Freire
	 */
	public void onOK$longboxPessoa(){
		pessoa = new Pessoa();
		setCompPessoa((Pessoa)AGFBandbox.onOKLongbox(longboxPessoa, pessoaBandbox, pessoaService));
	}

	/**Método ao aperta enter no Bandbox Pessoa. 
	 * 
	 * @author Arthur Freire
	 */
	public void onOK$bandboxPessoa(){
		onChange$bandboxPessoa();
	}
	/**Método quando ouver uma mudança no Bandbox Pessoa. 
	 * 
	 * @author Arthur Freire
	 */
	public void onChange$bandboxPessoa(){
		pessoa.setNome(bandboxPessoa.getValue());
		inicRecepPessoa();
		objs = pessoaService.filter(pessoa, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
		totalSize = pessoaService.getNumberRecordsFilter(pessoa).intValue();
		AGFBandbox.onChange(bandboxPessoa, listboxPessoa, pessoaService, paginacaoPessoa, pageSizeBandbox, totalSize, objs);
	}
	/**Método quando abrir Bandbox Pessoa. 
	 * 
	 * @author Arthur Freire
	 */
	public void onOpen$bandboxPessoa(){
		pessoa.setNome(null);
		listaPessoas();
	}
	/**Método ao clicar Bandbox Pessoa. 
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$listboxPessoa(){	
		pessoa.setNome(null);
		setCompPessoa((Pessoa)AGFBandbox.onClickList(bandboxPessoa, pessoaBandbox, listboxPessoa));
	}
	/**Método de listar os objetos da Bandbox Pessoa. 
	 * 
	 * @author Arthur Freire
	 */
	public void listaPessoas(){
		inicRecepPessoa();
		objs = pessoaService.filter(pessoa, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
		totalSize = pessoaService.getNumberRecordsFilter(pessoa).intValue();
		AGFBandbox.listaElementos(listboxPessoa, pessoaService, paginacaoPessoa, pageSizeBandbox, totalSize, objs);
	}
	/**Método dos botões de paginação do Bandbox pessoa. 
	 * 
	 * @author Arthur Freire
	 */
	public void onPaging$paginacaoPessoa(){
		inicRecepPessoa();
		objs = pessoaService.filter(pessoa, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoPessoa,pageSizeBandbox,paginaAnterior));
		AGFBandbox.onPaging(null, listboxPessoa, pessoaService, paginacaoPessoa, pageSizeBandbox, paginaAnterior, objs);
	}
	/**Método inicializar o objeto e variiaveis do objeto do cambobox pessoa . 
	 * 
	 * @author Arthur Freire
	 */
	public void inicRecepPessoa(){
		pessoa.setId(0l);
		totalSize = 0;
		objs = new ArrayList<ObjetoPadrao>();
	}
	/**Método para guarda o objeto escolhido para o Bandbox e mostrar na view os dados que foi selecionado. 
	 * 
	 * @author Arthur Freire
	 * @param p Pessoa - Objeto do Bandbox Pessoa.
	 */
	public void setCompPessoa(Pessoa p){
		if(p!=null){	
			pessoaBandbox = p;
			bandboxPessoa.setValue(pessoaBandbox.getNome()); 
			longboxPessoa.setValue(pessoaBandbox.getId());
		}else{
			pessoaBandbox = null;
			bandboxPessoa.setValue(" "); 
			longboxPessoa.setValue(null);
			bandboxPessoa.setValue(null); 
		}
	}
	/**Método aonde é informado os códigos que insere os valores das variaveis no Bandbox Pessoa.
	 * 
	 * @author Arthur Freire      
	 */
	public void renderizarBandboxPessoa(){
		listboxPessoa.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem arg0, Object arg1) throws Exception {
				Pessoa m = (Pessoa) arg1;
				arg0.appendChild(new Listcell(m.getId().toString()));
				try{
					Image imagem = new Image();
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

	/* 
	 * Fim BandBox Pessoa - Arthur Freire 
	 */ 

	/**Método aonde é informado os códigos que seram introduzido nas variaveis do objeto os valores da view. 
	 * 
	 * @author Arthur Freire
	 */
	public void getRelacoesNpara1() {
		if(pessoaBandbox!=null)
			processo.setPessoa(pessoaBandbox);
		else
			processo.setPessoa(null);

		processo.setDtabertura(dateboxDtabertura.getValue());
	}
	/**Método aonde é informado os códigos que seram introduzido nos campos da view os valores do objeto. 
	 * 
	 * @author Arthur Freire
	 */
	public void setRelacoesNpara1() {
		setCompPessoa(processo.getPessoa());
		dateboxDtabertura.setValue(processo.getDtabertura());
	}
	/**Método aonde é informado os códigos que irá limpar os campos da view.  
	 * 
	 * @author Arthur Freire
	 */
	public void limparRelacoesNpara1() {
		setCompPessoa(null);
	}
	/**Método aonde é informado os códigos que irá validar os campos da variavel do objeto.
	 * 
	 * @author Arthur Freire
	 */
	public boolean isValidForm() {
		boolean ret = true;
		valid = 0;
		Processo processotemp = new Processo();
		processotemp.setId(0l);
		processotemp.setPessoa(new Pessoa());
		processotemp.getPessoa().setId(0l);
		processotemp.setDtabertura(null);
		processotemp.setNumprocesso(processo.getNumprocesso());
		if(processoService.getNumberRecordsFilter(processotemp)>0) {
			valid = 3;
			ret = false;
		}else
			if(processo.getPessoa()==null){
				valid = 1;
				ret = false;
			}else
				if((processo.getDtabertura() == null)||(processo.getDtabertura().equals(""))){
					valid = 2;
					ret =  false;
				}else
					if(processo.getNumprocesso()==null){
						valid = 5;
						ret = false;
					}

		return ret;
	}
	/**Método do botão salvar, salva ou alterar aonde é informado os códigos que irá imprimir a mensagem de erro na tela para os usuários.
	 * 
	 * @author Arthur Freire
	 */
	public void onClickbtSalvar() throws InterruptedException {
		Processo comp = (Processo) btSalvar(processo, processoService);
		if(comp!=null){
			processo = comp;
			triadorFinalizador(false);
		}
		else {
			switch (valid) {
			case 1:
				AGFComponente.showMessage("info","Informe o campo: PESSOA.");
				break;
			case 2:
				AGFComponente.showMessage("info","Informe o campo: DATA ABERTURA.");
				break;
			case 3:
				AGFComponente.showMessage("alerta","Já existe outro processo com o mesmo número de processo, por favor digitar outro valor no campo: N. PROCESSO.");
				break;
			case 5:
				AGFComponente.showMessage("info","Informe o campo: N. PROCESSO .");
				break;
			}
		}
	}
	/**Método para fazer Busca com filtros quando apertado enter nos campos de busca.
	 * 
	 * @author Arthur Freire
	 * @param event - campo para informar eventos.
	 */
	public void onOK$auxhead(Event event) {
		compAux = new Processo();
		String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
		if(idAux.equals("")){
			compAux.setId(0l);
		}else {
			compAux.setId(Long.valueOf(idAux));
		}
		compAux.setPessoa(new Pessoa()); 
		compAux.getPessoa().setNome(((Textbox) auxhead.getFellow("filtroPessoa")).getValue());
		compAux.getPessoa().setId(0l);
		compAux.setDtabertura(((Datebox) auxhead.getFellow("filtroDtabertura")).getValue() );
		compAux.setNumprocesso(((Intbox) auxhead.getFellow("filtroNumprocesso")).getValue());
		totalSize = 0;
		if(perfil!=null && perfil.getId()==8) {  
			parecerPendente(compAux, false);
			objnull = 1;
		}else {
			objs = new ArrayList<ObjetoPadrao>();
			objs = processoService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(new Paging(),pageSize,0));
			totalSize = processoService.getNumberRecordsFilter(compAux).intValue();
		}
		paginacao.setActivePage(0);
		AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, processoService,totalSize,objs);
		AGFPaginacao.paginacao(listbox, paginacao, pageSize, 0, processoService, objs,null);
	}
	/**Método do botão novo, abre o formulário para cadastro de um novo objeto.
	 * 
	 * @author Arthur Freire
	 */
	public void onClickbtNovo() {
		processo = new Processo();
		btNovo();
		controllerParecer.onClickbtLista();
		triadorFinalizador(true);
	}
	/**Método do botão cancelar, cancela alguma alteração nova.
	 * 
	 * @author Arthur Freire
	 * @deprecated
	 */
	public void onClickbtCancelar() {
		processo = (Processo) btCancelar(processo, processoService);
	}
	/**Método do botão remover, remove um oou varios objetos.
	 * 
	 * @author Arthur Freire
	 */
	public void onClickbtRemover() throws InterruptedException {
		btRemover(processo, processoService);
	}
	/**Método do botão listar, lista os objetos existentes.
	 * 
	 * @author Arthur Freire
	 */
	public void onClickbtLista() {
		btLista();
		perfil = triadorFinalizadorGeral();
		if(perfil!=null && perfil.getId()==8) {  
			parecerPendente(null, false);
			AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, processoService, totalSize, objs);
			objnull = 0;
		}else {
			AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, processoService, null, null);
		}
		objs = null;
	}
	/**Método dos botões de paginação, jagina os objetos da classe.
	 * 
	 * @author Arthur Freire
	 */
	public void onPaging$paginacao() {
		if(perfil!=null && perfil.getId()==8) {
			if(objnull==0){
				parecerPendente(null, true);
				objnull = 0;
			}else {
				parecerPendente(compAux, true);
				objnull = 1;
			}    	  
		}else {
			if(objs!=null){
				objs = processoService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
			}
		}    
		AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, processoService, objs,null);
	}
	/**Método usado quando for clicado no objeto da lista ele carregará o objeto na tela.
	 * 
	 * @author Arthur Freire
	 * @param obj Object - Objeto da classe
	 */
	public void carregarObjeto(Object obj) {
		processo = (Processo) obj;
		carregarObj(processo);
		controllerParecer.onClickbtLista();
		triadorFinalizador(false);
	}
	/**Método usado para remover dependencias da classe pai.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Classe pai.
	 * @deprecated
	 */
	@Override
	public Boolean removeDependencias(ObjetoPadrao obj) {
		return null;
	}
	/**Método usado para remover dependencias da classe pai de um objeto sem id padrao.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Classe pai.
	 * 
	 * @return Boolean - True / False
	 * @deprecated
	 */
	@Override
	public Boolean removeDependencias(ObjetoPadraoSemId obj) {
		return null;
	}
	/**Método cria um informativo no campo de DATA ABERTURA.
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$aInfodtabertura(){
		labelNomePoup.setValue("DATA ABERTURA");
		toolbarButton.setLabel("Data de abertura do processo.");
	}

	/*
	 * Classes Filhas - início
	 * 
	 */ 
	private IncludeParecer controllerParecer = null;
	private Include includeParecer;
	/**Método para iniciar o controler da classes filhas pela classe pai.
	 * 
	 * @author Arthur Freire
	 */
	@Override
	public void setControllerFilha() {
		controllerParecer = (IncludeParecer)includeParecer.getAttribute("controller");
	}
	/**Método cria um objeto com valores que será levada para a classes filhas.
	 * 
	 * @author Arthur Freire
	 */
	@Override
	public void setObjetosFilha() {
		objetos = new ArrayList<Object>();
		objetos.add(processo);
		objetos.add(binder);
		objetos.add(perfil);
		controllerParecer.onInicio(objetos);
	}

	/*
	 * Classes Filhas - FIM
	 * 
	 */ 

	/**Método quando click for clicado nas abas seja listada a classe filha da aba especifica.
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$tabParecer(){
		controllerParecer.onClickbtLista();
	}
	/**Método indentifica qual é o perfil logado, se o perfil for Triador ou
	 * Finalizador terá suas especificações de cada perfil criadas.
	 * 
	 * @author Arthur Freire
	 */
	private Perfil triadorFinalizadorGeral() {
		UsuarioPerfil usuperf = new UsuarioPerfil();
		usuperf.setId(0l);
		usuperf.setPerfil(new Perfil());
		usuperf.getPerfil().setNome(null);
		usuperf.getPerfil().setId(0l);
		usuperf.setUsuario(usuarioAux);
		usuperf.setAtivo(null);
		usuperf.setAdministrador(null);
		for(ObjetoPadrao temp:usuarioPerfilService.filter(usuperf)) {
			System.out.println("perfil: "+((UsuarioPerfil)temp).getPerfil().getNome()+", Nome Usuário: "+((UsuarioPerfil)temp).getUsuario().getNome());
			if(((UsuarioPerfil)temp).getPerfil().getId()==7) {
				btRemover.setVisible(false);
				tabParecer.setLabel("USUÁRIOS QUE REALIZARAM O PARECER");
				return ((UsuarioPerfil)temp).getPerfil();
			}else
				if(((UsuarioPerfil)temp).getPerfil().getId()==8) {
					btRemover.setVisible(false);
					btSalvar.setVisible(false);
					btNovo.setVisible(false);
					tabParecer.setLabel("INFORME O SEU PARECER");
					intboxNumprocesso.setDisabled(true);
					longboxPessoa.setDisabled(true);
					bandboxPessoa.setDisabled(true);
					dateboxDtabertura.setDisabled(true);
					return ((UsuarioPerfil)temp).getPerfil();
				}
		}
		return null;
	}
	/**Método para fisualizar componentes, se o perfil for Triador ou Finalizador
	 *  será visualizados as componentes especificos de cada perfil.
	 * 
	 * @author Arthur Freire
	 */
	private void triadorFinalizador(Boolean b) {
		if(perfil!=null) {
			if(perfil.getId() == 7) {
				btSalvar.setVisible(b);
			}else if(perfil.getId()==8) { 
				btRemover.setVisible(false);
				btSalvar.setVisible(false);
				btNovo.setVisible(false); 
			}
		}
	}
	/**Método lista somente os processo que estam com o parecer Pendente.
	 * 
	 * @author Arthur Freire
	 */
	private void parecerPendente(Processo pro, Boolean pag) {
		List<ObjetoPadraoSemId> listaparecer = new ArrayList<ObjetoPadraoSemId>();
		Parecer parecer = new Parecer();
		parecer.setId(0l);
		parecer.setUsuario(usuarioAux);
		if(pro==null) {
			parecer.setProcesso(new Processo());
			//parecer.getProcesso().setObs(null);
			parecer.getProcesso().setId(0l);
			parecer.getProcesso().setPessoa(new Pessoa());

		}else {
			parecer.setProcesso(pro);
		}
		parecer.setDescricao(" ");
		totalSize = parecerService.getNumberRecordsFilter(parecer).intValue();
		objs = new ArrayList<ObjetoPadrao>();
		if(totalSize>0) {
			if(pag) {
				listaparecer = parecerService.filter(parecer,pageSize,AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
			}else {
				listaparecer = parecerService.filter(parecer,pageSize,0);
			}
			for(ObjetoPadraoSemId temp: listaparecer) {
				objs.add(((Parecer)temp).getProcesso());
			}
		}
	}

}
