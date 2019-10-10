package br.com.softplan.processmanagement.controllers;

import br.com.softplan.processmanagement.domain.UserAuthentication;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserSystemProcess;
import br.com.softplan.processmanagement.security.ApiResponse;
import br.com.softplan.processmanagement.services.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(value = "Gerenciamento de Usuários")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "Visualizar a lista de usuários cadastrados")
    @GetMapping
    ResponseEntity<List<UserSystem>> list() {
        return ResponseEntity.ok(usersService.list());
    }

    @ApiOperation(value = "Visualizar a lista de usuários finalizadores")
    @GetMapping(value="/finalizadores")
    ResponseEntity<List<UserSystem>> listFinalizadores() {
        return ResponseEntity.ok(usersService.listFinalizadores());
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserAuthentication currentUser) {
        UserSystem userSystem = currentUser.getUser();
        return ResponseEntity.ok(userSystem);
    }

    @ApiOperation(value = "Salvando um novo usuário")
    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid UserSystem userSystem) {
        userSystem = usersService.save(userSystem);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userSystem.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Buscar um usuário cadastrado")
    @GetMapping("/{id}")
    public ResponseEntity<UserSystem> searchById(@PathVariable("id") Long id) {
        UserSystem userSystem = usersService.searchById(id);
        return ResponseEntity.ok(userSystem);
    }

    @ApiOperation(value = "Atualizar dados do usuário")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid UserSystem userSystem, @PathVariable("id") Long id) {
        userSystem.setId(id);
        usersService.update(userSystem);
        return ResponseEntity.ok(new ApiResponse(true, "User updated"));
    }

    @ApiOperation(value = "Apagando o usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        usersService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "User removed"));
    }

    @ApiOperation(value = "Buscando parecer do processo")
    @GetMapping(value = "/{idUser}/opinions")
    public ResponseEntity<List<UserSystemProcess>> getOpinionsByProcess(@PathVariable("idUser") Long idUser){
        List<UserSystemProcess> userSystemProcesses = usersService.getOpinionsByUser(idUser);
        return ResponseEntity.ok(userSystemProcesses);
    }

}
