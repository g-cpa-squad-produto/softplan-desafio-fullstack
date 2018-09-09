package br.com.softplan.core.mapper;

import java.util.Collection;
import java.util.List;

/**
 * Interface padrão para as classes Mappers (repsonsáveis por converter de entidade para DTO e vice-versa) do projeto
 *
 * @param <ENTIDADE> Entidade
 * @param <DTO>      DTO das telas de cadastro/alteração
 * @param <DTOLIST>  DTO da tela de listagem
 * @author Samuel Correia Guimarães
 */
public interface BaseMapper<ENTIDADE, DTO, DTOLIST> {

    ENTIDADE paraDocumento(DTO dto);

    DTO paraDTO(ENTIDADE documento);

    DTOLIST paraDTOResumido(ENTIDADE documento);

    List<DTOLIST> paraListaDTOResumido(Collection<ENTIDADE> documentos);

}