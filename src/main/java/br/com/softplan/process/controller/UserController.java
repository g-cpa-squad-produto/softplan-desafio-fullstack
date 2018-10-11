package br.com.softplan.process.controller;

import br.com.softplan.process.converter.UserInsertRequestConverter;
import br.com.softplan.process.converter.UserResponseConverter;
import br.com.softplan.process.converter.UserUpdateRequestConverter;
import br.com.softplan.process.exception.ApplicationException;
import br.com.softplan.process.request.UserInsertRequest;
import br.com.softplan.process.request.UserUpdateRequest;
import br.com.softplan.process.response.UserResponse;
import br.com.softplan.process.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid UserInsertRequest user) {
        this.service.save(insertRequestConverter.encode(user));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id,
                       @RequestBody @Valid UserUpdateRequest user) {
        user.setId(id);
        this.service.save(updateRequestConverter.encode(user));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> get() {
        return this.responseConverter.decode(this.service.findAll());
    }

    @GetMapping("/profile/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getByProfile(@PathVariable Long id) {
        return this.responseConverter.decode(this.service.findAllByProfile(id));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse find(@PathVariable Long id) throws ApplicationException {
        return this.responseConverter.decode(this.service.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) throws ApplicationException {
        this.service.delete(id);
    }
}
