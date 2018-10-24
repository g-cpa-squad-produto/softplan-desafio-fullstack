package br.com.softplan.models;

public enum ProfileTypes {

	ADMIN("ADMIN"), TRIADOR("TRIADOR"), FINALIZADOR("FINALIZADOR");

	public String profile;

	ProfileTypes(String profile) {
		this.profile = profile;
	}

}
