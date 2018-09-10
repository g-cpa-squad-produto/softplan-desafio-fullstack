package br.com.softplan.feature.processo.model;

import br.com.softplan.core.model.AbstractEntity;
import br.com.softplan.feature.usuario.model.Usuario;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Processo extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long numero;
    private LocalDate data;
    private String descricao;
    private String parecer;
    @Column(name = "ID_USUARIO_PARECER")
    private Usuario usuarioParecer;
    @Enumerated(value = EnumType.STRING)
    private StatusProcesso status;

}
