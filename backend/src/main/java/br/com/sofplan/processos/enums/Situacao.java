package br.com.sofplan.processos.enums;

import java.util.Arrays;

public enum Situacao {

	PENDENTE, ACEITO, NEGADO;
	
	public static Situacao from(String situacao) {
        return Arrays.asList(Situacao.values()).stream().filter(r -> r.toString().equals(situacao))
                .findAny().orElseThrow(IllegalArgumentException::new);
    }
	
}
