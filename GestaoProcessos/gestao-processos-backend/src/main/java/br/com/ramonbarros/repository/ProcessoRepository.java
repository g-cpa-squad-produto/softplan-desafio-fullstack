package br.com.ramonbarros.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import br.com.ramonbarros.entity.Processo;

@Repository
public interface ProcessoRepository extends JpaRepositoryImplementation<Processo,Long> {

}
