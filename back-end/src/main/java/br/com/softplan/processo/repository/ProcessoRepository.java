package br.com.softplan.processo.repository;

import br.com.softplan.processo.entity.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    public Processo findByNumero(String numero);
}
