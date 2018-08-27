package br.com.danilopaixao.ws.user;


public enum ProfileEnum {
	FINALIZADOR(1),
	ADMINISTRADOR(2),
	TRIADOR(3);
	
	private Integer id;
	
	ProfileEnum(Integer id) {
		this.id = id;
	}
	
	public static ProfileEnum getInstance(Integer id) {
		for (int i = 0; i < ProfileEnum.values().length; i++) {
			if(ProfileEnum.values()[i].getId().equals(id)){
				return ProfileEnum.values()[i]; 
			}
		}
		return null;
	}
	
	public static ProfileEnum getInstance(String strEnum) {
		for (int i = 0; i < ProfileEnum.values().length; i++) {
			if(ProfileEnum.values()[i].toString().equals(strEnum)){
				return ProfileEnum.values()[i]; 
			}
		}
		return null;
	}
	
	public Integer getId() {
		return this.id;
	}
}
