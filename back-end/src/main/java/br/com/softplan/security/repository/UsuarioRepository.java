package br.com.softplan.security.repository;

import br.com.softplan.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, QuerydslPredicateExecutor<Usuario> {


    @Query("SELECT us FROM  Usuario us JOIN FETCH us.papel WHERE us.situacao = 'ATIVO'")
    public List<Usuario> buscarUsuariosAtivosComPapel();

    @Query("SELECT us FROM  Usuario us JOIN FETCH us.papel WHERE us.id = :id")
    public Usuario buscar(@Param("id") Long id);

    @Query("SELECT us FROM  Usuario us JOIN FETCH us.papel WHERE us.email = :email")
    public Usuario buscar(@Param("email") String email);

    @Query(value = "select pe.pe_nome from softplan.tb_permissao pe join softplan.tb_permissao_papel ppe on ppe.pe_id = pe.pe_id  join softplan.tb_papel pp on pp.pp_id = ppe.pp_id  join softplan.tb_usuario us on us.pp_id = pp.pp_id  where us.us_id = :id", nativeQuery = true)
    public List<String> buscarPermissoes(@Param("id") Long id);

}
