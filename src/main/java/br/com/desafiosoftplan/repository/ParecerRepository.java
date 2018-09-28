package br.com.desafiosoftplan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.desafiosoftplan.model.Parecer;

import br.com.desafiosoftplan.model.Usuario;

/**
 * Repositório da entidade {@link Parecer}
 * @author Setembro/2018: Jessica Etiene Marques Almeida
 */
public interface ParecerRepository  extends JpaRepository<Parecer, Long>
{
   @Query("SELECT p FROM Parecer p WHERE p.usuario = :usuario AND p.indicadorParecer = false")
   public List<Parecer> buscarProcessosPendentes(@Param("usuario") Usuario usuario);
}
