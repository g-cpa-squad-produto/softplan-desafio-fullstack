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
import org.zkoss.zul.Include;
import com.agfgerador.compartilhado.controller.ControllerAGF;
import org.zkoss.zul.Checkbox;
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
/**UsuarioController - Classe para controle das funções da View usuarioperfilmanage.zul.
 * 
 * @author Arthur Freire
 */
public class UsuarioController extends ControllerAGF implements IPaginacao,IFilhas{
	private static final long serialVersionUID = 1L;

	public Usuario usuario;
	private UsuarioService usuarioService;
	private int valid;
	private List<ObjetoPadrao> objs = null;
	private Usuario compAux = new Usuario();
	private String senhaAntiga;

	private Label labelNomePoup;
	private Toolbarbutton toolbarButton;
	private Checkbox checkboxHabilitado;
	/**Inicializa todas as funções da classe controller 
	 * 
	 * @author Arthur Freire
	 * @param win Componente - Dados que será usado na view para iniciar a classe controller. 
	 */
	public void doAfterCompose(Component win) throws Exception {
		win.setAttribute("controller",this);
		win.setAttribute("nomeTela", "Usuario");
		win.setAttribute("istemfilha", true);
		super.doAfterCompose(win,true,"all");
		btInformacoes.setVisible(false);
	}
	/**Método aonde é informado os códigos que insere os valores na listagem da view.
	 * 
	 * @author Arthur Freire      
	 */
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
	/**Método aonde é informado os códigos que seram inicializado 
	 * as relações que será usado na formação da view. 
	 * 
	 * @author Arthur Freire
	 */
	public void inicializarRelacoesNpara1() {
	}

	/**Método aonde é informado os códigos que seram introduzido nas variaveis do objeto os valores da view. 
	 * 
	 * @author Arthur Freire
	 */
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
	/**Método aonde é informado os códigos que seram introduzido nos campos da view os valores do objeto. 
	 * 
	 * @author Arthur Freire
	 */
	public void setRelacoesNpara1() {
		checkboxHabilitado.setChecked(usuario.isHabilitado());
		senhaAntiga = usuario.getSenha();
	}
	/**Método aonde é informado os códigos que irá limpar os campos da view.  
	 * 
	 * @author Arthur Freire
	 */
	public void limparRelacoesNpara1() {
		checkboxHabilitado.setChecked(false);
	}
	/**Método aonde é informado os códigos que irá validar os campos da variavel do objeto.
	 * 
	 * @author Arthur Freire
	 */
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
	/**Método do botão salvar, salva ou alterar aonde é informado os códigos que irá imprimir a mensagem de erro na tela para os usuários.
	 * 
	 * @author Arthur Freire
	 */
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
	/**Método para fazer Busca com filtros quando apertado enter nos campos de busca.
	 * 
	 * @author Arthur Freire
	 * @param event - campo para informar eventos.
	 */
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
	/**Método do botão novo, abre o formulário para cadastro de um novo objeto.
	 * 
	 * @author Arthur Freire
	 */
	public void onClickbtNovo() {
		usuario = new Usuario();
		btNovo();
		controllerUsuarioperfil.onClickbtLista();
	}
	/**Método do botão cancelar, cancela alguma alteração nova.
	 * 
	 * @author Arthur Freire
	 * @deprecated
	 */
	public void onClickbtCancelar() {
		usuario = (Usuario) btCancelar(usuario, usuarioService);
	}
	/**Método do botão remover, remove um oou varios objetos.
	 * 
	 * @author Arthur Freire
	 */
	public void onClickbtRemover() throws InterruptedException {
		btRemover(usuario, usuarioService);
	}
	/**Método do botão listar, lista os objetos existentes.
	 * 
	 * @author Arthur Freire
	 */
	public void onClickbtLista() {
		btLista();
		AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, usuarioService, null, null);
		objs = null;
	}
	/**Método dos botões de paginação, jagina os objetos da classe.
	 * 
	 * @author Arthur Freire
	 */
	public void onPaging$paginacao() {
		if(objs!=null){
			objs = usuarioService.filter(compAux, pageSize, AGFPaginacao.getPagePaginacao(paginacao,pageSize,paginaAnterior));
		}
		AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, usuarioService, objs,null);
	}
	/**Método usado quando for clicado no objeto da lista ele carregará o objeto na tela.
	 * 
	 * @author Arthur Freire
	 * @param obj Object - Objeto da classe
	 */
	public void carregarObjeto(Object obj) {
		usuario = (Usuario) obj;
		carregarObj(usuario);
		controllerUsuarioperfil.onClickbtLista();
	}
	/**Método usado para remover dependencias da classe pai.
	 * 
	 * @author Arthur Freire
	 * @param obj ObjetoPadrao - Classe pai.
	 * 
	 * @return Boolean - True / False
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
	/**Método cria um informativo no campo de NOME.
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$aInfonome(){
		labelNomePoup.setValue("NOME");
		toolbarButton.setLabel("nome do usuário");
	}
	/**Método cria um informativo no campo de LOGIN.
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$aInfologin(){
		labelNomePoup.setValue("LOGIN");
		toolbarButton.setLabel("Login pra entra no sistema");
	}
	/**Método cria um informativo no campo de SENHA.
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$aInfosenha(){
		labelNomePoup.setValue("SENHA");
		toolbarButton.setLabel("senha que o usuário irá usar para o login no sistema");
	}
	/**Método cria um informativo no campo de HABILITADO.
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$aInfohabilitado(){
		labelNomePoup.setValue("HABILITADO");
		toolbarButton.setLabel("Se esse usuário está habilitado ou não para usar o sistema.");
	}
	/**Método cria um informativo no campo de EMAIL.
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$aInfoemail(){
		labelNomePoup.setValue("EMAIL");
		toolbarButton.setLabel("Email do usuário");
	}
	/**Método cria um informativo no campo de TELEFONE.
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$aInfotelefone(){
		labelNomePoup.setValue("TELEFONE");
		toolbarButton.setLabel("Telefone residencial");
	}
	/**Método cria um informativo no campo de CELULAR.
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$aInfotelefonealternativo(){
		labelNomePoup.setValue("CELULAR");
		toolbarButton.setLabel("Informar o celular");
	}

	/*
	 * Filhas
	 * @author Arthur Freire
	 */ 

	private IncludeUsuarioperfil controllerUsuarioperfil = null;
	private Include includeUsuarioperfil;
	/**Método para iniciar o controler da classes filhas pela classe pai.
	 * 
	 * @author Arthur Freire
	 */
	@Override
	public void setControllerFilha() {
		controllerUsuarioperfil = (IncludeUsuarioperfil)includeUsuarioperfil.getAttribute("controller");
	}
	/**Método cria um objeto com valores que será levada para a classes filhas.
	 * 
	 * @author Arthur Freire
	 */
	@Override
	public void setObjetosFilha() {
		objetos = new ArrayList<Object>();
		objetos.add(usuario);
		objetos.add(binder);
		controllerUsuarioperfil.onInicio(objetos);
	}
	/*
	 * Filhas - FIM
	 * @author Arthur Freire
	 */ 
	/**Método quando click for clicado nas abas seja listada a classe filha da aba especifica.
	 * 
	 * @author Arthur Freire
	 */
	public void onClick$tabUsuarioperfil(){
		controllerUsuarioperfil.onClickbtLista();
	}


}
