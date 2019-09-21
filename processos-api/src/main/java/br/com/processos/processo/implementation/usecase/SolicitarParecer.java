package br.com.processos.processo.implementation.usecase;

import br.com.processos.processo.implementation.repository.ParecerRepository;
import br.com.processos.processo.implementation.repository.ProcessoRepository;
import br.com.processos.processo.specification.entity.EnumParecerSituacao;
import br.com.processos.processo.specification.entity.Parecer;
import br.com.processos.processo.specification.entity.Processo;
import br.com.processos.processo.specification.exception.ProcessoNaoExistenteException;
import br.com.processos.usuario.specification.IUsuarioBusca;
import br.com.processos.usuario.specification.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class SolicitarParecer {

    @Autowired
    private ParecerRepository parecerRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private IUsuarioBusca iUsuarioBusca;

    public List<Parecer> executar(Long processoId, List<Long> usuariosId) {
        List<Usuario> usuarios = iUsuarioBusca.buscarPorIdIn(usuariosId);
        Processo processo = processoRepository.findById(processoId).orElse(null);
        validarProcessoExistente(processo);
        return criarSolicitaDeParecerParaUsuarios(processo, usuarios);
    }

    private List<Parecer> criarSolicitaDeParecerParaUsuarios(Processo processo, List<Usuario> usuarios) {
        List<Parecer> pareceres = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            Parecer parecer = criarParecer(processo, usuario);
            pareceres.add(parecerRepository.save(parecer));
        }
        return pareceres;
    }

    private Parecer criarParecer(Processo processo, Usuario usuario) {
        Parecer parecer = new Parecer();
        parecer.setProcesso(processo);
        parecer.setUsuario(usuario);
        parecer.setDataCriacao(new Date());
        parecer.setSituacao(EnumParecerSituacao.PENDENTE);
        return parecer;
    }

    private void validarProcessoExistente(Processo processo) {
        if (Objects.isNull(processo)) {
            throw new ProcessoNaoExistenteException();
        }
    }
}
