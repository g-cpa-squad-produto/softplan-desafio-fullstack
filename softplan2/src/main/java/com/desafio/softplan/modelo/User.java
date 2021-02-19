package com.desafio.softplan.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    /**
     * administrador       : 1
     * usuário-triador     : 2
     * usuário-finalizador : 3
     */

    @Id
    private String id_user;
    private String name;
    private String tipo;


}
