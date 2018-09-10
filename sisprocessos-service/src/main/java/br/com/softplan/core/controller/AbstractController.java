package br.com.softplan.core.controller;

import br.com.softplan.core.exception.NegocioException;
import br.com.softplan.core.mapper.BaseMapper;
import br.com.softplan.core.model.AbstractEntity;
import br.com.softplan.core.service.AbstractCrudService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * Classe abstrata com as operações e comportamentos padrão para os controladores REST do projeto
 *
 * @param <ENTIDADE> Entidade que o controller gerencia
 * @param <DTO>      DTO das telas de cadastro/alteração
 * @param <DTOLIST>  DTO da tela de listagem
 * @param <ID>       Classe que representa a chave primaria da entidade
 * @param <MAPPER>   Mapper que converte as entidade em DTOs e vice-versa
 * @author Samuel Correia Guimarães
 */
public abstract class AbstractController<ENTIDADE extends AbstractEntity, DTO, DTOLIST, ID extends Serializable, S extends AbstractCrudService<ENTIDADE, ID, ?>, MAPPER extends BaseMapper<ENTIDADE, DTO, DTOLIST>> {

    protected S service;
    protected MAPPER mapper;

    public AbstractController(S service, MAPPER mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "")
    public List<DTOLIST> buscaTodos() {
        List<ENTIDADE> data = service.pesquisarTodos();
        return mapper.paraListaDTOResumido(data);
    }

    @GetMapping(path = "/{id}")
    public DTO pesquisarPorId(@PathVariable("id") ID id) throws NegocioException {
        return mapper.paraDTO(service.pesquisarPorId(id).orElseThrow(
                () -> new NegocioException("Registro não encontrado")));
    }

    @PostMapping(path = "/filtro")
    public Page<DTOLIST> filtrar(@RequestBody(required = false) JsonNode filtro, Pageable paginacao) {
        Page<ENTIDADE> data = service.filtrar(filtro, paginacao);
        return new PageImpl<>(mapper.paraListaDTOResumido(data.getContent()), paginacao, data.getTotalElements());
    }

    @PostMapping(path = "")
    public DTO cadastrar(@RequestBody DTO dto) {
        ENTIDADE entidade = mapper.paraEntidade(dto);
        return mapper.paraDTO(service.cadastrar(entidade));
    }

    @PutMapping(path = "")
    public DTO alterar(@RequestBody DTO dto) {
        ENTIDADE entidade = mapper.paraEntidade(dto);
        return mapper.paraDTO(service.alterar(entidade));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity excluir(@PathVariable ID id) {
        service.excluir(id);
        return ResponseEntity.ok().build();
    }

}