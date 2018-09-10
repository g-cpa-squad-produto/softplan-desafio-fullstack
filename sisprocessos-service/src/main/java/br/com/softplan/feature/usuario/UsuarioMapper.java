package br.com.softplan.feature.usuario;

import br.com.softplan.core.mapper.BaseMapper;
import br.com.softplan.feature.usuario.dto.UsuarioDTO;
import br.com.softplan.feature.usuario.dto.UsuarioResumidoDTO;
import br.com.softplan.feature.usuario.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDTO, UsuarioResumidoDTO> {

}
