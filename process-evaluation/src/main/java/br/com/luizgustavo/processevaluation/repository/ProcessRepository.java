package br.com.luizgustavo.processevaluation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.luizgustavo.processevaluation.model.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {

	@Query(value = "select p.* from process p left join report r on r.idprocess = p.idprocess where r.idauthor = :idAuthor and r.text is null", nativeQuery = true)
	List<Process> findProcessListAuthorAndTextIsNull(@Param("idAuthor") Long idAuthor);
	
}
