package br.com.softplan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.models.Feedback;
import br.com.softplan.models.Proccess;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, Long>{
	public List<Feedback> findAllByProcess(Proccess p);
}
