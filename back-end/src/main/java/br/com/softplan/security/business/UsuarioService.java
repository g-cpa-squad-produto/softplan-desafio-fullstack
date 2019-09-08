package br.com.softplan.security.business;

import br.com.softplan.security.dto.UsuarioDTO;
import br.com.softplan.security.entity.Usuario;
import br.com.softplan.security.entity.enumeration.SituacaoUsuarioEnum;
import br.com.softplan.security.exception.UsuarioJaExistenteException;
import br.com.softplan.security.filter.UsuarioFilter;
import br.com.softplan.security.repository.UsuarioRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CriptografiaService criptografiaService;

    @Value("sofplan-backend.senhaPadrao")
    String senhaPadrao;

    public List<UsuarioDTO> buscarTodos(UsuarioFilter filter){
        List<Usuario> usuarios = IteratorUtils.toList(usuarioRepository.findAll(filter.getMainBooleanExpression()).iterator());
        return UsuarioDTO.getListDto(usuarios);
    }

    public Usuario criar(Usuario usuario){

        validarUsuarioJaExistente(usuario);
        setDadosIniciaiNovoUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    public Usuario editar(Long id, Usuario usuario) {

        validarUsuarioJaExistente(usuario);
        Usuario usuarioEdicao = buscar(id);
        usuario.setSenha(usuarioEdicao.getSenha());
        usuario.setSituacao(usuarioEdicao.getSituacao());
        return usuarioRepository.save(usuario);
    }

    public void excluir(Long id){
        Usuario usuario = buscar(id);
        usuario.setSituacao(SituacaoUsuarioEnum.REMOVIDO);
        usuarioRepository.save(usuario);
    }

    public Usuario buscar(Long id){
        return usuarioRepository.buscar(id);
    }

    private void setDadosIniciaiNovoUsuario(Usuario usuario){
        usuario.setId(null);
        usuario.setSituacao(SituacaoUsuarioEnum.ATIVO);
        usuario.setSenha(criptografiaService.criptografarBCrypt(senhaPadrao));
    }

    private void validarUsuarioJaExistente(Usuario usuario) {

         Usuario usuarioExistente = usuarioRepository.buscar(usuario.getEmail());
        if(Objects.nonNull(usuarioExistente) && (Objects.nonNull(usuario.getId()) || !usuario.getId().equals(usuarioExistente.getId()))){
                throw new UsuarioJaExistenteException();
        }
    }
}
