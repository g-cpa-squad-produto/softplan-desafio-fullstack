package br.com.softplan.marcusvoltolim.service;

import br.com.softplan.marcusvoltolim.model.Usuario;
import br.com.softplan.marcusvoltolim.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService extends AbstractService<Usuario> {
	
	private static final BCryptPasswordEncoder CRYPT = new BCryptPasswordEncoder();
	
	private final UsuarioRepository repository;
	
	@Autowired
	public UsuarioService(UsuarioRepository repository) {
		super(repository, Usuario.class);
		this.repository = repository;
	}
	
	//	public List<Parecer> findAllPendentesPorUsuario(Usuario responsavel) {
	//		return repository.findAllByUsuarioModificadorAndSituacao(responsavel, SituacaoParecer.PENDENTE);
	//	}
	//
	//	public Parecer getUltimoProduto() {
	//		return repository.findFirstByOrderByIdDesc();
	//	}
	
	@Override
	public List<Usuario> findAllBy(String filtro) {
		return repository.findAllByLoginLikeOrEmailLikeOrNomeCompletoLike(filtro, filtro, filtro);
	}
	
	@Override
	void updateAtributos(Usuario usuarioExistente, String dadosNovosJson) {
		Usuario usuarioDadosNovos = entityFromJson(dadosNovosJson);
		usuarioExistente.setSenha(CRYPT.encode(usuarioDadosNovos.getSenha()));
		usuarioExistente.setPermissao(usuarioDadosNovos.getPermissao());
		usuarioExistente.setNomeCompleto(usuarioDadosNovos.getNomeCompleto());
		usuarioExistente.setEmail(usuarioDadosNovos.getEmail());
	}
	
}
