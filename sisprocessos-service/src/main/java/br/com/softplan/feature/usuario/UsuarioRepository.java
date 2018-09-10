package br.com.softplan.feature.usuario;

import br.com.softplan.feature.usuario.model.PerfilUsuario;
import br.com.softplan.feature.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByPerfil(PerfilUsuario perfil);

}
