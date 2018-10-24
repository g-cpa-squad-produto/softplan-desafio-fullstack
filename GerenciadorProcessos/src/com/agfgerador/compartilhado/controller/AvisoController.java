package com.agfgerador.compartilhado.controller;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.agfgerador.autenticacao.domain.Aviso;
import com.agfgerador.autenticacao.domain.Perfil;
import com.agfgerador.autenticacao.domain.TipoSistema;
import com.agfgerador.autenticacao.domain.TipoUsuario;
import com.agfgerador.autenticacao.service.AvisoService;
import com.agfgerador.autenticacao.service.PerfilService;
import com.agfgerador.compartilhado.domain.ObjetoPadrao;
import com.agfgerador.compartilhado.domain.ObjetoPadraoSemId;
import com.agfgerador.compartilhado.util.AGFComponente;
import com.agfgerador.compartilhado.util.AGFFile;
import com.agfgerador.compartilhado.util.AGFPaginacao;

public class AvisoController extends ControllerAutenticacao implements IPaginacao {

	private static final long serialVersionUID = 1L;

	public Aviso aviso;
	private Combobox comboSistema,comboPerfil,comboTipo,filtroSistema;
	private Checkbox check;
	private int valid;
	private Button btArquivo;
	private Textbox nomeArquivo;
	
	private AvisoService avisoService;
	private PerfilService perfilService;
	
	@Override
	public void doAfterCompose(Component win) throws Exception {
		win.setAttribute("controller", this);
		win.setAttribute("nomeTela", "Aviso");
		doAfterCompose(win, true, "all");
		inicializaFiltroSistema();
	
	}

	@Override
	public void renderizarListaPrincipal() {
		listbox.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem arg0, Object arg1) throws Exception {
				Aviso m = (Aviso) arg1;	
				Listcell lc = new Listcell();
				lc.appendChild(new Checkbox());
				arg0.appendChild(lc);
				arg0.appendChild(new Listcell(m.getId().toString()));
				arg0.appendChild(new Listcell(m.getSistema().toString()));
				arg0.appendChild(new Listcell(m.getTitulo()));
				if(m.getDescricao().length()<=100)
					arg0.appendChild(new Listcell(m.getDescricao()));
				else
					arg0.appendChild(new Listcell(m.getDescricao().substring(0, 100)+"..."));
				
			}
		});
		
	}

	@Override
	public void inicializarRelacoesNpara1() {
		ListModel ls = null;
		
		ls = new ListModelList(TipoSistema.values());
		comboSistema.setModel(ls);
	
		ls = new ListModelList();
		comboPerfil.setModel(ls);
		
		ls = new ListModelList(TipoUsuario.values());
		comboTipo.setModel(ls);
		
	}

	public void inicializaFiltroSistema(){
		ListModel ls = null;
		
		ls = new ListModelList(TipoSistema.values());
		filtroSistema.setModel(ls);
	
	}
	
	public void onSelect$comboSistema()
	{	buscaPerfilBySistema((TipoSistema)comboSistema.getSelectedItem().getValue());
	}
	
	public void onCheck$check(){
		if(!check.isChecked()){
			aviso.setUrldoc(null);
			aviso.setNomedoc(null);
		}
	}
	
	public void buscaPerfilBySistema(TipoSistema sis)
	{   comboPerfil.setValue(null);
		ListModel ls = null;
		ls = new ListModelList(perfilService.findBySistema(sis));
		comboPerfil.setModel(ls);
	}

	@Override
	public void getRelacoesNpara1() {
		if(!comboSistema.getValue().equals(""))
			aviso.setSistema((TipoSistema)comboSistema.getSelectedItem().getValue());
		else
			aviso.setSistema(null);
		
		if(!comboPerfil.getValue().equals(""))
			aviso.setPerfil((Perfil)comboPerfil.getSelectedItem().getValue());
		else
			aviso.setPerfil(null);
		
		if(!comboTipo.getValue().equals(""))
			aviso.setTipoUsuario((TipoUsuario)comboTipo.getSelectedItem().getValue());
		else
			aviso.setTipoUsuario(null);
		
	}
	public void deabilitaCombos(boolean condicao){
		comboPerfil.setDisabled(condicao);
		comboTipo.setDisabled(condicao);
	}
	@Override
	public void setRelacoesNpara1() {
		deabilitaCombos(true);
		buscaPerfilBySistema(aviso.getSistema());
		comboPerfil.setConstraint("");
		comboTipo.setConstraint("");
		comboSistema.setConstraint("");
		if(aviso.getSistema()!=null)
			comboSistema.setValue(aviso.getSistema().toString());
		else
			comboSistema.setValue(null);

		if(aviso.getPerfil()!=null){
			comboPerfil.setValue(aviso.getPerfil().getNome());
		}
		else{	
			comboPerfil.setValue(null);
		}
		
		if(aviso.getTipoUsuario()!=null){	
			comboTipo.setValue(aviso.getTipoUsuario().toString());
		}
		else{	
			comboTipo.setValue(null);
		}
		if(aviso.getNomedoc()!=null)
			visibilidadeElementosDoc(true);
		else
			visibilidadeElementosDoc(false);
		comboPerfil.setConstraint("strict");
		comboTipo.setConstraint("strict");
		comboSistema.setConstraint("strict");
		
	}

	private void visibilidadeElementosDoc(boolean codicao) {
		nomeArquivo.setVisible(codicao);
		check.setChecked(codicao);
		btArquivo.setVisible(codicao);
	}
	@Override
	public void limparRelacoesNpara1() {
		comboSistema.setValue(null);
		comboPerfil.setValue(null);
		comboTipo.setValue(null);
	}

	@Override
	public boolean isValidForm() {
		boolean ret = true;
		valid = 0;
		if((aviso.getSistema()==null)||(aviso.getSistema().equals(""))){
			valid = 1;
			ret = false;
		}
		else if((aviso.getTitulo()==null)||(aviso.getTitulo().equals(""))){
			valid = 2;
			ret = false;
		}
		else if((aviso.getDescricao()==null)||(aviso.getDescricao().equals(""))){
			valid = 3;
			ret = false;
		}
		
		return ret;
	}
	@Override
	public void onClickbtSalvar() throws InterruptedException {
		getRelacoesNpara1();
		if(isValidForm()){
			if(aviso.getId()==null){
				verificaAvisos();
			}
			else
				saveOrUpdateObj();
		}
		else {	
			switch (valid) {
				case 1:
					AGFComponente.showMessage("info", "Informe o campo: Sistema");
					break;
				case 2:
					AGFComponente.showMessage("info", "Informe o campo: Título");
					break;
				case 3:
					AGFComponente.showMessage("info","Informe o campo: Descrição");
					break;	
				default:
					AGFComponente.showMessage("info","Um ou mais campos possuem valores ilegais, cheque os erros de cada campo.");
					break;
			}
		}
	}

	public void saveOrUpdateObj(){
		boolean isNew = true,erro=false;
		if(aviso.getId()!=null)
			isNew = false;
		if(check.isChecked()){
			if(saveFile()){
				aviso = (Aviso) saveOrUpdateObjeto(aviso, avisoService);
			}
			else{
				erro = true;
				AGFComponente.showMessage("erro","Erro ao salvar o arquivo.");
			}
				
		}
		else
			aviso = (Aviso) saveOrUpdateObjeto(aviso, avisoService);
			
		
		if((isNew)&&(!erro))
			setAvisosUsuario();
	}

	
	public void verificaAvisos() throws InterruptedException{
		Long count = usuarioService.avisos(true);
		if(count>0){
			Messagebox.show("Existem usuários que já possuem avisos ativos. Deseja realmente continuar?", "Atenção", Messagebox.YES|Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {
				public void onEvent(Event evt) {
					switch (((Integer)evt.getData()).intValue()) {
					case Messagebox.YES:
						saveOrUpdateObj();
						break; //the Yes button is pressed
					case Messagebox.NO:
						
						break; 
					}
				}
			}); 
		}
		else
			saveOrUpdateObj();
	}
	@Override
	public void onOK$auxhead(Event event) {
		Aviso avisoAux = new Aviso();
		String idAux = 	((Textbox) auxhead.getFellow("filtroCodigo")).getValue();
		if(idAux.equals("")){
			avisoAux.setId(0L);
		}else{
			avisoAux.setId(Long.valueOf(idAux));
		}
		Combobox combo = ((Combobox) auxhead.getFellow("filtroSistema"));
		
		if(!combo.getValue().equals(""))
			avisoAux.setSistema((TipoSistema)combo.getSelectedItem().getValue());
		
		avisoAux.setTitulo(((Textbox) auxhead.getFellow("filtroTitulo")).getValue());
		avisoAux.setDescricao(((Textbox) auxhead.getFellow("filtroDescricao")).getValue());
		ListModel ls = new ListModelList(avisoService.filter(avisoAux)) ;
		listbox.setModel(ls);
		if(listbox.getPagingChild() != null)
			listbox.getPagingChild().setVisible(true);
		paginacao.setVisible(false);
		
	}

	@Override
	public void onClickbtNovo() {
		aviso = new Aviso();
		deabilitaCombos(false);
		btNovo();
	}

	@Override
	public void onClickbtCancelar() {
		btCancelar(aviso, avisoService);
	}

	@Override
	public void onClickbtRemover() throws InterruptedException {
		btRemover(aviso, avisoService);
	}

	@Override
	public Boolean removeDependencias(ObjetoPadrao obj) {
		return null;
	}

	@Override
	public void onClickbtLista() {
		btLista();
		AGFPaginacao.btListaPaginacao(listbox, paginacao, pageSize, avisoService, null, null);
		
	}

	@Override
	public void carregarObjeto(Object obj) {
		aviso = (Aviso)obj;
		carregarObj(aviso);
		
	}
	
	@Override
	public void onPaging$paginacao() {
		AGFPaginacao.paginacao(listbox, paginacao, pageSize, paginaAnterior, avisoService, null,null);
		
	}
	
	public Media media;
	
	public void onUpload$btArquivo()
	{	try{	
			media = (Media) window.getAttribute("media"); 
			aviso.setNomedoc(media.getName());
			binder.loadAll();
		}
		catch (Exception e){
			System.out.println(e);
		}
			
	}

	public boolean saveFile(){
		boolean ret = false;
		aviso.setUrldoc(AGFFile.getSrcSistema("/view/compartilhado/view/aviso", (String)session.getAttribute("projeto"), aviso.getNomedoc()));
		ret = AGFFile.salvaArquivo(aviso.getUrldoc(), media.getByteData().length, media.getStreamData());
		return ret;
	}

	public Boolean setAvisosUsuario(){
		boolean ret = true;
		String sql="UPDATE `aut_usuario` a SET a.aviso_id = "+aviso.getId();

		String where="";
		
		if(aviso.getPerfil()!=null){
			where=" WHERE a.id IN (SELECT d.`usuario_id` FROM aut_usuarioperfil d WHERE d.`perfil_id` = "+aviso.getPerfil().getId()+")";
		}
		else{
			where= " WHERE a.id IN (SELECT c.`usuario_id` FROM aut_perfil b "
				 + " JOIN aut_usuarioperfil c ON (c.`perfil_id`=b.id)"
				 + " WHERE b.`sistema` ='"+aviso.getSistema()+"')";
		}
		
		if(aviso.getTipoUsuario()!=null){
			where=where+" AND a.`tipo` = '"+aviso.getTipoUsuario()+"'";
		}
		
		ret = sqlJdbcService.executeSql(sql);
		return ret;
	}

	@Override
	public Boolean removeDependencias(ObjetoPadraoSemId obj) {
		return null;
	}

}
