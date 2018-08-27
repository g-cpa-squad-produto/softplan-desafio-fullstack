package br.com.danilopaixao.ws.user;


public enum UserStatusEnum {
	ATIVO,
	INATIVO;
	public static UserStatusEnum getInstance(String strEnum) {
		for (int i = 0; i < UserStatusEnum.values().length; i++) {
			if(UserStatusEnum.values()[i].toString().equals(strEnum)){
				return UserStatusEnum.values()[i]; 
			}
		}
		return null;
	}
}
