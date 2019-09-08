package br.com.softplan.security.repository;

import br.com.softplan.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    @Query("SELECT us FROM  Usuario us JOIN FETCH us.papel WHERE us.situacao = 'ATIVO'")
    public List<Usuario> buscarUsuariosAtivosComPapel();

    @Query("SELECT us FROM  Usuario us JOIN FETCH us.papel WHERE us.id = :id")
    public Usuario buscar(@Param("id") Long id);

    @Query("SELECT us FROM  Usuario us JOIN FETCH us.papel WHERE us.email = :email")
    public Usuario buscar(@Param("email") String email);

}
