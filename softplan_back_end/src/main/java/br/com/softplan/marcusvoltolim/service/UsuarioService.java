package br.com.softplan.marcusvoltolim.service;

import br.com.softplan.marcusvoltolim.enums.Permissao;
import br.com.softplan.marcusvoltolim.model.Usuario;
import br.com.softplan.marcusvoltolim.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<String> findAllNomeUsuarioFinalizadores() {
		return repository.findAllByPermissao(Permissao.FINALIZADOR).stream().map(Usuario::getLogin)
				.collect(Collectors.toList());
	}
	
	@Override
	void updateAtributos(Usuario usuarioExistente, String dadosNovosJson) {
		Usuario usuarioDadosNovos = entityFromJson(dadosNovosJson);
		usuarioExistente.setPermissao(usuarioDadosNovos.getPermissao());
		usuarioExistente.setNomeCompleto(usuarioDadosNovos.getNomeCompleto());
		usuarioExistente.setEmail(usuarioDadosNovos.getEmail());
		if (!usuarioDadosNovos.getSenha().isEmpty()) {
			usuarioExistente.setSenha(CRYPT.encode(usuarioDadosNovos.getSenha()));
		}
	}
	
}
