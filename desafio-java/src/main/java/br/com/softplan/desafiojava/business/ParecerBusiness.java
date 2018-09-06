package br.com.softplan.desafiojava.business;

import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softplan.desafiojava.entity.Parecer;
import br.com.softplan.desafiojava.entity.Usuario;
import br.com.softplan.desafiojava.entity.enums.TipoUsuario;
import br.com.softplan.desafiojava.repository.ParecerJpaRepository;

@Service
@Transactional
public class ParecerBusiness {

	@Autowired
	ParecerJpaRepository repository;
	
	@Autowired
	UsuarioBusiness usuarioBusiness;

	public Parecer consultar(Long id) {
		Optional<Parecer> op = repository.findById(id);
		if (!op.isPresent()) {
			throw new ValidationException("Parecer não encontrado");
		}
		return op.get();
	}
	
	/**
	 * Pareceres de um determinado processo
	 * @param id Identificador do processo
	 * @return Lista de pareceres
	 */
	public List<Parecer> listarPorProcesso(Long id) {
		return repository.findByProcesso(id);
	}

	public Parecer incluir(Parecer parecer) {
		Usuario relator = usuarioBusiness.consultar(parecer.getRelator().getId());
		if (relator.getTipo() != TipoUsuario.FINALIZADOR) {
			throw new ValidationException("Este usuario não pode emitir parecer.");
		}
		
		return repository.save(parecer);
	}
}
