package br.com.softplan.processo.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.softplan.processo.modelos.Processo;
import br.com.softplan.usuario.dto.UsuarioDto;
/**
 * @author emanuel
 *
 */
public class ProcessoDto {

	public Integer id;

	public String nome;

	public Timestamp dataHora;

	public List<UsuarioDto> usuarios = new ArrayList<>();

	public Boolean finalizado;

	public ProcessoDto(Processo processo) {
		this.id = processo.getId();
		this.nome = processo.getNome();
		this.dataHora = processo.getDataHora();
		this.finalizado = processo.getFinalizado();
		processo.getUsuarios().forEach(usuario -> {
			usuarios.add(new UsuarioDto(usuario));
		});
	}
}
