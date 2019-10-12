package br.com.softplan.processmanagement.controllers;

import br.com.softplan.processmanagement.domain.Opinion;
import br.com.softplan.processmanagement.domain.UserAuthentication;
import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.security.ApiResponse;
import br.com.softplan.processmanagement.services.UsersService;
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
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    ResponseEntity<List<UserSystem>> list() {
        return ResponseEntity.ok(usersService.list());
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserAuthentication currentUser) {
        UserSystem userSystem = currentUser.getUser();
        return ResponseEntity.ok(userSystem);
    }

    @PostMapping
    ResponseEntity<ApiResponse> save(@RequestBody @Valid UserSystem userSystem) {
        userSystem = usersService.save(userSystem);
        return ResponseEntity.ok(new ApiResponse(true, "User created"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSystem> searchById(@PathVariable("id") Long id) {
        UserSystem userSystem = usersService.searchById(id);
        return ResponseEntity.ok(userSystem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid UserSystem userSystem, @PathVariable("id") Long id) {
        userSystem.setId(id);
        usersService.update(userSystem);
        return ResponseEntity.ok(new ApiResponse(true, "User updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        usersService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "User removed"));
    }

    @GetMapping(value = "/finalizadores")
    ResponseEntity<List<UserSystem>> listFinalizadores() {
        return ResponseEntity.ok(usersService.listFinalizadores());
    }

    @GetMapping(value = "/{idUser}/opinions")
    public ResponseEntity<List<Opinion>> getOpinionsByProcess(@PathVariable("idUser") Long idUser) {
        List<Opinion> opinions = usersService.getOpinionsByUser(idUser);
        return ResponseEntity.ok(opinions);
    }

    @GetMapping(value = "/process/{id}")
    ResponseEntity<List<UserSystem>> listByProcess(@PathVariable("id") Long idProcess) {
        return ResponseEntity.ok(usersService.listByProcess(idProcess));
    }

}