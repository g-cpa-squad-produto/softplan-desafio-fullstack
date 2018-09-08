package br.com.sitalobr.dev.desafio.service;

import br.com.sitalobr.dev.desafio.dto.UsuarioCadastroDTO;
import br.com.sitalobr.dev.desafio.entity.Role;
import br.com.sitalobr.dev.desafio.entity.RoleName;
import br.com.sitalobr.dev.desafio.entity.Usuario;
import br.com.sitalobr.dev.desafio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.Collections;

/**
 * Classe responsável por tratar as regras de negócio relacionadas a entidade {@link Usuario}
 */
@Service
public class UsuarioService extends AbstractService<Usuario, Long> {
    private final UsuarioRepository usuarioRepository;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, RoleService roleService, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleService = roleService;
        this.encoder = encoder;
    }
    
    @Override
    public UsuarioRepository getRepository() {
        return this.usuarioRepository;
    }

    /**
     * Função responsável por recuperar todos os Usuários cadastrados
     * @return Lista de Usuários registrados
     */
    public Iterable<Usuario> findAll() {
        return this.getRepository().findAll();
    }

    /**
     * Função responsável por verificar existência de um usuário a partir de seu username
     * @param username String contendo o username a ser verificado
     * @return True caso exista, false caso contrário
     */
    public Boolean existsByUsername(String username) {
        return this.getRepository().existsByUsername(username);
    }

    /**
     * Função responsável por registrar um {@link Usuario}; caso o ID especificado seja null, um novo registro é cadastrado;
     * caso o ID esteja presente, o registro existente é atualizado
     * @param entity Objeto {@link Usuario} contendo os dados a serem registrados
     * @return Objeto {@link Usuario} recém-registrado
     */
    private Usuario save(Usuario entity) {
        return this.getRepository().save(entity);
    }

    /**
     * Função responsável por cadastrar um novo registro de {@link Usuario}
     * @param request Objeto contendo os dados de {@link Usuario}
     * @return Objeto {@link Usuario} recém-cadastrado
     */
    public Usuario create(UsuarioCadastroDTO request) {
        if (this.getRepository().existsByUsername(request.getUsername()))
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "O username informado já existe.");

        Role role = this.roleService.findByName(RoleName.valueOf(request.getRoleName()));

        if (role == null)
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "A role especificada não existe.");

        Usuario usuario = new Usuario(request.getUsername(), encoder.encode(request.getPassword()), request.getNome(),
                LocalDate.now(), Collections.singleton(role));

        return this.save(usuario);
    }

    /**
     * Função responsável por atualizar um registro de {@link Usuario}
     * @param id Long contendo o ID da {@link Usuario} a ser atualizado
     * @param request Objeto contendo os dados de {@link Usuario}
     * @return Objeto {@link Usuario} recém-atualizado
     */
    public Usuario update(Long id, Usuario request) {
        Usuario usuario = this.findById(id);

        usuario.setUsername(request.getUsername());
        usuario.setNome(request.getNome());

        return this.save(usuario);
    }

    /**
     * Função responsável por excluir {@link Usuario}
     * @param id Long contendo o ID do {@link Usuario} a ser excluído
     */
    public void delete(Long id) {
        this.getRepository().deleteById(id);
    }
}
