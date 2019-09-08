package br.com.softplan.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UsuarioLogadoDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private List<String> permissoes;


}
