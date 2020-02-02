package br.com.sofplan.processos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sofplan.processos.models.ProcessoUsuario;
import br.com.sofplan.processos.models.ProcessoUsuarioID;

@Repository
public interface ProcessoUsuarioRepository extends JpaRepository<ProcessoUsuario, ProcessoUsuarioID> {

}
