package br.com.softplan.desafiojava.business;

import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softplan.desafiojava.entity.Usuario;
import br.com.softplan.desafiojava.repository.UsuarioJpaRepository;

@Service
@Transactional
public class UsuarioBusiness {

	@Autowired
	UsuarioJpaRepository repository;
	
	public List<Usuario> listarTodos() {
		return repository.findAll();
	}
	
	public Usuario consultar(Long id) {
		Optional<Usuario> op = repository.findById(id);
		if (!op.isPresent()) {
			throw new ValidationException("Usuário não encontrado");
		}
		return op.get();
	}
	
	public Usuario incluir(Usuario usuario) {
		return repository.save(usuario); 
	}
	
	public Usuario alterar(Usuario usuario) {
		return repository.save(usuario); 
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}

}
