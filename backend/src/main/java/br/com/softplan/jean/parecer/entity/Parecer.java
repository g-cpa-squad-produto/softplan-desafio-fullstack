package br.com.softplan.jean.parecer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.softplan.jean.processo.entity.Processo;
import br.com.softplan.jean.usuario.entity.Usuario;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"usuario_id" , "processo_id"})})
public class Parecer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8437739504590600710L;

	@Id
    @GeneratedValue(generator = "parecer_generator")
    @SequenceGenerator(name = "parecer_generator", sequenceName = "parecer_sequence", initialValue = 1)
	private Long id;
	
	@Column(columnDefinition = "text")
	@NotBlank
	@Size(max = 200)
    private String titulo;
	
	@Column(columnDefinition = "text")
	@NotBlank
    private String descricao;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@NotNull
	private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Processo processo;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Processo getProcesso() {
		return processo;
	}
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}   
    
}
