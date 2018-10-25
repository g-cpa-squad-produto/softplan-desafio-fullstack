package br.com.softplan.parecer.dto;

import java.sql.Timestamp;

import br.com.softplan.parecer.modelos.Parecer;
import br.com.softplan.processo.dto.ProcessoDto;
import br.com.softplan.usuario.dto.UsuarioDto;

/**
 * @author emanuel
 *
 */
public class ParecerDto {

	public Integer id;

	public String nome;

	public Timestamp dataHora;

	public ProcessoDto processo;

	public UsuarioDto usuario;

	public ParecerDto(Parecer parecer) {
		this.id = parecer.getId();
		this.nome = parecer.getNome();
		this.dataHora = parecer.getDataHora();
		this.processo = new ProcessoDto(parecer.getProcesso());
		this.usuario = new UsuarioDto(parecer.getUsuario());
	}
}
