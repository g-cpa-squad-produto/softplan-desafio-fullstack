package br.com.softplan.processos.model;

public enum Papel {

    ROLE_ADMIN, ROLE_USUARIO_TRIADOR, ROLE_USUARIO_FINALIZADOR;

    public static String[] names() {
	String[] names = new String[values().length];
	for (int index = 0; index < values().length; index++) {
	    names[index] = values()[index].name();
	}

	return names;
    }
}
