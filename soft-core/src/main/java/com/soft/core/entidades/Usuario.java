/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soft.core.entidades;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author Moquiuti
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario extends Pessoa {

    @NotEmpty(message = "Senha não pode ser vazio")
    @Size(min = 7, max = 40, message = "senha entre 7 e 40 caracteres")
    private String senhaAcesso;

    /**
     * ADMINISTRADOR, TRIADOR, FINALIZADOR
     */
    private String tipoUsuario;

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
