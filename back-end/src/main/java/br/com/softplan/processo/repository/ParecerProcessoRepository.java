package br.com.softplan.processo.repository;

import br.com.softplan.processo.dto.ParecerProcessoDTO;
import br.com.softplan.processo.entity.ParecerProcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParecerProcessoRepository extends JpaRepository<ParecerProcesso, Long> {

    @Query("SELECT new br.com.softplan.processo.dto.ParecerProcessoDTO(pa.id, pa.parecer, pa.usuario) FROM ParecerProcesso  pa WHERE pa.processo.id = :processoId")
    List<ParecerProcessoDTO> buscar(@Param("processoId") Long processoId);

    @Query("SELECT pa FROM ParecerProcesso  pa WHERE pa.processo.id = :processoId AND pa.usuario.id = :usuarioId")
    ParecerProcesso buscarPorUsuario(@Param("processoId") Long processoId, @Param("usuarioId") Long usuarioId);
}
