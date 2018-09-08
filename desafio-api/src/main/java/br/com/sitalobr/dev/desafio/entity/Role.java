package br.com.sitalobr.dev.desafio.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Classe entidade que contém os dados de permissões
 */
@Entity(name = "role")
public class Role implements InterfaceEntity<Long> {
    private static final long serialVersionUID = -1692686748768023661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome", nullable = false)
    private RoleName nome;

    public Role() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getNome() {
        return nome;
    }

    public void setNome(RoleName nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) &&
                getNome() == role.getNome();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getNome());
    }
}
