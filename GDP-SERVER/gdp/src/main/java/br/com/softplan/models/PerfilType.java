package br.com.softplan.models;

public enum PerfilType {

	ADMIN("ADMIN"), TRIADOR("TRIADOR"), FINALIZADOR("FINALIZADOR");

	public String perfil;

	PerfilType(String perfil) {
		this.perfil = perfil;
	}

}
