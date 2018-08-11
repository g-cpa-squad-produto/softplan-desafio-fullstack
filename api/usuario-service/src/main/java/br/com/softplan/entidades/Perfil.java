package br.com.softplan.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "perfil")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codigo")
    @SequenceGenerator(name = "perfil_codigo_seq", sequenceName = "perfil_codigo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_codigo_seq")
    private Long codigo;

    @Column(name = "nome_perfil")
    private String nomePerfil;

    @Column(name = "descricao_perfil")
    private String descricaoPerfil;

}
