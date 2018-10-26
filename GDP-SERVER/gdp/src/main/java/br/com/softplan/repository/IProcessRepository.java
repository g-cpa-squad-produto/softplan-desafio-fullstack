package br.com.softplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.models.Proccess;

@Repository
public interface IProcessRepository extends JpaRepository<Proccess, Long>{

}
