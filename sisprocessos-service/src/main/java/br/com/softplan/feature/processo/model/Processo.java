package br.com.softplan.feature.processo.model;

import br.com.softplan.core.model.AbstractEntity;
import br.com.softplan.feature.usuario.model.Usuario;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private Long idUsuarioParecer;
    @Enumerated(value = EnumType.STRING)
    private StatusProcesso status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "processo_usuario",
            joinColumns = @JoinColumn(name = "processo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> usuariosPermissao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USUARIO_PARECER", insertable = false, updatable = false)
    private Usuario usuarioParecer;

}
