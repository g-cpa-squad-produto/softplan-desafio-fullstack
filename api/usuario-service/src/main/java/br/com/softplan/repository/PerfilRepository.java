package br.com.softplan.repository;

import br.com.softplan.entidades.Perfil;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PerfilRepository extends PagingAndSortingRepository<Perfil, Long> {
}
