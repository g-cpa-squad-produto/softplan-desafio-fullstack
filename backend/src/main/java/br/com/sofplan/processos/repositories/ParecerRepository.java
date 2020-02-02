package br.com.sofplan.processos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sofplan.processos.models.Parecer;

@Repository
public interface ParecerRepository extends JpaRepository<Parecer, Long> {

}
