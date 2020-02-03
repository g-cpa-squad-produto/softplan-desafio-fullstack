package br.com.sofplan.processos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.sofplan.processos.dto.v1.ParecerDTO;
import br.com.sofplan.processos.models.Parecer;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, 
	uses = { ProcessoMapper.class, UsuarioMapper.class })
public interface ParecerMapper {

	ParecerDTO toDTO(Parecer parecer);
	
	Parecer fromDTO(ParecerDTO dto);
	
	Parecer copy(Parecer source, @MappingTarget Parecer target);
	
}
