/**
 * 
 */
package br.com.desafiofullstack.service;

import java.util.List;
import java.util.Optional;

import br.com.desafiofullstack.domain.Usuario;

/**
 * @author Delano Jr
 *
 */
public interface UsuarioService extends BaseService<Usuario, Long> {

	Optional<List<Usuario>> consultaUsuariosAtivos();

}
