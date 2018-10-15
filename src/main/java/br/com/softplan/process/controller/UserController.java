package br.com.softplan.process.controller;

import br.com.softplan.process.converter.UserInsertRequestConverter;
import br.com.softplan.process.converter.UserResponseConverter;
import br.com.softplan.process.converter.UserUpdateRequestConverter;
import br.com.softplan.process.exception.ApplicationException;
import br.com.softplan.process.request.UserInsertRequest;
import br.com.softplan.process.request.UserUpdateRequest;
import br.com.softplan.process.response.UserResponse;
import br.com.softplan.process.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Api REST de usuários")
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserService service;
    private UserResponseConverter responseConverter;
    private UserInsertRequestConverter insertRequestConverter;
    private UserUpdateRequestConverter updateRequestConverter;

    @Autowired
    public UserController(UserService service, UserResponseConverter responseConverter,
                          UserInsertRequestConverter insertRequestConverter, UserUpdateRequestConverter updateRequestConverter) {
        this.service = service;
        this.responseConverter = responseConverter;
        this.insertRequestConverter = insertRequestConverter;
        this.updateRequestConverter = updateRequestConverter;
    }

    @ApiOperation(value = "Salvar usuário")
    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid UserInsertRequest user) {
        this.service.save(insertRequestConverter.encode(user));
    }

    @ApiOperation(value = "Atualizar usuário")
    @PutMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id,
                       @RequestBody @Valid UserUpdateRequest user) {
        user.setId(id);
        this.service.save(updateRequestConverter.encode(user));
    }

    @ApiOperation(value = "Listar todos os usuário")
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> get() {
        return this.responseConverter.decode(this.service.findAll());
    }

    @ApiOperation(value = "Buscar usuário por perfil")
    @GetMapping(value = "/profile/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getByProfile(@PathVariable Long id) {
        return this.responseConverter.decode(this.service.findAllByProfile(id));
    }

    @ApiOperation(value = "Buscar usuário por id")
    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse find(@PathVariable Long id) throws ApplicationException {
        return this.responseConverter.decode(this.service.findById(id));
    }

    @ApiOperation(value = "Excluir usuário por id")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) throws ApplicationException {
        this.service.delete(id);
    }
}
