package br.com.softplan.feature.usuario;

import br.com.softplan.core.controller.AbstractController;
import br.com.softplan.core.exception.NegocioException;
import br.com.softplan.feature.usuario.dto.UsuarioDTO;
import br.com.softplan.feature.usuario.dto.UsuarioResumidoDTO;
import br.com.softplan.feature.usuario.model.PerfilUsuario;
import br.com.softplan.feature.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController extends AbstractController<Usuario, UsuarioDTO, UsuarioResumidoDTO, Long, UsuarioService, UsuarioMapper> {

    @Autowired
    public UsuarioController(UsuarioService service, UsuarioMapper mapper) {
        super(service, mapper);
    }

    @GetMapping(path = "/finalizadores")
    public ResponseEntity<?> pesquisarUsuariosFinalizadores() {
        return ResponseEntity.ok(service.pesquisarUsuariosFinalizadores());
    }

    @PutMapping(path = "/{id}/bloquear")
    public ResponseEntity<?> bloquearUsuario(@PathVariable Long id) throws NegocioException {
        return ResponseEntity.ok(service.bloquearUsuario(id));
    }

    @PutMapping(path = "/{id}/desbloquear")
    public ResponseEntity<?> desbloquearUsuario(@PathVariable Long id) throws NegocioException {
        return ResponseEntity.ok(service.desbloquearUsuario(id));
    }

    @GetMapping(path = "/perfis")
    public ResponseEntity<PerfilUsuario[]> listaPerfis() {
        return ResponseEntity.ok(PerfilUsuario.values());
    }

}
