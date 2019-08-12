package com.renantabaresmachado.enuns;

public enum Perfil {
	ADMIN(1, "ROLE_ADMIN"),
	TRIADOR(2, "ROLE_TRIADOR"),
	FINALIZADOR(3, "ROLE_FINALIZADOR");
	
	private int cod;
	private String descricao;
	
	private Perfil (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+ cod);
	}

}
