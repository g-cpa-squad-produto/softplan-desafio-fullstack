package br.com.softplan.desafio.bo;

import br.com.softplan.desafio.models.Usuario;
import br.com.softplan.desafio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
public class UsuarioBO {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Usuario> search() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario salva(Usuario usuario) {

        usuario.setDataCadastro(LocalDate.now());

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualiza(Usuario usuario) {

        usuario.setDataAlteracao(LocalDate.now());

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario deleta(Long codigo) {

        Usuario usuario = usuarioRepository.findOne(codigo);

        usuario.setDataExclusao(LocalDate.now());

        return usuarioRepository.save(usuario);
    }

}
