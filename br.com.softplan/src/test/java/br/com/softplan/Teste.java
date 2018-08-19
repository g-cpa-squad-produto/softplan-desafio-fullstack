package br.com.softplan;

import org.junit.Test;

import br.com.softplan.controle.UsuarioCon;

public class Teste {

	@Test
	public void test() {
		UsuarioCon usuarioCon = new UsuarioCon();
		usuarioCon.salvar();	    
		usuarioCon.listar();
	}

}
