package br.com.softplan.desafio.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProcessoDTO {

    private Long codigo;

    private String numero;

    private String parecer;

    private Status status;

    private List<UsuarioDTO> usuarios = new ArrayList<>();

}
