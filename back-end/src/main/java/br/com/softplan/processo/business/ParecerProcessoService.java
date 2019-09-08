package br.com.softplan.processo.business;

import br.com.softplan.processo.dto.ParecerProcessoDTO;
import br.com.softplan.processo.entity.ParecerProcesso;
import br.com.softplan.processo.entity.Processo;
import br.com.softplan.processo.exception.ParecerProcessoNaoExistenteException;
import br.com.softplan.processo.exception.ProcessoNaoExistenteException;
import br.com.softplan.processo.exception.UsuarioJaPossuiParecerProcessoException;
import br.com.softplan.processo.repository.ParecerProcessoRepository;
import br.com.softplan.processo.repository.ProcessoRepository;
import br.com.softplan.security.dto.UsuarioDTO;
import br.com.softplan.security.entity.Papel;
import br.com.softplan.security.entity.Usuario;
import br.com.softplan.security.exception.UsuarioNaoExistenteException;
import br.com.softplan.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ParecerProcessoService {

    @Autowired
    private ParecerProcessoRepository repository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<ParecerProcessoDTO> buscarTodos(Long processoId){
        return repository.buscar(processoId);
    }

    public ParecerProcessoDTO incluirParecer(Long id, ParecerProcessoDTO parecerProcessoDTO) {
        ParecerProcesso parecerExistente = repository.findById(id).orElseThrow(() -> new ParecerProcessoNaoExistenteException());
        parecerExistente.setParecer(parecerProcessoDTO.getParecer());
        repository.save(parecerExistente);
        return new ParecerProcessoDTO(parecerExistente);
    }

    public void adicionarUsuario(Long processoId, UsuarioDTO usuarioDTO) {

        Processo processo = buscarProcesso(processoId);
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getId()).orElseThrow(() -> new UsuarioNaoExistenteException());

        validarUsuarioJaPossuiPareceProcesso(processo, usuario);

        criarNovoParecer(processo, usuario);
    }

    public ParecerProcessoDTO buscarAtual(Long processoId) {

        Processo processo = buscarProcesso(processoId);

        Usuario usuario = new Usuario();
        usuario.setNome("ddf");
        usuario.setPapel( new Papel());



        ParecerProcesso parecerProcesso = new ParecerProcesso();
        parecerProcesso.setId(1L);
        parecerProcesso.setUsuario(usuario);
        parecerProcesso.setProcesso(processo);

        return new ParecerProcessoDTO(parecerProcesso);
    }

    private Processo buscarProcesso(Long processoId){
        return processoRepository.findById(processoId).orElseThrow(() -> new ProcessoNaoExistenteException());
    }

    private void validarUsuarioJaPossuiPareceProcesso(Processo processo, Usuario usuario) {
        ParecerProcesso parecerProcesso = repository.buscarPorUsuario(processo.getId(), usuario.getId());
        if(Objects.nonNull(parecerProcesso)){
            throw new UsuarioJaPossuiParecerProcessoException();
        }
    }

    private ParecerProcesso criarNovoParecer(Processo processo, Usuario usuario){
        ParecerProcesso parecerProcesso = new ParecerProcesso();
        parecerProcesso.setProcesso(processo);
        parecerProcesso.setUsuario(usuario);
        return repository.save(parecerProcesso);
    }
}
