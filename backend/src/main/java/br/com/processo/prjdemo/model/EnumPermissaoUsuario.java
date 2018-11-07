package br.com.processo.prjdemo.model;

/**
 * 
 * @author Guilherme Sena
 * ENUM que armazena os tipos possíveis de permissao
 *
 */
public enum EnumPermissaoUsuario {
	ADMINISTRADOR("A"),
	TRIADOR("T"),
	FINALIZADOR("F");
	
	private String sigla;
	
	private EnumPermissaoUsuario(String sigla) {
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}	
}
