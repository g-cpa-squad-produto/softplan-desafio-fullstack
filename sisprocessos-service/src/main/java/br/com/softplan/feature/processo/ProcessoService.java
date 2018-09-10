package br.com.softplan.feature.processo;

import br.com.softplan.core.exception.NegocioException;
import br.com.softplan.core.service.AbstractCrudService;
import br.com.softplan.feature.processo.model.Processo;
import br.com.softplan.feature.processo.model.StatusProcesso;
import br.com.softplan.feature.usuario.UsuarioService;
import br.com.softplan.feature.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessoService extends AbstractCrudService<Processo, Long, ProcessoRepository> {

    private UsuarioService usuarioService;
    private ProcessoMapper mapper;

    @Autowired
    public ProcessoService(ProcessoRepository repository,
                           UsuarioService usuarioService,
                           ProcessoMapper mapper) {
        super(Processo.class, repository);
        this.usuarioService = usuarioService;
        this.mapper = mapper;
    }

    public void atribuirUsuariosAProcesso(Long idProcesso, List<Long> idUsuarios) throws NegocioException {
        Processo processo = pesquisarPorId(idProcesso).orElseThrow(() -> new NegocioException("processo.nao.encontrado"));
        List<Usuario> usuarios = this.usuarioService.pesquisarPorId(idUsuarios);

        processo.setUsuariosPermissao(usuarios);
        processo.setStatus(StatusProcesso.AGUARDANDO_PARECER);
        super.alterar(processo);
    }

    public void incluirParecer(Long idProcesso, String textoParecer) throws NegocioException {
        Processo processo = pesquisarPorId(idProcesso).orElseThrow(() -> new NegocioException("processo.nao.encontrado"));
        Usuario usuario = this.usuarioService.pesquisarPorId(1L).get(); //TODO pegar usuário logado

        processo.setParecer(textoParecer);
        processo.setIdUsuarioParecer(usuario.getId());
        processo.setUsuarioParecer(usuario);
        processo.setStatus(StatusProcesso.FINALIZADO);
        super.alterar(processo);
    }

    @Override
    public void excluir(Long id) {
        throw new UnsupportedOperationException("processo.nao.pode.ser.excluido");
    }
}
