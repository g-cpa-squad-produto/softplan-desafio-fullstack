package br.com.softplan.processos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Permissao implements Serializable {

    private static final long serialVersionUID = 3235909884292816554L;

    @Id
    @NotNull
    @Size(min = 0, max = 50)
    private String nome;

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }
}
