package com.isadora.backendapi.repositories;

import com.isadora.backendapi.domain.Parecer;
import com.isadora.backendapi.domain.Processo;
import com.isadora.backendapi.domain.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParecerRepository extends CrudRepository<Parecer, Long>{

    Parecer findByProcesso(Processo processo);
    Parecer findByUsuario(Usuario usuario);

}
