package com.agfgerador.gerenciadorprocessos.controller;

import java.util.List;
import java.util.ArrayList;
import org.zkoss.zul.Paging;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Checkbox;
import com.agfgerador.compartilhado.util.AGFPaginacao;
import com.agfgerador.compartilhado.controller.IPaginacao;
import com.agfgerador.compartilhado.controller.IncludeBaseSemId;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.util.AGFComponente;
import com.agfgerador.gerenciadorprocessos.domain.Parecer;
import com.agfgerador.gerenciadorprocessos.service.ParecerService;
import com.agfgerador.autenticacao.domain.Perfil;
import com.agfgerador.autenticacao.domain.Usuario;
import com.agfgerador.autenticacao.domain.UsuarioPerfil;
import com.agfgerador.autenticacao.service.UsuarioPerfilService;
import com.agfgerador.autenticacao.service.UsuarioService;
import org.zkoss.zul.Listbox;
import com.agfgerador.compartilhado.util.AGFBandbox;
import org.zkoss.zul.Bandbox;
import com.agfgerador.gerenciadorprocessos.domain.Processo;
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbarbutton;

/**IncludeParecer - Classe para controle das funções da View includeParecer.zul.
 * 
 * @author Arthur Freire
 */
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
	private Perfil perfil = new Perfil();
	private UsuarioPerfilService usuarioPerfilService;
	private Div divparecer, divBandboxUsuario;
	private Textbox filtroDescricao;
	private Listheader listheaderDescricao;


	/////////////Usuario
	Usuario usuario = new Usuario();
	private Bandbox bandboxUsuario;
	private Longbox longboxUsuario;
	private Listbox listboxUsuario;
	private Paging paginacaoUsuario;
	private Usuario usuarioBandbox;
	private UsuarioPerfil usuPerAux;
	private UsuarioService usuarioService;

	private Label labelNomePoup;
	private Toolbarbutton toolbarButton;

	/**Inicializa todas as funções da classe controller 
	 * 
	 * @author Arthur Freire
	 * @param win Componente - Dados que será usado na view para iniciar a classe controller. 
	 */
	public void doAfterCompose(Component win) throws Exception {
		win.setAttribute("controller",this);
		super.doAfterCompose(win,"all");
		renderizarBandboxUsuario();
		usuarioAux = (Usuario)session.getAttribute("usuarioLogado");
	}
	/**Método aonde e tirado os dados que foi enviado pela classe pai.
	 * 
	 * @author Arthur Freire  
	 * @param  objetos List<Object>   
	 */
	@Override
	public void onInicio(List<Object> objetos) {
		onInicio();
		processo = (Processo)objetos.get(0);
		perfil = (Perfil)objetos.get(2);
	}
	/**Método aonde é informado os códigos que insere os valores na listagem da view.
	 * 
	 * @author Arthur Freire      
	 */
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
	 * BandBox Usuario - Arthur Freire 
	 */ 
	/**Método aonde redireciona para a tela de Usuario 
	 * 
	 * @author Arthur Freire
	 * @deprecated
	 */
	public void onClick$labelUsuario(){
		labelLink("usuario", null);
	}
	/**Método que busca pelo id do objeto do Bandbox Usuario. 
	 * 
	 * @author Arthur Freire
	 */
	public void onOK$longboxUsuario(){
		usuario = new Usuario();
		inicRecepUsuario();
		if(longboxUsuario.getValue()!=null) {
			inicRecepUsuario();
			usuPerAux.getUsuario().setId(longboxUsuario.getValue());
			List<ObjetoPadrao>listausoperfil = usuarioPerfilService.filter(usuPerAux, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0));
			if(listausoperfil.size()>0) {
				usuario = ((UsuarioPerfil)listausoperfil.get(0)).getUsuario();
				setCompUsuario(usuario);
			}else {
				setCompUsuario(usuario);
			}
		}else {
			setCompUsuario(usuario);
		}
		//setCompUsuario((Usuario)AGFBandbox.onOKLongbox(longboxUsuario, usuarioBandbox, usuarioService));
	}
	/**Método ao aperta enter no Bandbox Usuario. 
	 * 
	 * @author Arthur Freire
	 */
	public void onOK$bandboxUsuario(){
		onChange$bandboxUsuario();
	}
	/**Método quando ouver uma mudança no Bandbox Usuario. 
	 * 
	 * @author Arthur Freire
	 */
	public void onChange$bandboxUsuario(){
		inicRecepUsuario();
		usuPerAux.getUsuario().setNome(bandboxUsuario.getValue());

		for(ObjetoPadrao temp:usuarioPerfilService.filter(usuPerAux, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0))) {
			objs.add(((UsuarioPerfil)temp).getUsuario());
		}
		totalSize = usuarioPerfilService.getNumberRecordsFilter(usuPerAux).intValue();

		AGFBandbox.onChange(bandboxUsuario, listboxUsuario, usuarioService, paginacaoUsuario, pageSizeBandbox, totalSize, objs);
	}
	/**Método quando abrir Bandbox Usuario. 
	 * 
	 * @author Arthur Freire
	 */
	public void onOpen$bandboxUsuario(){
		usuario.setNome(null);
		listaUsuarios();
	}
	/**Método ao clicar Bandbox Usuario. 
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$listboxUsuario(){	
		usuario.setNome(null);
		setCompUsuario((Usuario)AGFBandbox.onClickList(bandboxUsuario, usuarioBandbox, listboxUsuario));
	}
	/**Método de listar os objetos da Bandbox Usuario. 
	 * 
	 * @author Arthur Freire
	 */
	public void listaUsuarios(){
		inicRecepUsuario();

		for(ObjetoPadrao temp:usuarioPerfilService.filter(usuPerAux, pageSizeBandbox, AGFPaginacao.getPagePaginacao(new Paging(),pageSizeBandbox,0))) {
			objs.add(((UsuarioPerfil)temp).getUsuario());
		}
		totalSize = usuarioPerfilService.getNumberRecordsFilter(usuPerAux).intValue();

		AGFBandbox.listaElementos(listboxUsuario, usuarioService, paginacaoUsuario, pageSizeBandbox, totalSize, objs);
	}
	/**Método dos botões de paginação do Bandbox Usuario. 
	 * 
	 * @author Arthur Freire
	 */
	public void onPaging$paginacaoUsuario(){
		inicRecepUsuario();

		for(ObjetoPadrao temp:usuarioPerfilService.filter(usuPerAux, pageSizeBandbox, AGFPaginacao.getPagePaginacao(paginacaoUsuario,pageSizeBandbox,paginaAnterior))) {
			objs.add(((UsuarioPerfil)temp).getUsuario());
		}

		AGFBandbox.onPaging(null, listboxUsuario, usuarioService, paginacaoUsuario, pageSizeBandbox, paginaAnterior, objs);
	}
	/**Método inicializar o objeto e variiaveis do objeto do cambobox pessoa . 
	 * 
	 * @author Arthur Freire
	 */
	public void inicRecepUsuario(){
		//usuario.setId(0l);
		usuPerAux = new UsuarioPerfil();
		usuPerAux.setId(0L);
		usuPerAux.setPerfil(new Perfil()); 
		usuPerAux.getPerfil().setId(8L);
		usuPerAux.setUsuario(new Usuario()); 
		usuPerAux.getUsuario().setNome(null);
		usuPerAux.getUsuario().setId(0L);
		usuPerAux.setAtivo(null);
		usuPerAux.setAdministrador(null);
		totalSize = 0;
		objs = new ArrayList<ObjetoPadrao>();
	}
	/**Método para guarda o objeto escolhido para o Bandbox e mostrar na view os dados que foi selecionado. 
	 * 
	 * @author Arthur Freire
	 * @param p Pessoa - Objeto do Bandbox Pessoa.
	 */
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
	/**Método aonde é informado os códigos que insere os valores das variaveis no Bandbox Pessoa.
	 * 
	 * @author Arthur Freire      
	 */
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
	 * Fim BandBox Usuario - Arthur Freire 
	 */ 
	/**Método aonde é informado os códigos que seram introduzido nas variaveis do objeto os valores da view. 
	 * 
	 * @author Arthur Freire
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
	}
	/**Método aonde é informado os códigos que seram introduzido nos campos da view os valores do objeto. 
	 * 
	 * @author Arthur Freire
	 */
	public void setRelacoesNpara1() {
		setCompUsuario(parecer.getUsuario());
	}
	/**Método aonde é informado os códigos que irá limpar os campos da view.  
	 * 
	 * @author Arthur Freire
	 */
	public void limparRelacoesNpara1() {
		setCompUsuario(null);
	}
	/**Método aonde é informado os códigos que irá validar os campos da variavel do objeto.
	 * 
	 * @author Arthur Freire
	 */
	public boolean isValidForm() {
		boolean ret = true;
		valid = 0;
		if(parecer.getUsuario()==null){
			valid = 1;
			ret = false;
		}else
			if(parecer.getProcesso()==null){
				valid = 2;
				ret = false;
			}else {
				if(perfil!=null && perfil.getId()==7) {
					Parecer parecertemp = new Parecer();
					parecertemp.setId(0l);
					parecertemp.setUsuario(parecer.getUsuario());
					parecertemp.setProcesso(parecer.getProcesso());
					parecertemp.setDescricao(null);
					if(parecerService.getNumberRecordsFilter(parecertemp)>0) {
						valid = 3;
						ret = false;
					}
				}else if(perfil!=null && perfil.getId()==8) { 
					if((parecer.getDescricao()==null)||(parecer.getDescricao().equals(""))){
						valid = 4;
						ret = false;
					}		   
				}      
			}
		return ret;
	}
	/**Método do botão salvar, salva ou alterar aonde é informado os códigos que irá imprimir a mensagem de erro na tela para os usuários.
	 * 
	 * @author Arthur Freire
	 */
	public void onClickbtSalvar() throws InterruptedException {
		Parecer comp = (Parecer) btSalvar(parecer, parecerService);
		if(comp!=null){
			parecer = comp;
			getTriadorFinalizador(false,false);
		}
		else {
			switch (valid) {
			case 1:
				AGFComponente.showMessage("info","Informe o campo: USUARIO.");
				break;
			case 2:
				AGFComponente.showMessage("info","Informe o campo: PROCESSO.");
				break;
			case 3:
				AGFComponente.showMessage("alerta","Usuário já está vinculado ao processo.");
				break;
			case 4:
				AGFComponente.showMessage("info","Informe o campo: PARECER.");
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
	/**Método do botão novo, abre o formulário para cadastro de um novo objeto.
	 * 
	 * @author Arthur Freire
	 */
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
			getTriadorFinalizador(true,false);
		}
	}
	/**Método do botão cancelar, cancela alguma alteração nova.
	 * 
	 * @author Arthur Freire
	 * @deprecated
	 */
	public void onClickbtCancelar() {
		parecer = (Parecer) btCancelar(parecer, parecerService);
	}
	/**Método do botão remover, remove um oou varios objetos.
	 * 
	 * @author Arthur Freire
	 */
	public void onClickbtRemover() throws InterruptedException {
		btRemover(parecer, parecerService);
	}
	/**Método do botão listar, lista os objetos existentes.
	 * 
	 * @author Arthur Freire
	 */
	public void onClickbtLista() {
		getTriadorFinalizadorGeral();
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

				if(perfil!=null && perfil.getId()==8) {
					compAux.setUsuario(usuarioAux);
				}else {
					compAux.setUsuario(new Usuario());
					compAux.getUsuario().setNome(((Textbox) auxhead.getFellow("filtroUsuario")).getValue());
					compAux.getUsuario().setId(0l);
				}
				compAux.setProcesso(new Processo()); 
				compAux.setProcesso(processo);
				System.out.println("include filter descrição: "+((Textbox) auxhead.getFellow("filtroDescricao")).getValue());
				compAux.setDescricao(((Textbox) auxhead.getFellow("filtroDescricao")).getValue());
				Integer totalSize = 0;
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
	/**Método dos botões de paginação, jagina os objetos da classe.
	 * 
	 * @author Arthur Freire
	 */
	public void onPaging$paginacao() {
		if(objsemid!=null){
			objsemid = parecerService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
		}
		AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, parecerService, objsemid,null);
	}
	/**Método usado quando for clicado no objeto da lista ele carregará o objeto na tela.
	 * 
	 * @author Arthur Freire
	 * @param obj Object - Objeto da classe
	 */
	public void carregarObjeto(Object obj) {
		parecer = (Parecer) obj;
		carregarObj(parecer);
		if((parecer.getDescricao()==null)||(parecer.getDescricao().equals(""))){
			getTriadorFinalizador(false,true);
		}else {
			getTriadorFinalizador(false,false);
		}
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
	/**Método cria um informativo no campo de PARECER.
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$aInfodescricao(){
		labelNomePoup.setValue("PARECER");
		toolbarButton.setLabel("Descrição do parecer");
	}

	//////////////////////////////////////////
	/**Método indentifica qual é o perfil logado, se o perfil for Triador ou
	 * Finalizador terá suas especificações de cada perfil criadas.
	 * 
	 * @author Arthur Freire
	 */
	private void getTriadorFinalizadorGeral() {
		if(perfil!=null) {
			if(perfil.getId()==7) {
				barraFerramentasInclude.getFellow("btRemover").setVisible(false);
				divBandboxUsuario.setVisible(true);
				divparecer.setVisible(false);
				filtroDescricao.setVisible(false);
				listheaderDescricao.setVisible(false);
			}else
				if(perfil.getId()==8) {       
					barraFerramentasInclude.getFellow("btRemover").setVisible(false);
					barraFerramentasInclude.getFellow("btNovo").setVisible(false);
					divBandboxUsuario.setVisible(false);
				}
		}
	}
	/**Método para fisualizar componentes, se o perfil for Triador ou Finalizador
	 *  será visualizados as componentes especificos de cada perfil.
	 * 
	 * @author Arthur Freire
	 */
	private void getTriadorFinalizador(Boolean triador, Boolean finalizador) {
		if(perfil!=null && perfil.getId()==7) {
			barraFerramentasInclude.getFellow("btSalvar").setVisible(triador);        		   
		}else if(perfil!=null && perfil.getId()==8) { 
			barraFerramentasInclude.getFellow("btSalvar").setVisible(finalizador);
		}
	}

}
