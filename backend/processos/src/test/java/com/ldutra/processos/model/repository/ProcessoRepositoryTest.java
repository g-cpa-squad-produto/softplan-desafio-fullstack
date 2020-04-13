package com.ldutra.processos.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ldutra.processos.model.entity.Processo;
import com.ldutra.processos.model.enums.StatusProcesso;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class ProcessoRepositoryTest {

	@Autowired
	ProcessoRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveSalvarUmProcesso() {
		Processo processo = criarProcesso();
		
		processo = repository.save(processo);
		
		assertThat(processo.getId()).isNotNull();
	}

	@Test
	public void deveDeletarUmProcesso() {
		Processo processo = criarEPersistirUmProcesso();
		
		processo = entityManager.find(Processo.class, processo.getId());
		
		repository.delete(processo);
		
		Processo processoInexistente = entityManager.find(Processo.class, processo.getId());
		assertThat(processoInexistente).isNull();
	}

	
	@Test
	public void deveAtualizarUmProcesso() {
		Processo processo = criarEPersistirUmProcesso();
		
		processo.setAno(2018);
		processo.setParecer("Teste Atualizar");
		processo.setStatus(StatusProcesso.CANCELADO);
		
		repository.save(processo);
		
		Processo processoAtualizado = entityManager.find(Processo.class, processo.getId());
		
		assertThat(processoAtualizado.getAno()).isEqualTo(2018);
		assertThat(processoAtualizado.getParecer()).isEqualTo("Teste Atualizar");
		assertThat(processoAtualizado.getStatus()).isEqualTo(StatusProcesso.CANCELADO);
	}
	
	@Test
	public void deveBuscarUmProcessoPorId() {
		Processo processo = criarEPersistirUmProcesso();
		
		Optional<Processo> processoEncontrado = repository.findById(processo.getId());
		
		assertThat(processoEncontrado.isPresent()).isTrue();
	}

	private Processo criarEPersistirUmProcesso() {
		Processo processo = criarProcesso();
		entityManager.persist(processo);
		return processo;
	}
	
	public static Processo criarProcesso() {
		return Processo.builder()
									.ano(2019)
									.mes(1)
									.parecer("processo qualquer")
									.status(StatusProcesso.PENDENTE)
									.dataCadastro(LocalDate.now())
									.build();
	}
	
	
	
	
	
}

