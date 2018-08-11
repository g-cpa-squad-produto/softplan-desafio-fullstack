package br.com.softplan.repository;

import br.com.softplan.entidades.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {
}
