package br.com.softplan.processo.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuario")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codigo")
    @SequenceGenerator(name = "usuario_codigo_seq", sequenceName = "usuario_codigo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_codigo_seq")
    private Long codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "observacao")
    private String observacao;
}
