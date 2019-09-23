package br.com.soft.as;

import br.com.soft.dao.GenericDao;
import com.soft.core.entidades.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Moquiuti
 */
@LocalBean
@Stateless
public class UsuarioAS {

    @EJB
    private GenericDao<Usuario, Long> dao;

    private final Logger log = LogManager.getLogger(getClass());

    public Usuario criar(Usuario usuario) {
        return dao.salvar(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        return dao.atualizar(usuario);
    }

    public Long getFindByIdTipo(Long id, String tipo) {
        String query = "select u.id from usuario u where u.id = " + id + " and u.tipousuario = '" + tipo + "' ";
        Query queryNative = dao.getEntityManager().createNativeQuery(query);
        Long usuario = null;
        try {
            usuario = (Long) queryNative.getSingleResult();
        } catch (NoResultException e) {
            log.error(" Falha na tentativa de busca de usuario por id e tipo {}, {}", id, e.getMessage());
        }
        return usuario;
    }

    public List<Usuario> getTodos() {
        return dao.getList(Usuario.class);
    }
}
