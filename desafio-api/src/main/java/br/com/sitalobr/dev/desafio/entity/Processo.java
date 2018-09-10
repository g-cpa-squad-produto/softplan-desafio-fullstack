package br.com.sitalobr.dev.desafio.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Classe entidade que contém os dados de processos
 */
@Entity(name = "processo")
public class Processo implements InterfaceEntity<Long> {
    private static final long serialVersionUID = -3590564401982738453L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "parecer")
    private String parecer;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(optional = false)
    private Usuario responsavel;

    @ManyToMany
    @JoinTable(name = "usuario_processo",
            joinColumns = {@JoinColumn(name = "processo_id")},
            inverseJoinColumns = {@JoinColumn(name = "usuario_id")})
    private Set<Usuario> finalizadores;

    public Processo() {
    }

    public Processo(String descricao, LocalDateTime dataCriacao, Usuario responsavel, Set<Usuario> finalizadores) {
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.responsavel = responsavel;
        this.finalizadores = finalizadores;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Set<Usuario> getFinalizadores() {
        return finalizadores;
    }

    public void setFinalizadores(Set<Usuario> finalizadores) {
        this.finalizadores = finalizadores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processo processo = (Processo) o;
        return Objects.equals(getId(), processo.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
