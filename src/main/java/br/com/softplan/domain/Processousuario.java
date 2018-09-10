package br.com.softplan.domain;

import javax.persistence.*;

@Entity
@Table(name = "processo_usuario")
public class Processousuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_processo_fk")
    private Processo processo;

    @ManyToOne
    @JoinColumn(name = "id_usuario_fk")
    private Usuarios usuarios;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
}
