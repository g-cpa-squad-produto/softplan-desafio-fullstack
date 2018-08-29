package br.com.softplan.desafio.bo;

import br.com.softplan.desafio.models.Processo;
import br.com.softplan.desafio.models.Status;
import br.com.softplan.desafio.models.Usuario;
import br.com.softplan.desafio.repository.ProcessoRepository;
import br.com.softplan.desafio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ProcessoBO {

    protected static final String USUARIO_NOT_PERFIL = "O usuário não tem perfil para alteração";

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Processo> findAllPendentes() {
        return processoRepository.findAllPendentes();
    }

    @Transactional(readOnly = true)
    public List<Processo> findAll() {
        return processoRepository.findAll();
    }

    @Transactional
    public Processo salva(Processo processo, Long usuarioCodigo) {

        validaUsuarioPerfil(usuarioCodigo);

        processo.setStatus(Status.PDT);
        processo.setDataCadastro(LocalDate.now());

        return processoRepository.save(processo);
    }

    @Transactional
    public Processo update(Processo processo, Long usuarioCodigo) {

        processoRepository.getByCodigo(processo.getCodigo()).orElseThrow(() -> new RuntimeException("Processo não encontrado"));

        Usuario usuario = validaUsuarioPerfil(usuarioCodigo);

        processo.setStatus(Status.FNL);
        processo.setDataFinalizacao(LocalDate.now());
        processo.setUsuarioFinalizacao(usuario);

        return processoRepository.save(processo);
    }

    private Usuario validaUsuarioPerfil(Long usuarioCodigo) {

        Optional<Usuario> usuario = usuarioRepository.findByCodigoAndPerfilFinalizador(usuarioCodigo);

        usuario.orElseThrow(() -> new RuntimeException(USUARIO_NOT_PERFIL));

        return usuario.get();
    }


}
