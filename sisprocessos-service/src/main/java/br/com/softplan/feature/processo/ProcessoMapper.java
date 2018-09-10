package br.com.softplan.feature.processo;

import br.com.softplan.core.mapper.BaseMapper;
import br.com.softplan.feature.processo.dto.ProcessoDTO;
import br.com.softplan.feature.processo.dto.ProcessoResumidoDTO;
import br.com.softplan.feature.processo.model.Processo;
import br.com.softplan.feature.usuario.UsuarioMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface ProcessoMapper extends BaseMapper<Processo, ProcessoDTO, ProcessoResumidoDTO> {

}
