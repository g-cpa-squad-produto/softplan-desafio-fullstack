package br.com.ramonbarros.enuns;

public enum StatusProcessoEnum {
	
	PENDENTE(1, "Pendente"),
	FINALIZADO(2, "Finalizado");
	
	private int cod;
	private String descricao;
	
	private StatusProcessoEnum(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static StatusProcessoEnum toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (StatusProcessoEnum x : StatusProcessoEnum.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
