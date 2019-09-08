package br.com.softplan.security.business;

import br.com.softplan.security.entity.Usuario;
import br.com.softplan.security.entity.enumeration.SituacaoUsuarioEnum;
import br.com.softplan.security.exception.UsuarioJaExistenteException;
import br.com.softplan.security.repository.UsuarioRepository;
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

    public List<Usuario> buscarTodos(){
        return usuarioRepository.buscarUsuariosAtivosComPapel();
    }

    public Usuario salvar(Usuario usuario){

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

    public Usuario buscar(String email){
        return usuarioRepository.buscar(email);
    }

    private void setDadosIniciaiNovoUsuario(Usuario usuario){
        usuario.setId(null);
        usuario.setSituacao(SituacaoUsuarioEnum.ATIVO);
        usuario.setSenha(criptografiaService.criptografarBCrypt(senhaPadrao));
    }

    private void validarUsuarioJaExistente(Usuario usuario) {

         Usuario usuarioExistente = usuarioRepository.buscar(usuario.getEmail());
        if(Objects.nonNull(usuarioExistente)){
            if(Objects.isNull(usuario.getId()) || !usuario.getId().equals(usuarioExistente.getId())){
                throw new UsuarioJaExistenteException();
            }
        }
    }
}
