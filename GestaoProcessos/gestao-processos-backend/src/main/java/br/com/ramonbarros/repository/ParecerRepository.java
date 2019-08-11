package br.com.ramonbarros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import br.com.ramonbarros.entity.Parecer;

@Repository
public interface ParecerRepository extends JpaRepositoryImplementation<Parecer,Long> {

	List<Parecer> findByIdProcesso(Long idProcesso);
}
