package br.com.softplan.desafiojava.business;

import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softplan.desafiojava.entity.Processo;
import br.com.softplan.desafiojava.entity.Usuario;
import br.com.softplan.desafiojava.repository.ProcessoJpaRepository;

@Service
@Transactional
public class ProcessoBusiness {

	@Autowired
	ProcessoJpaRepository repository;
	
	public List<Processo> listarTodos() {
		return repository.findAll();
	}
	
	public Processo consultar(Long id) {
		Optional<Processo> op = repository.findById(id);
		if (!op.isPresent()) {
			throw new ValidationException("Processo não localizado.");
		}
		return op.get();
	}
	
	/**
	 * Processos pendentes de um usuário
	 * @param id Identificador do usuário que está solicitando a lista
	 * @return Lista de processos
	 */
	public List<Processo> listarPorUsuario(Long id) {
		return repository.findByUsuario(id);
	}
	
	public void incluir(Processo processo) {
		repository.save(processo);
	}
	
	/**
	 * Associa o(s) usuário(s) ao processo informado 
	 * @param listaUsuarios Lista de usuários a serem associados
	 * @param id Identificador do processo.
	 */
	public Processo associarUsuarios(List<Usuario> listaUsuarios, Long id) {
		Processo processo = consultar(id);
		processo.setFinalizadores(listaUsuarios);
		repository.save(processo);
		return processo;
	}
}
