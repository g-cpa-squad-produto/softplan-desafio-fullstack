/**
 * Descrição: 
 * @author Charles Lennon
 */ 

window.onload = function() {
	$("body").addClass("load");
}

//Aplica a m�scara no campo
//Fun��o para ser utilizada nos eventos do input para formata��o din�mica
function maskCpfCnpj(campo,tammax,teclapres) {
	var tecla = teclapres.keyCode;
	
	if ((tecla < 48 || tecla > 57) && (tecla < 96 || tecla > 105) && tecla != 46) {
		return false;
	}

	var vr = campo.value;
	vr = vr.replace( /\//g, "" );
	vr = vr.replace( /-/g, "" );
	vr = vr.replace( /\./g, "" );
	vr = vr.replace(/[^\d.]/g, "");
	var tam = vr.length;

	if ( tam <= 2 ) {
		campo.value = vr;
	}else	if ( (tam > 2) && (tam <= 5) ) {
		campo.value = vr.substr( 0, tam - 2 ) + '.' + vr.substr( tam - 2, tam); 
	}
	else if ( (tam >= 6) && (tam <= 8) ) {
		campo.value = vr.substr( 0, tam - 5 ) + '.' + vr.substr( tam - 5, 3 ) + '.' + vr.substr( tam - 2, tam );
	}
	else if ( (tam >= 9) && (tam <= 11) ) {
		campo.value = vr.substr( 0, tam - 8 ) + '.' + vr.substr( tam - 8, 3 ) + '.' + vr.substr( tam - 5, 3 ) + '-' + vr.substr( tam - 2, tam );
	}
	else if ( (tam == 12) ) {
		campo.value = vr.substr( tam - 12, 3 ) + '.' + vr.substr( tam - 9, 3 ) + '/' + vr.substr( tam - 6, 4 ) + '-' + vr.substr( tam - 2, tam );
	}
	else if ( (tam > 12) && (tam <= 14) ) {
		campo.value = vr.substr( 0, tam - 12 ) + '.' + vr.substr( tam - 12, 3 ) + '.' + vr.substr( tam - 9, 3 ) + '/' + vr.substr( tam - 6, 4 ) + '-' + vr.substr( tam - 2, tam );
	}
}

function mask(input, mask) {  

	switch (mask) {
	  case "cnpj":
		$(input).mask("99.999.999/9999-99");
	    break;
	  case "cpf":
		$(input).mask("999.999.999.99");
	    break;
	  case "cep":
		$(input).mask("99999-999");
	    break; 
	  case "telefone":
		$(input).mask("(99) 9999-9999");
		break;
	  case "cel":
			$(input).mask("(99) 99999-9999");
		break;
	} 
}

function maskReport(idcampo,mask) { 
    $(jq("#"+idcampo)).unmask(mask);
    $(jq("#"+idcampo)).mask(mask);
}

//set the date we're counting down to
var target_date = new Date().getTime()+ 600000;
 
// variables for time units
var days, hours, minutes, seconds;
 

function iniciarContagemSessao(){
	// update the tag with id "countdown" every 1 second
	setInterval(function () {
		// get tag element
		var countdown = document.getElementById("countdown");

	 
	    // find the amount of "seconds" between now and target
	    var current_date = new Date().getTime();
	    var seconds_left = (target_date - current_date) / 1000;
	 
	    // do some time calculations
	    days = parseInt(seconds_left / 86400);
	    seconds_left = seconds_left % 86400;
	     
	    hours = parseInt(seconds_left / 3600);
	    seconds_left = seconds_left % 3600;
	     
	    minutes = parseInt(seconds_left / 60);
	    seconds = parseInt(seconds_left % 60);
	     
	    // format countdown string + set tag value
//	    countdown.innerHTML = days + "d, " + hours + "h, "
//	    + minutes + "m, " + seconds + "s";  
	    countdown.innerHTML = "Sua sessao expira em: "+minutes + "m, " + seconds + "s";  
	    if((minutes<=0) && (seconds<=0)){
	    	countdown.innerHTML = "Sua sessao expirou! Recarregue a pagina.";
	    	return false;
	    }
	 
	}, 1000);
	
}

/**
 * Informativo de browser desatualizado
 */
function ieWarning() {
	var dialog = document.createElement('div'), 
	browserVersion = head.browser.version,
	browserName = head.browser.name;
	
	if((browserName=="ie") && (browserVersion<9)){
		dialog.className = 'browser-warning';
		dialog.id = "browserWarning";
		dialog.innerHTML = '\
			<div class="box-warning">\
			    <p class="title">Navegador desatualizado.</p> \
			    <div class="content"> \
			        <p class="subtitle">A vers&atilde;o de seu <b>Internet Explorer</b> &eacute; muito antiga. (V.:'+browserVersion+'.0)</p> \
			        <p>Para utilizar os nossos sistemas AGF!, <b>&eacute; recomendado que atualize o seu navegador atual</b> ou utilizar um dos navegadores abaixo:</p> \
		            <div class="nav"><a href="http://www.google.com/intl/pt-BR/chrome/browser/" title="Google Chrome" target="_blank"> \
			               <img src="view/compartilhado/img/icones-geral/chrome_logo.png" alt="Google Chrome"> \
			            </a> \
			            <a href="http://www.mozilla.org/pt-BR/firefox/" title="Mozilla Firefox" target="_blank"> \
			               <img src="view/compartilhado/img/icones-geral/firefox_logo.png" alt="Mozilla Firefox"> \
			            </a>\
					</div>\
					<a href="#" class="continuar" onclick="teste()">Continuar acessando</a>\
			    </div> \
			</div> \
		';
		document.body.appendChild(dialog);
	}  
}
function teste(){
	jq("#browserWarning").addClass("hide");
}

/**
 * Checagem via browser do formul�rio de login
 * @returns {Boolean}
 */
function checkFormLogin() { 
	if((jq("$login")[0].value=="") || (jq("$senha")[0].value=="")) {
		setMensagem('danger', 'Informe os dados corretamente.');
		return false;
	}else{ 
		return true;
	}
}


function setMensagem(tipo, msg) {
	var div = document.createElement("div");
	jq("$msg")[0].innerHTML = "";

	div.className="alert alert-"+ tipo;
	div.innerHTML = "<span id='msgErro'>"+msg+"</span>";
	jq("$msg")[0].appendChild(div);
}

/**
 * Alternar visualiza��o na tela de login
 * @param div : ID do elemento que ficar� vis�vel
 */
function divAtivaLogin(div) { 
	jq("$divRecuperarSenha")[0].className = "fade out ocultar";
	jq("$divForm")[0].className = "fade out ocultar";
	jq("$msg")[0].innerHTML = "";
	
	jq("$"+div)[0].className = "box-login-form fade in";
}

/**
 * Alterar a visualiza��o da tela
 * @param div1 : ID do elemento que ficar� oculto
 * @param div2 : ID do elemento que ficar� vis�vel
 */
function changeView(div1, div2){  
	jq("#"+div1).removeClass("fade in").addClass("fade out ocultar");
	jq("#"+div2).removeClass("fade out ocultar").addClass("fade in");
}

/**
 * Alterar a visualiza��o do menu pai de acordo com a tela. 
 * @param tipo : valores permitidos: 'form' ou 'list'. Define qual a view ficar� vis�vel para o usu�rio 
 * @param list : lista com ID dos bot�es do menu.
 */
function menuView(tipo, list){
	var botoes = JSON.parse(list);
	for(var i=0; i<botoes.length; i++){
		if(tipo=="list"){
			if(botoes[i].contexto == "form")
				jq("#"+botoes[i].uuid).addClass("hide-left");
			else 
				jq("#"+botoes[i].uuid).removeClass("hide-left");	
		}else if(tipo=="form") {
			if(botoes[i].contexto == "list")
				jq("#"+botoes[i].uuid).addClass("hide-left");
			else 
				jq("#"+botoes[i].uuid).removeClass("hide-left");
		}
	}
}
/**
 * Alterar a visualiza��o do menu filho de acordo com a tela. 
 * @param tipo : valores permitidos: 'form' ou 'list'. Define qual a view ficar� vis�vel para o usu�rio
 * @param list : lista com ID dos bot�es do menu.
 */
function menuViewInclude(tipo, list){
	var botoes = JSON.parse(list);  	
	for(var i=0; i<botoes.length; i++){
		if(tipo=="list"){
			if(botoes[i].contexto == "form")
				jq("#"+botoes[i].uuid).addClass("hide");
			else 
				jq("#"+botoes[i].uuid).removeClass("hide");	
		}else if(tipo=="form") {
			if(botoes[i].contexto == "list")
				jq("#"+botoes[i].uuid).addClass("hide");
			else 
				jq("#"+botoes[i].uuid).removeClass("hide");
		}
	}
}
/**
 * Fun��o para exibir mensagens de notifica��o na tela 
 * @param tipo : String indicando o tipo da mensagem. Valores aceitos: {'info', 'sucesso', 'alerta', 'erro'}
 * @param mensagem : Mensagem a ser exibida
 */
function showMessage(tipo, mensagem) {
	if(tipo=='sucesso')
		tipo="success";
	else if (tipo=='alerta')
		tipo="warning";
	else if (tipo=='erro')
		tipo="danger";
	$('.notifications').notify({
	    message: { text: mensagem },
	    type: tipo
	}).show();
}

/**
 * Function para verificar se um elemento possui uma classe
 * 
 * @param element : elemento que ser� manipulado
 * @param cls : classe para verifica��o
 * @returns {Boolean} : Retorna TRUE Se a classe estiver declarada no elemento | Retorna FALSE Se a classe n�o estiver declarada no elemento 
 */
function hasClass(element, cls) {
    return (' ' + element.className + ' ').indexOf(' ' + cls + ' ') > -1;
}

var graficos = {};
/**
 * Fun��o para cria��o de gr�ficos
 * @param canvas : Elemento onde ser� desenhado o gr�fico
 * @param tipoGrafico : Tipo do gr�fico. Verificar tipos em http://www.chartjs.org/
 * @param dados : json com os Dados que ser�o inclu�dos no gr�fico
 */
function newGrafico(canvas, tipoGrafico, dados){
	var ctx = document.getElementById(canvas).getContext("2d");
	var divLegend = document.getElementById(canvas+"-legend"); 
	switch (tipoGrafico) {
	case "Bar":
		graficos[canvas] = new Chart(ctx).Bar(JSON.parse(dados));
		divLegend.innerHTML = graficos[canvas].generateLegend();
		break;
	case "Doughnut":
		graficos[canvas] = new Chart(ctx).Doughnut(JSON.parse(dados));
		divLegend.innerHTML = graficos[canvas].generateLegend();
		break;

	default:
		break;
	}
}

/*
 * @param value : data no formato ex.: "2015-01-28"
 * @return Retorna a mesma data com / ao invés de - e invertida Ex.: "28/01/2015"
 */
function formatDate(value) {
	 if(value){
		 var d = new Date(value);
		 return value.split("-").reverse().join("/");
	 }
}

function submitForm(arg){
	document.getElementById(arg).submit();
}

function changeAction(form, url) {
	var formulario = document.getElementById(form);
	formulario.action= url;
	formulario.submit();
}

/**
 * Alterar visualiza��o do elemento em modo 'Acordeon'
 * @param arg : String com ID do componente que dever� ser manipulado
 */
function viewAccordionMode(arg) {
	element = $("#"+arg); 
	if(element.hasClass("hide-accordion")){
		element.removeClass("hide-accordion");
	}else {
		element.addClass("hide-accordion");
	}
}

/**
 * Ativa visualização do dashboard em modo 'Shortcut'
 * @param arg : String com ID do componente que deverá ser manipulado
 */
function shortcutActive(arg) {
	element = $("#"+arg); 
	if(!element.hasClass("shortcut-active")){
		element.addClass("shortcut-active");
	}
}

/**
 * Desativa visualização do dashboard em modo 'Shortcut'
 * @param arg : String com ID do componente que deverá ser manipulado
 */
function shortcutDisable(arg) {
	element = $("#"+arg); 
	if(element.hasClass("shortcut-active")){
		element.removeClass("shortcut-active");
	}
}

/**
 * Alterar visualização do dashboard em modo 'Shortcut'
 * @param arg : String com ID do componente que deverá ser manipulado
 */
function shortcut(arg) {
	addRemoveClass(arg,"shortcut-active");
}

/**
 * Adiciona ou Remove uma classa a um elemento
 * @param uuid : String com ID do componente que deverá ser manipulado
 * @param c : String com a Classe que será adicionada ou removida
 */
function addRemoveClass(uiid, c) {
	element = $("#"+uiid);
	if(element.hasClass(c)){
		element.removeClass(c);
	}else {
		element.addClass(c);
	}
}

/**
 * Sobrescreve uma classa de um elemento
 * @param uuid : String com ID do componente que deverá ser manipulado
 * @param c1 : String com a Classe que será sobrescrita
 * @param c2 : String com a Classe que será adicionada
 */
function overwriteClass(uiid, c1, c2) {
	element = $("#"+uiid);
	if(element.hasClass(c1)){
		element.removeClass(c1);
		element.addClass(c2);
	}
}

/**
 * Adiciona uma classa a um elemento
 * @param uuid : String com ID do componente que deverá ser manipulado
 * @param c : String com a Classe que será adicionada
 */
function addClass(uiid, c) {
	element = $("#"+uiid);
	if(!element.hasClass(c)) {	element.addClass(c); }
}

/**
 * Remove uma classa a um elemento
 * @param uuid : String com ID do componente que deverá ser manipulado
 * @param c : String com a Classe que será removida
 */
function removeClass(uiid, c) {
	element = $("#"+uiid);
	if(element.hasClass(c)) {	element.removeClass(c); }
}

function setImagem(img1, img2) {
	element1 = document.getElementById(img1);
	element2 = document.getElementById(img2);
	element2.src = element1.src;
}

function startWeb() {
	Webcam.attach( '#my_camera' );
}

function webcamShowPreview(arg) {
	console.log(arg);
}

function webcamHidePreview(arg) {
	console.log(arg);
}

function take_snapshot(window) {
	var uri = null;
    Webcam.snap( function(data_uri) {
    	uri = data_uri;
    } );
    
    if(uri!=null){
	    zk.Widget.$(jq('$previa')[0]).smartUpdate('src',uri);
	    zk.Widget.$(jq('$previa')[0]).setSrc(uri);
	    zk.Widget.$(jq('$'+ window)[0]);
    }
    return uri;
}