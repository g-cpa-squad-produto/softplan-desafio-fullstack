package br.com.softplan.processos.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.softplan.processos.serializer.LocalDateSerializer;

@Entity
public class Processo implements Serializable {

    private static final long serialVersionUID = 1654167574634224620L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String titulo;

    @Size(min = 0, max = 500)
    private String descricao;

    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "data_criacao", columnDefinition = "DATE")
    private LocalDate dataCriacao;

    @ManyToMany
    @JoinTable(name = "processo_usuario_parecer", joinColumns = @JoinColumn(name = "codigo_processo"), inverseJoinColumns = @JoinColumn(name = "codigo_usuario"))
    private Set<Usuario> usuarios;

    public Processo() {
    }

    public Processo(Long codigo, String titulo, String descricao) {
	this.codigo = codigo;
	this.titulo = titulo;
	this.descricao = descricao;
	this.dataCriacao = LocalDate.now();
    }

    public Long getCodigo() {
	return codigo;
    }

    public void setCodigo(Long codigo) {
	this.codigo = codigo;
    }

    public String getTitulo() {
	return titulo;
    }

    public void setTitulo(String titulo) {
	this.titulo = titulo;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
	return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
	this.dataCriacao = dataCriacao;
    }

    public Set<Usuario> getUsuarios() {
	return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
	this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Processo other = (Processo) obj;
	if (codigo == null) {
	    if (other.codigo != null)
		return false;
	} else if (!codigo.equals(other.codigo))
	    return false;
	return true;
    }
}
