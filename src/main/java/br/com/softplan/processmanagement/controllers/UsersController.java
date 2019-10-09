package br.com.softplan.processmanagement.controllers;

import br.com.softplan.processmanagement.domain.User;
import br.com.softplan.processmanagement.services.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(usersService.list());
    }

    @ApiOperation(value = "Visualizar a lista de usuários finalizadores")
    @GetMapping(value="/finalizadores")
    ResponseEntity<List<User>> listFinalizadores() {
        return ResponseEntity.ok(usersService.listFinalizadores());
    }

    @ApiOperation(value = "Salvando um novo usuário")
    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid User user) {
        user = usersService.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Buscar um usuário cadastrado")
    @GetMapping("/{id}")
    public ResponseEntity<User> searchById(@PathVariable("id") Long id) {
        User user = usersService.searchById(id);
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "Atualizar dados do usuário")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid User user, @PathVariable("id") Long id) {
        user.setId(id);
        usersService.update(user);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Apagando o usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        usersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
