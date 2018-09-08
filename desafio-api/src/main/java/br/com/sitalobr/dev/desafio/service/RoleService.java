package br.com.sitalobr.dev.desafio.service;

import br.com.sitalobr.dev.desafio.entity.Role;
import br.com.sitalobr.dev.desafio.entity.RoleName;
import br.com.sitalobr.dev.desafio.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável por tratar as regras de negócio relacionadas a entidade {@link Role}
 */
@Service
public class RoleService extends AbstractService<Role, Long> {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Override
    public RoleRepository getRepository() {
        return this.roleRepository;
    }

    /**
     * Função responsável por recuperar todos os Usuários cadastrados
     * @return Lista de Roles registradas
     */
    public Iterable<Role> findAll() {
        return this.getRepository().findAll();
    }

    /**
     * Função responsável por recuperar uma {@link Role} a partir de seu nome
     * @param name String contendo o nome da role a ser pesquisada
     * @return Objeto {@link Role} ao obter sucesso na busca, null caso contrário
     */
    public Role findByName(RoleName name) {
        return this.getRepository().findByNome(name);
    }
}
