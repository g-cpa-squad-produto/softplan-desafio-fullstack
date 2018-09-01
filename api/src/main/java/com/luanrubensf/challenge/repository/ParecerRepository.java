package com.luanrubensf.challenge.repository;

import com.luanrubensf.challenge.model.Parecer;
import com.luanrubensf.challenge.model.Processo;
import com.luanrubensf.challenge.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ParecerRepository extends CrudRepository<Parecer, Long> {

    List<Parecer> findByUser(User user);

    Parecer findParecerById(Long id);

    boolean existsByUserAndProcesso(User user, Processo processo);

    boolean existsByUserAndProcessoAndIdIsNot(User user, Processo processo, Long id);
}

