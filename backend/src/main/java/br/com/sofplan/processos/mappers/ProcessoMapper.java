package br.com.sofplan.processos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.sofplan.processos.dto.v1.ProcessoDTO;
import br.com.sofplan.processos.models.Processo;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, 
	uses = { ParecerMapper.class, UsuarioMapper.class })
public interface ProcessoMapper {

	@Mapping(target = "responsaveis", ignore = true)
	ProcessoDTO toDTO(Processo processo);
	
	@Mapping(target = "responsaveis", ignore = true)
	Processo fromDTO(ProcessoDTO dto);
	
	Processo copy(Processo source, @MappingTarget Processo target);
	
}
