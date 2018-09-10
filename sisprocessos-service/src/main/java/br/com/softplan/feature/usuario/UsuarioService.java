package br.com.softplan.feature.usuario;

import br.com.softplan.core.exception.NegocioException;
import br.com.softplan.core.service.AbstractCrudService;
import br.com.softplan.feature.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends AbstractCrudService<Usuario, Long, UsuarioRepository> {

    private final UsuarioMapper mapper;

    @Autowired
    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        super(Usuario.class, repository);
        this.mapper = mapper;
    }

    @Override
    public void excluir(Long id) {
        //TODO validar se usuário tem parecer em processos
        super.excluir(id);
    }

    public boolean bloquearUsuario(Long id) throws NegocioException {
        Usuario usuario = pesquisarPorId(id).orElseThrow(() -> new NegocioException("usuario.nao.encontrado"));

        if (!usuario.isAtivo()) {
            throw new NegocioException("usuario.ja.esta.bloqueado");
        }

        usuario.setAtivo(false);
        repository.save(usuario);
        return true;
    }

    public boolean desbloquearUsuario(Long id) throws NegocioException {
        Usuario usuario = pesquisarPorId(id).orElseThrow(() -> new NegocioException("usuario.nao.encontrado"));

        if (usuario.isAtivo()) {
            throw new NegocioException("usuario.ja.esta.desbloqueado");
        }

        usuario.setAtivo(true);
        repository.save(usuario);
        return true;
    }

}
