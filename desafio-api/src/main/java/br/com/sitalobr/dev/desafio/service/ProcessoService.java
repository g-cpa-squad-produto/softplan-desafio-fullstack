package br.com.sitalobr.dev.desafio.service;

import br.com.sitalobr.dev.desafio.dto.ProcessoCadastroDTO;
import br.com.sitalobr.dev.desafio.dto.ProcessoFinalizadorDTO;
import br.com.sitalobr.dev.desafio.entity.Processo;
import br.com.sitalobr.dev.desafio.entity.Usuario;
import br.com.sitalobr.dev.desafio.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

/**
 * Classe responsável por tratar as regras de negócio relacionadas a entidade {@link Processo}
 */
@Service
public class ProcessoService extends AbstractService<Processo, Long> {
    private final ProcessoRepository processoRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public ProcessoService(ProcessoRepository processoRepository, UsuarioService usuarioService) {
        this.processoRepository = processoRepository;
        this.usuarioService = usuarioService;
    }
    
    @Override
    public ProcessoRepository getRepository() {
        return this.processoRepository;
    }

    /**
     * Função responsável por recuperar todos os Processos cadastrados
     * @return Lista de Processos registrados
     */
    public Iterable<Processo> findAll() {
        return this.getRepository().findAll();
    }

    /**
     * Função responsável por recuperar todos os Processos cadastrados relacionados a um finalizdaor
     * @param usernameFinalizador String contendo o username do usuário finalizddor a ser pesquisado
     * @return Lista de Processos registrados
     */
    public Iterable<Processo> findAllByFinalizador(String usernameFinalizador) {
        Usuario finalizador = this.usuarioService.findByUsername(usernameFinalizador);
        Set<Usuario> finalizadores = Collections.singleton(finalizador);
        return this.getRepository().findAllByFinalizadores(finalizadores);
    }

    /**
     * Função responsável por registrar um {@link Processo}; caso o ID especificado seja null, um novo registro é cadastrado;
     * caso o ID esteja presente, o registro existente é atualizado
     * @param entity Objeto {@link Processo} contendo os dados a serem registrados
     * @return Objeto {@link Processo} recém-registrado
     */
    private Processo save(Processo entity) {
        return this.getRepository().save(entity);
    }

    /**
     * Função responsável por cadastrar um novo registro de {@link Processo}
     * @param request Objeto contendo os dados de {@link Processo}
     * @param usernameResponsavel String contendo o username do usuário responsável (criador)
     * @return Objeto {@link Processo} recém-cadastrado
     */
    public Processo create(ProcessoCadastroDTO request, String usernameResponsavel) {
        Usuario finalizador = this.usuarioService.findById(request.getFinalizador());
        Usuario responsavel = this.usuarioService.findByUsername(usernameResponsavel);

        if (finalizador == null)
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "O usuário finalizador informado não existe.");

        Processo processo = new Processo(request.getDescricao(), LocalDateTime.now(), responsavel,
                Collections.singleton(finalizador));

        return this.save(processo);
    }

    /**
     * Função responsável por finalizar um registro de {@link Processo}
     * @param request Objeto contendo os dados de {@link Processo}
     */
    public void finalizar(ProcessoFinalizadorDTO request) {
        Processo processoAFinalizar = this.findById(request.getIdProcesso());

        if (processoAFinalizar == null)
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "O processo informado não existe.");

        processoAFinalizar.setParecer(request.getParecer());

        this.save(processoAFinalizar);
    }

    /**
     * Função responsável por atualizar um registro de {@link Processo}
     * @param id Long contendo o ID da {@link Processo} a ser atualizado
     * @param request Objeto contendo os dados de {@link Processo}
     * @return Objeto {@link Processo} recém-atualizado
     */
    public Processo update(Long id, Processo request) {
        Processo processo = this.findById(id);
        processo.setDescricao(request.getDescricao());

        return this.save(processo);
    }

    /**
     * Função responsável por excluir {@link Processo}
     * @param id Long contendo o ID do {@link Processo} a ser excluído
     */
    public void delete(Long id) {
        this.getRepository().deleteById(id);
    }
}
