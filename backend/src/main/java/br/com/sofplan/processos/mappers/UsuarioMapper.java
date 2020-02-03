package br.com.sofplan.processos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.com.sofplan.processos.dto.v1.UsuarioDTO;
import br.com.sofplan.processos.models.Usuario;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioMapper {

	UsuarioDTO toDTO(Usuario usuario);

	Usuario fromDTO(UsuarioDTO dto);

	Usuario copy(Usuario source, @MappingTarget Usuario target);

}
