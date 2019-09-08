package br.com.softplan.security.repository;

import br.com.softplan.security.entity.Papel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PapelRepository extends JpaRepository<Papel, Long> {
}
