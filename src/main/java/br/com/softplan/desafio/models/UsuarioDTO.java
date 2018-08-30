package br.com.softplan.desafio.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO {

    private Long codigo;

    private String nome;

    private Perfil perfil;

}
