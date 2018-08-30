package br.com.softplan.desafio.bo;

import br.com.softplan.desafio.models.Usuario;
import br.com.softplan.desafio.models.UsuarioDTO;
import br.com.softplan.desafio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class UsuarioBO {

    private static final String USER_NOT_FOUND = "Usuário não encontrado.";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> search() {
        return usuarioRepository.findAll().stream().map(Usuario::mapFrom).collect(toList());
    }



    @Transactional(readOnly = true)
    public Usuario findByNome(String nome) {
        return usuarioRepository.findByNome(nome).orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
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
