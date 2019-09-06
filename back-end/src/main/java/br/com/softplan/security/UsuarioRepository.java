package br.com.softplan.security;

import br.com.softplan.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    @Query("SELECT us FROM  Usuario us JOIN FETCH us.papel")
    public List<Usuario> buscarUsuariosComPapel();

}
